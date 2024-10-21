Feature: Customer Registration
  In order to create a new account for charging services
  As a customer
  I want to register using my email address, phone number, password, and payment details

  Scenario: Customer successfully registers with valid information
    Given the customer is on the registration page
    When the customer enters the following details:
      | field              | value                  |
      | email              | customer@gmail.com     |
      | phone              | +491234567890          |
      | password           | Password!123           |
      | paymentMethod      | Credit Card            |
      | paymentDetails     | 1234 5678 9012 3456    |
    And the customer submits the registration form
    Then the system creates a new account for the customer
    And the customer receives a confirmation message "Registration successful!"

    #############################################################
    #############################################################
    #########################Error-Case##########################
    #############################################################
    #############################################################

  Scenario: Customer fails to register with missing payment details
    Given the customer is on the registration page
    When the customer enters the following details:
      | field         | value              |
      | email         | customer@gmail.com |
      | phone         | +491234567890      |
      | password      | Password!123       |
      | paymentMethod | Credit Card        |
      | paymentDetails|                    |
    And the customer submits the registration form
    Then the system rejects the registration with an error message "Payment details are required"




