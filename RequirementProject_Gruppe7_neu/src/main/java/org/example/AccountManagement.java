package org.example;

import java.util.List;

public abstract class AccountManagement {
    public String email;
    public String phoneNumber;
    public String password;
    public String paymentDetails;

    public AccountManagement(String email, String phoneNumber, String password, String paymentDetails) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.paymentDetails = paymentDetails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public abstract List<Location> viewAllLocations();

    public abstract List<InvoiceManagement> invoiceInformations();


    public abstract double getBalance();
}
