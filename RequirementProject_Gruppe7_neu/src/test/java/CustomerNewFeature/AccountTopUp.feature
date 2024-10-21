Feature: Top Up Account
  In order to continue using charging services
  As a customer
  I want to top up my account using standard payment methods

  Scenario: Customer successfully tops up account with credit card
    Given the customer is on the "Top Up Account" page
    When the customer selects "Credit Card" as the payment method
    And the customer enters the amount to top up as €20.00
    And the customer enters valid credit card details:
      | field             | value                 |
      | cardNumber        | 1234 5678 9012 3456  |
      | expiryDate        | 12/25                |
      | cvv               | 123                  |
    And the customer submits the top-up request
    Then the system processes the payment
    And the new balance is updated to €70.00

  Scenario: Customer successfully tops up account with cash
    Given the customer is on the "Top Up Account" page
    When the customer selects "Cash" as the payment method
    And the customer enters the amount to top up as €50.00
    And the customer confirms the transaction
    Then the system processes the payment
    And the new balance is updated to €100.00

  Scenario: Customer successfully tops up account with ATM
    Given the customer is on the "Top Up Account" page
    When the customer selects "ATM" as the payment method
    And the customer enters the amount to top up as €30.00
    And the customer confirms the transaction
    Then the system processes the payment
    And the new balance is updated to €80.00


