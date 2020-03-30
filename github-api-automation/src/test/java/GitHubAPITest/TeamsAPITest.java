package GitHubAPITest;

import static org.testng.Assert.assertEquals;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import GitHubAPI.Tools;
import Utils.Logs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TeamsAPITest {
	private String username="deepitripathi13@gmail.com";
	private String password="83e2c95911a00414f47331b7a7aa2549d3d81a3b";
	private static String url="https://api.github.com/orgs/TestOrganisa/teams"; 

	/**@description:This test retrieves all the teams under organisation specified from github
	 * @author: Deepika
	 */
	@Test 
	public void getTeam() {

		RestAssured.baseURI = url;
		RequestSpecification httpRequest = RestAssured.given();

		int statusCode;

		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic(username, password);

		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.GET);
		statusCode = responseWithAuth.getStatusCode();
		// response
		Assert.assertEquals(statusCode, 200);
		Logs.logger.info(responseWithAuth.getBody().asString());

	}

	/**@description:This test creates a new team under organisation specified from github
	 * @author: Deepika
	 */
	@Test
	public void createTeam() {
		String newTeamMName = "NewTeam" + System.currentTimeMillis();
		RestAssured.baseURI = url;

		RequestSpecification httpRequest = RestAssured.given();
		int statusCode;

		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic(username,password);
		httpRequest.body("{\n" + "  \"name\": \"" + newTeamMName + "\",\n" + "  \"description\": \"A great team\",\n"
				+ "  \"permission\": \"admin\",\n" + "  \"privacy\": \"closed\"\n" + "}");
		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.POST);
		statusCode = responseWithAuth.getStatusCode();
		Logs.logger.info(statusCode);
		Logs.logger.info(responseWithAuth.getBody().asString());
		// response
		Assert.assertEquals(statusCode, 201);

	}

	/**@description:This test updates/edits a team under organisation and team specified from github
	 * @author: Deepika
	 */
	@Test
	public void updateTeam() {

		String teamname = createTeamForTest();
		RestAssured.baseURI = url + "/" + teamname;

		RequestSpecification httpRequest = RestAssured.given();
		int statusCode;

		String newTeamMName = "Deepika" + System.currentTimeMillis();
		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic(username, password);
		httpRequest.body("{\n" + "  \"name\": \"" + newTeamMName + "\",\n"
				+ "  \"description\": \"new team description\",\n" + "  \"privacy\": \"closed\"\n" + "}");
		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.PATCH);
		statusCode = responseWithAuth.getStatusCode();
		Logs.logger.info(statusCode);
		Logs.logger.info(responseWithAuth.getBody().asString());
		JSONObject response = new JSONObject(responseWithAuth.getBody().asString());
		// getTeamForTest(response.getString("name"));
		Tools.verifyResults(
				Tools.convertObjMapToStringMap(Tools.jsonToMapConverter1(responseWithAuth.getBody().asString())),
				Tools.convertObjMapToStringMap(Tools.jsonToMapConverter1(getTeamForTest(response.getString("name")))));
	}

	/**@description:This test deletes a team under organisation specified from github
	 * @author: Deepika
	 */
	@Test
	public void deleteTeam() {
		String teamname = createTeamForTest();
		RestAssured.baseURI = url+ "/"+teamname;

		RequestSpecification httpRequest = RestAssured.given();
		int statusCode;

		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic(username, password);

		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.DELETE);
		statusCode = responseWithAuth.getStatusCode();
		Logs.logger.info(statusCode);
		assertEquals(statusCode, 204);
		Logs.logger.info(responseWithAuth.getBody().asString());

	}

	/**@description:This method creates a team in github and returns its name
	 */	
	public String createTeamForTest() {

		RestAssured.baseURI = url;

		RequestSpecification httpRequest = RestAssured.given();
		int statusCode;
		String teamName = "Team" + System.currentTimeMillis();
		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic(username, password);
		httpRequest.body("{\n" + "  \"name\":\"" + teamName + "\",\n" + "  \"description\": \"A great team\",\n"
				+ "  \"permission\": \"admin\",\n" + "  \"privacy\": \"closed\"\n" + "}");
		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.POST);
		statusCode = responseWithAuth.getStatusCode();
		Logs.logger.info(statusCode);
		Logs.logger.info(responseWithAuth.getBody().asString());

		JSONObject response = new JSONObject(responseWithAuth.getBody().asString());
		response.getString("name");

		return response.getString("name");

	}

	/**@description:This method retrieves a team by the team name passed and returns its body
	 * @param: name
	 * 				Name of the new team whose body is to be retrieved
	 */	
	public String getTeamForTest(String name) {

		RestAssured.baseURI = url + "/"+ name;

		RequestSpecification httpRequest = RestAssured.given();

		int statusCode;

		// Passing the Basic Auth username and password
		httpRequest.auth().preemptive().basic(username, password);

		// Expecting our Auth works and verifying status code
		Response responseWithAuth = httpRequest.request(Method.GET);
		statusCode = responseWithAuth.getStatusCode();
		// response
		Assert.assertEquals(statusCode, 200);
		Logs.logger.info(responseWithAuth.getBody().asString());

		return responseWithAuth.getBody().asString();
	}

}
