package org.example;
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Customer() {}

    public Customer(int customerId, String firstName, String lastName, String email, String phone, String address) {
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) {
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public void setPhone(String phone) {
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }
        this.phone = phone;
    }

    public void setAddress(String address) { this.address = address; }

    public void printCustomerInfo() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }
}
