Feature: Removing AC/DC Charging Points
  In order to manage charging points at my locations
  As an owner
  I want to remove AC/DC charging points from specific locations

  Scenario: Owner successfully removes an AC charging point
    Given the owner is logged in to remove charging points
    And the owner has an existing AC charging point at "Main Street 1"
    When the owner selects "Remove Charging Point" for "Main Street 1"
    And the owner confirms the removal
    Then the system removes the AC charging point from "Main Street 1"

  Scenario: Owner successfully removes a DC charging point
    Given the owner is logged in to remove charging points
    And the owner has an existing DC charging point at "Park Avenue 5"
    When the owner selects "Remove Charging Point" for "Park Avenue 5"
    And the owner confirms the removal
    Then the system removes the DC charging point from "Park Avenue 5"

