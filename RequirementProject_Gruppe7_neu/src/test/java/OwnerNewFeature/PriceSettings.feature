Feature: Managing Prices for Charging Locations
  In order to set and update pricing for charging services
  As an owner
  I want to set prices per location, per kWh, and for charging sessions

  Scenario: Owner successfully sets the price per kWh for a charging location
    Given the owner is logged into their account
    And the owner has an existing charging location at "Main Street 1"
    When the owner selects "Set Prices"
    And the owner sets the price per kWh to €0.30
    Then the system updates the price per kWh for "Main Street 1"
    And the owner receives a confirmation message "Price per kWh updated successfully to €0.30."

  Scenario: Owner successfully sets the price for a charging session at a charging location
    Given the owner is logged into their account
    And the owner has an existing charging location at "Park Avenue 5"
    When the owner selects "Set Prices"
    And the owner sets the price for charging sessions to €5.00
    Then the system updates the price for charging sessions at "Park Avenue 5"
    And the owner receives a confirmation message "Price for charging sessions updated successfully to €5.00."