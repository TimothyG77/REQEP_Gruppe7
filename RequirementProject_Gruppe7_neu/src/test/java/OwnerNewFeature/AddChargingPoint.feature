Feature: Adding AC/DC Charging Points
  In order to manage charging points at my locations
  As an owner
  I want to add AC/DC charging points to specific locations

  Scenario: Owner successfully adds an AC charging point to a location
    Given the owner is on the "Add Charging Point" page
    When the owner enters the following details:
      | field      | value                |
      | location   | Main Street 1         |
      | type       | AC                   |
      | power      | 11 kW                |
      | status     | Available            |
    And the owner submits the form
    Then the system adds the new "AC" charging point to "Main Street 1"
    And the owner receives a confirmation message "AC Charging Point added successfully."

  Scenario: Owner successfully adds a DC charging point to a location
    Given the owner is on the "Add Charging Point" page
    When the owner enters the following details:
      | field      | value                |
      | location   | Park Avenue 5         |
      | type       | DC                   |
      | power      | 50 kW                |
      | status     | Available            |
    And the owner submits the form
    Then the system adds the new "DC" charging point to "Park Avenue 5"
    And the owner receives a confirmation message "DC Charging Point added successfully."

     ############################################################
     ############################################################
     ########################Error-Case##########################
     ############################################################
     ############################################################

  Scenario: Owner fails to add a charging point with missing location
    Given the owner is on the "Add Charging Point" page
    When the owner enters the following details:
      | field      | value      |
      | location   |            |
      | type       | AC         |
      | power      | 11 kW      |
      | status     | Available  |
    And the owner submits the form
    Then the system rejects the submission with an error message "Location is required"

