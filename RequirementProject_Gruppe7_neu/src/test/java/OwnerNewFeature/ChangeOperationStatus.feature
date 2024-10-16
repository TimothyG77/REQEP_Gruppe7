Feature: Managing Charging Point Status
  In order to effectively manage the availability of charging points
  As an owner
  I want to set the operating status of charging points to "Free", "Occupied", or "Out of Service"

  Scenario: Owner successfully sets a charging point status to Free
    Given the owner is logged into their account
    And the owner has an existing charging point at "Main Street 1"
    When the owner selects "Set Status"
    And the owner sets the status to "Free"
    Then the system updates the status of "Main Street 1" to "Free"
    And the owner receives a confirmation message "Status updated to Free."

  Scenario: Owner successfully sets a charging point status to Occupied
    Given the owner is logged into their account
    And the owner has an existing charging point at "Park Avenue 5"
    When the owner selects "Set Status"
    And the owner sets the status to "Occupied"
    Then the system updates the status of "Park Avenue 5" to "Occupied"
    And the owner receives a confirmation message "Status updated to Occupied."

  Scenario: Owner successfully sets a charging point status to Out of Service
    Given the owner is logged into their account
    And the owner has an existing charging point at "Elm Street 3"
    When the owner selects "Set Status"
    And the owner sets the status to "Out of Service"
    Then the system updates the status of "Elm Street 3" to "Out of Service"
    And the owner receives a confirmation message "Status updated to Out of Service."
