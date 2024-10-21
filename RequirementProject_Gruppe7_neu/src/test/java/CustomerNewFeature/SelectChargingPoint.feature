Feature: Finding and Selecting a Charging Location
  In order to charge my electric vehicle
  As a customer
  I want to find and select a charging location and choose the charging type (AC or DC)

  Scenario: Customer finds and selects a charging location
    Given the customer is logged into their account
    And the system has the following available charging locations:
      | location       | status    |
      | Main Street 1  | Available |
      | Park Avenue 5  | Available |
    When the customer searches for charging locations near "Park Avenue"
    Then the system displays the following available charging locations:
      | location       | status    |
      | Park Avenue 5  | Available |
    And the following charging locations are displayed:
      | location       | status    |
      | Park Avenue 5  | Available |
    When the customer selects the charging location "Park Avenue 5"
    Then the system confirms the selection of "Park Avenue 5"

     ############################################################
     ############################################################
     ########################Error-Case##########################
     ############################################################
     ############################################################

  Scenario: Customer searches for unavailable charging locations
    Given the customer is logged into their account
    And the system has the following available charging locations:
      | location       | status    |
      | Main Street 1  | Available |
    When the customer searches for charging locations near "Nonexistent Street"
    Then the system displays an error message "No charging locations found"

