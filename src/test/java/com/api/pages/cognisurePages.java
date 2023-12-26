package com.api.pages;

import static io.restassured.RestAssured.given;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.api.stepdefinition.CognisurestepDef;
import com.api.utils.PropertiesFile;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class cognisurePages {

	public static Response response;
	public static RequestSpecification reqspec;
	private static final Logger logger = LogManager.getLogger(CognisurestepDef.class);

	public static String GRANT_TYPE;
	public static String USERNAME;
	public static String PASSWORD;

	public static String getAnyBaseURLForAPIFromConfig(String base) {
		String BaseURI = null;
		switch (base) {
		case "Login":
			BaseURI = PropertiesFile.getProperty("login_baseURL");
			break;
		}
		return BaseURI;
	}
	
	public static void setBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	public static void getAnyFormParametersForHeader(String reqFormParameter) {
		switch (reqFormParameter) {
		case "grant type":
			GRANT_TYPE = PropertiesFile.getProperty("grant_type");
			break;
		case "username":
			USERNAME = PropertiesFile.getProperty("username");
			break;
		case "password":
			PASSWORD = PropertiesFile.getProperty("password");
			break;
		}
	}

	public static Response sendAuthenticationRequest() {
		return given().header("accept", PropertiesFile.getProperty("accept")).header("Content-Type", PropertiesFile.getProperty("content.type"))
				.formParam("grant_type", GRANT_TYPE).formParam("username", USERNAME).formParam("password", PASSWORD)
				.when().post("/token");
	}

	public static void printResponseDetails(Response response) {
		try {
			logger.info("Response Code: " + response.getStatusCode());
			/* System.out.println("Response Body: " + response.getBody().asString()); */
		} catch (Exception e) {
			logger.error("Error occured while getting response: " + e.getStackTrace() + e.getMessage());
		}
	}

	public static void printTokenDetails(Response response) {
		try {
			logger.info("Access Token: " + response.jsonPath().getString("access_token"));
			logger.info("Token Type: " + response.jsonPath().getString("token_type"));
			logger.info("Expires In: " + response.jsonPath().getInt("expires_in"));
			logger.info("User Name: " + response.jsonPath().getString("userName"));
		} catch (Exception e) {
			logger.error(
					"Error occured while getting details from response: " + e.getStackTrace() + e.getMessage());
		}
	}
}
