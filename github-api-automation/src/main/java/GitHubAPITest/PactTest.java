package GitHubAPITest;


import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

public class PactTest {
	@Rule
	public PactProviderRule mockProvider
	  = new PactProviderRule("test_provider", "localhost", 8080, this);
	
	@Pact(consumer = "test_consumer")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
	    Map<String, String> headers = new HashMap<>();
	    headers.put("Content-Type", "application/json");
	 
	    return builder
	      .given("test GET")
	        .uponReceiving("GET REQUEST")
	        .path("/pact")
	        .method("GET")
	      .willRespondWith()
	        .status(200)
	        .headers(headers)
	        .body("{\"condition\": true, \"name\": \"tom\"}").toPact();
	//        (...)
	}
	
	
	@Test
	@PactVerification()
	public void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody() {
	  
	    // when
	    ResponseEntity<String> response = new RestTemplate().getForEntity(mockProvider.getUrl() + "/pact", String.class);
	 
//	     then
//	    assertThat(response.getStatusCode().value()).isEqualTo(200);
//	    assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();
//	    assertThat(response.getBody()).contains("condition", "true", "name", "tom");
//	    
	    System.out.println(response.getStatusCode().value());
//	    assertEquals(actual, expected);
//	    assertEquals(response.getBody().get, expected);
	}
}
