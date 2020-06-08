package com.marcos.barbosa.bachapplication;

import com.marcos.barbosa.bachapplication.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

  private static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

  @Override
  public Customer process(final Customer Customer) throws Exception {
    final String firstName = Customer.getFirstName().toUpperCase();
    final String lastName = Customer.getLastName().toUpperCase();
    final Double balance = Customer.getBalance();

    final Customer transformedCustomer = new Customer(firstName, lastName, balance);

    log.info("Converting (" + Customer + ") into (" + transformedCustomer + ")");

    return transformedCustomer;
  }

}