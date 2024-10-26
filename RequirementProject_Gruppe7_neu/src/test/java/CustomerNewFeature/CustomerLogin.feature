Feature: Customer Login
  In order to access my account
  As a customer
  I want to log in using my email or phone number and password

  Scenario: Customer successfully logs in with email
    Given the customer is on the login page
    When the customer enters the following login details:
      | field         | value                     |
      | email         | customer@gmail.com        |
      | password      | Password!123              |
    And the customer submits the login form
    Then the system authenticates the customer
    And the customer is redirected to their account dashboard
    And the system displays a welcome message "Welcome back, Customer!"

  Scenario: Customer successfully logs in with phone number
    Given the customer is on the login page
    When the customer enters the following login details:
      | field         | value                     |
      | phone         | +491234567890             |
      | password      | Password!123              |
    And the customer submits the login form
    Then the system authenticates the customer
    And the customer is redirected to their account dashboard
    And the system displays a welcome message "Welcome back, Customer!"

    #############################################################
    #############################################################
    #########################Error-Case##########################
    #############################################################
    #############################################################

  Scenario: Customer fails to log in with incorrect password
    Given the customer is on the login page
    When the customer enters the following login details:
      | field    | value                 |
      | email    | customer@gmail.com     |
      | password | WrongPassword          |
    And the customer submits the login form
    Then the system rejects the login attempt with an error message "Invalid credentials"

    #############################################################
    #############################################################
    #########################Edge-Case###########################
    #############################################################
    #############################################################

  Scenario: Customer fails to log in with an empty password
    Given the customer is on the login page
    When the customer enters the following login details:
      | field    | value                 |
      | email    | customer@gmail.com     |
      | password |                       |
    And the customer submits the login form
    Then the system rejects the login attempt with an error message "Password cannot be empty"
