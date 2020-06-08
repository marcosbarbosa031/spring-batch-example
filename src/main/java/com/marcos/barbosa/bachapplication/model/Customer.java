package com.marcos.barbosa.bachapplication.model;

public class Customer {
  private String firstName;
  private String lastName;
  private Double balance;

  public Customer() {
  }

  public Customer(String firstName, String lastName, Double balance) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.balance = balance;
  }
  
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "firstName: "+ firstName + ", lastName: " + lastName;
  }

}
