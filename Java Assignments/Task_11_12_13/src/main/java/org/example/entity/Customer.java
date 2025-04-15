package org.example.entity;

public class Customer {
    private long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Customer() {}

    public Customer(long customerId, String firstName, String lastName, String email, String phone, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "Customer ID: " + customerId + ", Name: " + firstName + " " + lastName + ", Email: " + email + ", Phone: " + phone + ", Address: " + address;
    }
}
