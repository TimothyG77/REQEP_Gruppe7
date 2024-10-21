Feature: Managing Prices for Charging Locations
  In order to set and update pricing for charging services
  As an owner
  I want to set prices per location, per kWh, and for charging sessions

  Scenario: Owner successfully sets the price per kWh for a charging location
    Given the owner has an existing charging location at "Main Street 1"
    And the owner selects the charging location "Main Street 1"
    When the owner sets the price per kWh to €0.30
    Then the system updates the price per kWh for "Main Street 1"

  Scenario: Owner successfully sets the price for a charging session at a charging location
    Given the owner has an existing charging location at "Park Avenue 5"
    And the owner selects the charging location "Park Avenue 5"
    When the owner sets the price for charging sessions to €5.00
    Then the system updates the price for charging sessions at "Park Avenue 5"

      ############################################################
      ############################################################
      #######################Error-Case###########################
      ############################################################
      ############################################################

  Scenario: Owner sets the price for a non-existent charging location
    Given the owner is logged into their account
    When the owner selects the charging location "Unknown Street"
    Then the system displays following error message "Charging location not found"

