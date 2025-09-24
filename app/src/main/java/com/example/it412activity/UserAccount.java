package com.example.it412activity;

public class UserAccount {
    private String emailAddress;
    private String password;
    private String lastName;
    private String firstName;
    private String mobileNo;

    // Constructors
    public UserAccount() {
        // No-arg constructor
    }

    public UserAccount(String emailAddress, String password, String lastName, String firstName, String mobileNo) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mobileNo = mobileNo;
    }

    // Getters and Setters
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    // toString for debugging/logging
    @Override
    public String toString() {
        return String.format(
                "UserAccount{emailAddress='%s', lastName='%s', firstName='%s', mobileNo='%s'}",
                emailAddress, lastName, firstName, mobileNo
        );
    }
}
