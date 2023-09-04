Feature: Create Get request function
  I want to use this template for my feature file

   Scenario: Create User Details
    Given I have the base URL 
    And I have following headers:
     | Content-Type | application/json |
    And I have following JSON request body "CreateUserDetails_API_Test01.txt"
    When I send a GET request to "/todos/1"
    Then response status code should be 200
    And the response should contain
    |userId|1|
    |id|1|
    |title|delectus aut autem|
    |completed|false|
    
   Scenario: Create User Details with invalid data
    Given I have the base URL 
    And I have following headers:
     | Content-Type | application/json |
    And I have following JSON request body "CreateUserDetails_API_Test02.txt"
    When I send a GET request to "/todos/1"
    Then response status code should be 400
    And the response should contain
    |message_code|bad_request_parameters|
    |validation_error.description[0]|required_field|
    
    
    
 