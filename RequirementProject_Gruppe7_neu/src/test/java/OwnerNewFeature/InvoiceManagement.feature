Feature: Managing and Viewing Invoices
  In order to review charging session expenses
  As an owner
  I want to choose an invoice number and view a detailed invoice list

  Scenario: Owner chooses an invoice number to view details
    Given the system has the following invoices:
      | invoiceNumber | location      | chargingPoint | chargingMode | duration   | chargedEnergy | price   |
      | INV123456     | Main Street 1 | AC            | AC           | 1 hour    | 10 kWh       | €3.00  |
      | INV123457     | Park Avenue 5 | DC            | DC           | 30 minutes | 5 kWh        | €2.50  |
    And the owner is logged in
    When the owner selects invoice number "INV123456"
    Then the system displays the details for invoice "INV123456":
      | field            | value            |
      | location         | Main Street 1    |
      | chargingPoint    | AC                |
      | chargingMode     | AC                |
      | duration         | 1 hour           |
      | chargedEnergy    | 10 kWh           |
      | price            | €3.00            |

  Scenario: Owner views the invoice list with all details
    Given the system has the following invoices:
      | invoiceNumber | location      | chargingPoint | chargingMode | duration   | chargedEnergy | price   |
      | INV123456     | Main Street 1 | AC            | AC           | 1 hour    | 10 kWh       | €3.00  |
      | INV123457     | Park Avenue 5 | DC            | DC           | 30 minutes | 5 kWh        | €2.50  |
    When the owner navigates to the "Invoice List" page
    Then the system displays the following invoice list:
      | invoiceNumber | location      | chargingPoint | chargingMode | duration   | chargedEnergy | price   |
      | INV123456     | Main Street 1 | AC            | AC           | 1 hour    | 10 kWh       | €3.00  |
      | INV123457     | Park Avenue 5 | DC            | DC           | 30 minutes | 5 kWh        | €2.50  |
