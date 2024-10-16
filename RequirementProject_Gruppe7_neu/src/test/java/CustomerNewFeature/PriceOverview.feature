Feature: Viewing Current Price Overview for Charging Locations
  In order to manage pricing for charging services
  As an owner
  I want to see the current price overview per charging location and per kWh

  Scenario: Owner views price overview for charging locations
    Given the owner is logged into their account
    And the system has the following pricing information:
      | location      | pricePerKWh | pricePerMinute |
      | Main Street 1 | €0.30       | €0.05          |
      | Park Avenue 5 | €0.50       | €0.10          |
      | Elm Street 3  | €0.25       | €0.04          |
    When the owner navigates to the "Price Overview" page
    Then the system displays the following price overview:
      | location      | pricePerKWh | pricePerMinute |
      | Main Street 1 | €0.30       | €0.05          |
      | Park Avenue 5 | €0.50       | €0.10          |
      | Elm Street 3  | €0.25       | €0.04          |

  Scenario: Owner views price per kWh for a specific location
    Given the owner is logged into their account
    And the owner selects "Main Street 1" from the price overview
    Then the system displays the price details for "Main Street 1":
      | field         | value       |
      | pricePerKWh  | €0.30      |
      | pricePerMinute| €0.05      |


