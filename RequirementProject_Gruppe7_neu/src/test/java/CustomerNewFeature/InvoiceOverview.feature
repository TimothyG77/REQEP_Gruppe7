Feature: Viewing Invoice After Charging Session
  In order to manage my charging expenses
  As a customer
  I want to view the invoice details after finishing a charging session

  Scenario: Customer views the invoice after completing a charging session
    Given the customer has completed a charging session at "Park Avenue 5"
    And the charging session details are as follows:
      | invoiceNumber |  INV123456         |
      | pricePerKWh  | €0.50              |
      | price         | €5.00              |
      | chargingPoint | "Park Avenue 5"    |
      | duration      | 1 hour             |
    When the customer navigates to the "Invoice" page
    Then the system displays the following invoice details:
      | field            | value               |
      | invoiceNumber    | INV123456          |
      | pricePerKWh      | €0.50              |
      | totalPrice       | €5.00              |
      | chargingPoint     | Park Avenue 5      |
      | duration         | 1 hour             |

    #############################################################
    #############################################################
    #########################Error-Case##########################
    #############################################################
    #############################################################

  Scenario: Customer attempts to view an invoice with invalid invoice number
    Given the customer has completed a charging session at "Park Avenue 5"
    When the customer navigates to the "Invoice" page
    Then the system prints the following error message "Invoice not found"


