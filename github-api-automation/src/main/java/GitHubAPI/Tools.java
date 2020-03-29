package GitHubAPI;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//import com.jayway.restassured.response.Headers;
//
public class Tools {
	public static Map<String, String> convertKeystoLowerCase(Map<String, String> inputMap) {
		Map<String, String> resultMap;
		try {
			resultMap = new HashMap<String, String>();
			Set<String> x1 = inputMap.keySet();
			for (Iterator iterator = x1.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				resultMap.put(key.toLowerCase(), String.valueOf(inputMap.get(key)));
			}
			return resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> verifyResults(Map<String, String> actualdata, Map<String, String> expecteddata) {
		boolean responsetimeVerification = false;
		String keysVerified = "";
		Map<String, String> returnmap = new HashMap<String, String>();
		try {
			// Below loop will be executed only if expected is not having
			// response time
			for (Map.Entry<String, String> actual : actualdata.entrySet()) {
				if (actual.getKey().contains("responsetime") && !(expecteddata.containsKey("responsetime"))) {
					// Response time verification needs to be done only for API
					// response

					responsetimeVerification = true;
				}
			}
			Map<String, String> map1 = convertKeystoLowerCase(actualdata);
			Map<String, String> map2 = convertKeystoLowerCase(expecteddata);
			for (Map.Entry<String, String> expected : map2.entrySet()) {
				if (map1.containsKey(expected.getKey())) {
					if (expected.getValue() == null || map1.get(expected.getKey()) == null) {
						map2.put(expected.getKey(), "null");
						map1.put(expected.getKey(), "null");
					}
					if (expected.getKey().contains("responsetime")) {
						keysVerified = keysVerified + "|" + expected.getKey();
						if (Integer.valueOf(map1.get(expected.getKey())) < Integer
								.valueOf(map2.get(expected.getKey()))) {
							Assert.assertTrue(true);
						} else {
							System.out.println("RESPONSE TIME :: <font color='red'>"
									+ "Expected API response time should be less than " + map2.get(expected.getKey())
									+ " but found " + map1.get(expected.getKey()) + "</font>");
						}
					} else if ((expected.getValue().matches("[0-9]+") && (expected.getValue().length() > 9))) {
						long act = Long.parseLong(map1.get(expected.getKey()));
						long exp = Long.parseLong(map2.get(expected.getKey()));
						Assert.assertTrue(act >= exp, "Time stamp verified");
						keysVerified = keysVerified + "|" + expected.getKey();
					} else {
						Assert.assertEquals(map1.get(expected.getKey()), map2.get(expected.getKey()),
								"Actual Data for key " + expected.getKey() + " is " + map1.get(expected.getKey())
										+ " Expected Data for key " + expected.getKey() + " is "
										+ map2.get(expected.getKey()));
						keysVerified = keysVerified + "|" + expected.getKey();
					}
				} else {
					returnmap.put(expected.getKey(), "KEY_NOT_FOUND");
				}
			}
			if (responsetimeVerification && !keysVerified.contains("responsetime")) {
				System.out.println("Response Time :: Not verified ");
			}
			if (!returnmap.isEmpty() || returnmap.isEmpty()) {

				System.out.println("Verification Done :: Keys Verified = " + keysVerified);
			}
		} catch (Exception e) {
			System.out.println("Verification-Exception" + e.getMessage());
		}
		return returnmap;
	}

	public static HashMap convertJsonToMap(String jsonString) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JSONObject jObject = new JSONObject(jsonString);
		Iterator<?> keys = jObject.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Object value = jObject.get(key);
			map.put(key, value);
		}
		return map;
	}

	public static Map jsonToMapConverter1(String jsonData) {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		try {
			Gson gson = new Gson();
			// Map inspectionObj = gson.fromJson(jsondata, Map.class);
			Map inspectionObj = convertJsonToMap(jsonData);
			// logger.info( "map:--" + inspectionObj );

			// put non json data in map
			Iterator datalocal = dataMap.entrySet().iterator();
			while (datalocal.hasNext()) {
				Map.Entry extradatamap = (Map.Entry) datalocal.next();
				// logger.info(extradatamap.getKey() + " = "
				// + extradatamap.getValue());
				// inspectionObj.put(extradatamap.getKey(),
				// extradatamap.getValue());
				System.out.println(extradatamap.getKey() + " = " + extradatamap.getValue());
				inspectionObj.put(extradatamap.getKey(), extradatamap.getValue());
			}
			return inspectionObj;
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			System.out.println("Exception in stringToJsonConverter For test Data " + jsonData + "\n" + e);
		}
		return null;
	}

	public static Map<String, String> convertObjMapToStringMap(Map temp) {
		Map<String, String> retTemp = new HashMap<String, String>();
		Iterator it = temp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry expectedData = (Map.Entry) it.next();
			retTemp.put(String.valueOf(expectedData.getKey()), String.valueOf(expectedData.getValue()));
		}
		System.out.println("converted Map - " + retTemp);
		return retTemp;
	}

	// public static String requestURI = null;
	// public static Object requestObject = null;
	// public static final String REQUEST_URL="requestURI";
	// public static final String REQUEST_BODY="requestBody";
	// public static final String METHOD="method";
	// public static Headers header = new Headers();
	// public static HTTPQueryParams queryParams = new HTTPQueryParams();
	// public static HTTPParams params = new HTTPParams();
	//
	// public static void sendRequest(String input, String methodName) {
	//
	// JSONObject jsonObject = stringToJson(input);
	// try {
	// String path = null;
	//
	// path = requestURI;
	//
	// // if (jsonObject.containsKey(REQUEST_BODY))
	// //LogExtent.jsonInfo("Request Body for "+ methodName +" is ",
	// jsonObject.toString());
	// HTTPRequest requestSpec = new HTTPRequest(header, params, queryParams,
	// requestObject);
	// if (null == jsonObject.get(METHOD))
	// response = rc.sendRequest(HTTPMethod.GET, path, requestSpec);
	// else {
	//
	// switch (jsonObject.get(METHOD).toString().toLowerCase()) {
	// case "delete":
	// response = rc.sendRequest(HTTPMethod.DELETE, path, requestSpec);
	// break;
	// case "post":
	// response = rc.sendRequest(HTTPMethod.POST, path, requestSpec);
	// break;
	// case "put":
	// response = rc.sendRequest(HTTPMethod.PUT, path, requestSpec);
	// break;
	// default:
	// response = rc.sendRequest(HTTPMethod.GET, path, requestSpec);
	// break;
	// }
	// }
	// //LogExtent.info( "Method -> " + jsonObject.get( METHOD
	// ).toString().toUpperCase() + " and End point " + path );
	// //LogExtent.jsonInfo( "Reaponse Status code is -> " +
	// response.getStatusCode() + " and Response Body for " + methodName + " is ",
	// response
	// // .getBody().getBodyText() );
	// } catch (Exception e) {
	// // Log.error("Error:: " + e);
	// }
	// }
	// public static JSONObject stringToJson(String input) {
	// try {
	// JSONParser parser = new JSONParser();
	// JSONObject json = (JSONObject) parser.parse(input);
	// // logs.info("test data " + input);
	// return json;
	// } catch (ParseException e) {
	// // Log.error("Error in pasrsing input data:" + e);
	// return null;
	// }
	// }
}
