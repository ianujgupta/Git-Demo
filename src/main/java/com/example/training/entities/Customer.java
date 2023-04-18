package com.example.training.entities;

import javax.persistence.Entity;
import java.math.BigInteger;
import java.util.Objects;

public class Customer {

    private Integer LoginId;
    private String firstName;
    private String LastName;
    private String email;
    private String password;
    private BigInteger contactNumber;
    private String role;

    public Customer(Integer loginId, String firstName, String lastName, String email, String password, BigInteger contactNumber) {
        LoginId = loginId;
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.role=role;
    }

    public Integer getLoginId() {
        return LoginId;
    }

    public void setLoginId(Integer loginId) {
        LoginId = loginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigInteger getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(BigInteger contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "LoginId=" + LoginId +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNumber=" + contactNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer user = (Customer) o;
        return LoginId.equals(user.LoginId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(LoginId);
    }
}
