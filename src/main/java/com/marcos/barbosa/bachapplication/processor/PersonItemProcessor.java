package com.marcos.barbosa.bachapplication.processor;

import com.marcos.barbosa.bachapplication.model.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Customer, Customer> { 
  private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

  @Override
  public Customer process(final Customer customer) throws Exception {
    final String firstName = customer.getFirstName().toUpperCase();
    final String lastName = customer.getLastName().toUpperCase();
    final Double balance = customer.getBalance();

    final Customer transformedPerson = new Customer(firstName, lastName, balance);

    log.info("Converting (" + customer + ") into (" + transformedPerson + ")");

    return transformedPerson;
  }
}