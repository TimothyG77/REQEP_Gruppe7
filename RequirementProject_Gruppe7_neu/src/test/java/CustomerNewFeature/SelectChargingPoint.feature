Feature: Finding and Selecting a Charging Location
  In order to charge my electric vehicle
  As a customer
  I want to find and select a charging location and choose the charging type (AC or DC)

  Scenario: Customer finds and selects a charging location
    Given the customer is logged into their account
    And the system has the following available charging locations:
      | location      | type   | status     |
      | Main Street 1 | AC     | Available  |
      | Park Avenue 5 | DC     | Available  |
      | Elm Street 3  | AC     | Occupied   |
    When the customer searches for charging locations near "Main Street"
    Then the system displays the following available charging locations:
      | location      | type   | status     |
      | Main Street 1 | AC     | Available  |
      | Park Avenue 5 | DC     | Available  |

  Scenario: Customer selects a charging location
    Given the customer is on the charging location selection page
    And the following charging locations are displayed:
      | location      | type   | status     |
      | Main Street 1 | AC     | Available  |
      | Park Avenue 5 | DC     | Available  |
    When the customer selects the charging location "Park Avenue 5"
    Then the system confirms the selection of "Park Avenue 5"

  Scenario: Customer selects charging type AC
    Given the customer has selected the charging location "Park Avenue 5"
    When the customer chooses the charging type "AC"
    Then the system confirms the selection of charging type "AC"
    And the customer receives a message "You have selected AC charging at Park Avenue 5."

  Scenario: Customer selects charging type DC
    Given the customer has selected the charging location "Main Street 1"
    When the customer chooses the charging type "DC"
    Then the system confirms the selection of charging type "DC"
    And the customer receives a message "You have selected DC charging at Main Street 1."
