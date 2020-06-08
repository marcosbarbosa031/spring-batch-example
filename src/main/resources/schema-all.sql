DROP TABLE Customer IF EXISTS;

CREATE TABLE Customer (
  customer_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
  first_name VARCHAR(20),
  last_name VARCHAR(20),
  balance DOUBLE(10,2) DEFAULT 0.00
);
