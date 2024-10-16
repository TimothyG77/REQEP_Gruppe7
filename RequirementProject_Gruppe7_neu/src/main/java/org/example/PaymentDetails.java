package org.example;

public class PaymentDetails {
    private String cardNumber;
    private String expiryDate;
    private int cvv;
    private double cashAmount;
    private String atmTransactionId;


    public PaymentDetails(String cardNumber, String expiryDate, int cvv, double cashAmount, String atmTransactionId) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cashAmount = cashAmount;
        this.atmTransactionId = atmTransactionId;
    }


    public PaymentDetails(double cashAmount, String atmTransactionId) {
        this.cashAmount = cashAmount;
        this.atmTransactionId = atmTransactionId;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getAtmTransactionId() {
        return atmTransactionId;
    }

    public void setAtmTransactionId(String atmTransactionId) {
        this.atmTransactionId = atmTransactionId;
    }
}
