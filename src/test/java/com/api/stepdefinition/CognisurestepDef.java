package com.api.stepdefinition;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.pages.cognisurePages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class CognisurestepDef {
	public static Response response;
	private static final Logger logger = LogManager.getLogger(CognisurestepDef.class);

	@Given("User wants to set base URL for {string} API")
	public void userLaunchesTheBaseURL(String base) {
		cognisurePages.setBaseURI(cognisurePages.getAnyBaseURLForAPIFromConfig(base));
	}

	@And("User wants to set {string} as for the form parameter")
	public void userWantsToSetFromParameters(String formParameter) {
		cognisurePages.getAnyFormParametersForHeader(formParameter);
	}

	@When("User sends an authentication request with username and password")
	public void userSendsAuthenticationRequest() {
		response = cognisurePages.sendAuthenticationRequest();
	}

	@Then("User Validates the expected response {int} from the API")
	public void userValidatesTheExectedStatusCode(int expectedStatusCode) {
		cognisurePages.printResponseDetails(response);
		assertEquals("Unexpected response code", expectedStatusCode, response.getStatusCode());
	}

	@And("User verifies the access token, token type, expires in, and user name should be available in the response")
	public void userVerifiesTheDisplayOfExectedResponse() {
		cognisurePages.printTokenDetails(response);
	}

}
