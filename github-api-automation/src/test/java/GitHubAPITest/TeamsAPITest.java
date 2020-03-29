package GitHubAPITest;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import GitHubAPI.Tools;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TeamsAPITest {

	@Test // (dataProvider = "teams", dataProviderClass = ExcelReader.class)
	public void getTeam() {

		RestAssured.baseURI = "https://api.github.com/orgs/TestOrganisa/teams";

		RequestSpecification httpRequest = RestAssured.given();

		int statusCode;

		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic("deepitripathi13@gmail.com", "56f680a874088405c7d8316bbf6f238bedb2a117");

		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.GET);
		statusCode = responseWithAuth.getStatusCode();
		// response
		Assert.assertEquals(statusCode, 200);
		System.out.println(responseWithAuth.getBody().asString());

	}

	@Test // (dataProvider = "teams", dataProviderClass = ExcelReader.class)
	public void createTeam() {
		RestAssured.baseURI = "https://api.github.com/orgs/TestOrganisa/teams";

		RequestSpecification httpRequest = RestAssured.given();
		int statusCode;

		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic("deepitripathi13@gmail.com", "56f680a874088405c7d8316bbf6f238bedb2a117");
		httpRequest.body("{\n" + "  \"name\": \"Justice\",\n" + "  \"description\": \"A great team\",\n"
				+ "  \"permission\": \"admin\",\n" + "  \"privacy\": \"closed\"\n" + "}");
		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.POST);
		statusCode = responseWithAuth.getStatusCode();
		System.out.println(statusCode);
		System.out.println(responseWithAuth.getBody().asString());

	}

	@Test // (dataProvider = "teams", dataProviderClass = ExcelReader.class)
	public void updateTeam() {

		String teamname = createTeamForTest();
		RestAssured.baseURI = "https://api.github.com/orgs/TestOrganisa/teams/" + teamname;

		RequestSpecification httpRequest = RestAssured.given();
		int statusCode;

		String newTeamMName = "Deepika" + System.currentTimeMillis();
		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic("deepitripathi13@gmail.com", "56f680a874088405c7d8316bbf6f238bedb2a117");
		httpRequest.body("{\n" + "  \"name\": \"" + newTeamMName + "\",\n"
				+ "  \"description\": \"new team description\",\n" + "  \"privacy\": \"closed\"\n" + "}");
		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.PATCH);
		statusCode = responseWithAuth.getStatusCode();
		System.out.println(statusCode);
		System.out.println(responseWithAuth.getBody().asString());
		JSONObject response = new JSONObject(responseWithAuth.getBody().asString());
		// getTeamForTest(response.getString("name"));
		Tools.verifyResults(
				Tools.convertObjMapToStringMap(Tools.jsonToMapConverter1(responseWithAuth.getBody().asString())),
				Tools.convertObjMapToStringMap(Tools.jsonToMapConverter1(getTeamForTest(response.getString("name")))));
	}

	@Test // (dataProvider = "teams", dataProviderClass = ExcelReader.class)
	public void deleteTeam() {
		RestAssured.baseURI = "https://api.github.com/orgs/TestOrganisa/teams/justicej";

		RequestSpecification httpRequest = RestAssured.given();
		int statusCode;

		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic("deepitripathi13@gmail.com", "56f680a874088405c7d8316bbf6f238bedb2a117");

		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.DELETE);
		statusCode = responseWithAuth.getStatusCode();
		System.out.println(statusCode);
		assertEquals(statusCode, 204);
		System.out.println(responseWithAuth.getBody().asString());

	}

	public String createTeamForTest() {

		RestAssured.baseURI = "https://api.github.com/orgs/TestOrganisa/teams";

		RequestSpecification httpRequest = RestAssured.given();
		int statusCode;
		String teamName = "Team" + System.currentTimeMillis();
		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic("deepitripathi13@gmail.com", "56f680a874088405c7d8316bbf6f238bedb2a117");
		httpRequest.body("{\n" + "  \"name\":\"" + teamName + "\",\n" + "  \"description\": \"A great team\",\n"
				+ "  \"permission\": \"admin\",\n" + "  \"privacy\": \"closed\"\n" + "}");
		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.POST);
		statusCode = responseWithAuth.getStatusCode();
		System.out.println(statusCode);
		System.out.println(responseWithAuth.getBody().asString());

		JSONObject response = new JSONObject(responseWithAuth.getBody().asString());
		response.getString("name");

		return response.getString("name");

	}

	@Test // (dataProvider = "teams", dataProviderClass = ExcelReader.class)
	public String getTeamForTest(String name) {

		RestAssured.baseURI = "https://api.github.com/orgs/TestOrganisa/teams/" + name;

		RequestSpecification httpRequest = RestAssured.given();

		int statusCode;

		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic("deepitripathi13@gmail.com", "56f680a874088405c7d8316bbf6f238bedb2a117");

		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.GET);
		statusCode = responseWithAuth.getStatusCode();
		// response
		Assert.assertEquals(statusCode, 200);
		System.out.println(responseWithAuth.getBody().asString());

		return responseWithAuth.getBody().asString();
	}

}
