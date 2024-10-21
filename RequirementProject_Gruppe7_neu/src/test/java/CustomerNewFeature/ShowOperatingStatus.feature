Feature: Managing Charging Point Operating Status
  In order to effectively manage the availability of charging points
  As an owner
  I want to show the operating status of charging points as "Available", "Out of Service", or "Occupied"

  Scenario: Owner sets charging point status to "Available"
    Given the owner is logged into their account
    And the owner has an existing charging point at "Main Street 1"
    When the owner selects "Set Status"
    And the owner sets the status to "Available"
    Then the system updates the status of "Main Street 1" to "Available"

  Scenario: Owner sets charging point status to "Out of Service"
    Given the owner is logged into their account
    And the owner has an existing charging point at "Park Avenue 5"
    When the owner selects "Set Status"
    And the owner sets the status to "Out of Service"
    Then the system updates the status of "Park Avenue 5" to "Out of Service"

  Scenario: Owner sets charging point status to "Occupied"
    Given the owner is logged into their account
    And the owner has an existing charging point at "Elm Street 3"
    When the owner selects "Set Status"
    And the owner sets the status to "Occupied"
    Then the system updates the status of "Elm Street 3" to "Occupied"
