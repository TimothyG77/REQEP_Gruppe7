Feature: Viewing List of Charging Locations
  In order to manage my charging stations
  As an owner
  I want to see a list of all charging locations with their ID numbers

  Scenario: Owner views a list of all charging locations
    Given the owner is logged into their account
    And the system has the following charging locations:
      | idNumber | location      | type   | status     |
      | 001      | Main Street 1 | AC     | Available  |
      | 002      | Park Avenue 5 | DC     | Occupied   |
      | 003      | Elm Street 3  | AC     | Out of Service |
    When the owner navigates to the "Charging Locations" page
    Then the system displays the following list of charging locations:
      | idNumber | location      | type   | status     |
      | 001      | Main Street 1 | AC     | Available  |
      | 002      | Park Avenue 5 | DC     | Occupied   |
      | 003      | Elm Street 3  | AC     | Out of Service |

