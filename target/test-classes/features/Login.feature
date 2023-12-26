@bookerAPI
Feature: Authentication API

  Scenario Outline: Authentication with API
  	Given User wants to set base URL for "Login" API
    And User wants to set "grant type" as for the form parameter
    And User wants to set "username" as for the form parameter
    And User wants to set "password" as for the form parameter
    When User sends an authentication request with username and password
    Then User Validates the expected response <StatusCode> from the API
    And User verifies the access token, token type, expires in, and user name should be available in the response

    Examples:
     | StatusCode |
     | 200        |






