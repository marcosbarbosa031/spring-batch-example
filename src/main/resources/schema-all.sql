DROP TABLE customer IF EXISTS;

CREATE TABLE customer  (
    customer_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    balance DOUBLE(10,2) DEFAULT 0.00
);
