Feature: Charging Session Management
  In order to use charging services
  As a customer
  I want to log in with my customer ID, start a charging session, and get live updates on the charging status

  Scenario: Customer successfully logs in with customer ID
    Given the customer is on the login page
    When the customer enters the following login details:
      | field      | value            |
      | customerID | CUST123456      |
      | password    | Password!123     |
    And the customer submits the login form
    Then the system authenticates the customer
    And the customer is redirected to their account dashboard
    And the system displays a welcome message "Welcome back, Customer!"

  Scenario: Customer starts a charging session
    Given the customer is logged into their account
    And the customer has selected the charging location "Park Avenue 5"
    And the customer has chosen the charging type "DC"
    When the customer presses the "Start Charging" button
    Then the system starts the charging session
    And the customer receives a confirmation message "Charging session started at Park Avenue 5."

  Scenario: Customer gets live charging updates
    Given the customer is logged into their account
    And the customer has an active charging session at "Park Avenue 5"
    When the customer requests to see the live charging updates
    Then the system displays the following charging updates:
      | field          | value                |
      | energyCharged  | 5 kWh               |
      | duration       | 15 minutes           |
      | currentStatus  | Charging             |
      | estimatedCost  | €1.50                |
