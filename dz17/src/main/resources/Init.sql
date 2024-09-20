CREATE DATABASE coffee_shop;

CREATE TABLE drinks (
                        id SERIAL PRIMARY KEY,
                        name_en VARCHAR(100) NOT NULL,
                        name_de VARCHAR(100) NOT NULL,
                        price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE desserts (
                          id SERIAL PRIMARY KEY,
                          name_en VARCHAR(100) NOT NULL,
                          name_de VARCHAR(100) NOT NULL,
                          price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE staff (
                       id SERIAL PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(15),
                       email VARCHAR(255),
                       position VARCHAR(50) NOT NULL
);

CREATE TABLE customers (
                           id SERIAL PRIMARY KEY,
                           full_name VARCHAR(255) NOT NULL,
                           birth_date DATE,
                           phone_number VARCHAR(15),
                           email VARCHAR(255),
                           discount DECIMAL(5, 2) DEFAULT 0
);

CREATE TABLE staff_schedule (
                                id SERIAL PRIMARY KEY,
                                staff_id INT REFERENCES staff(id),
                                work_day DATE NOT NULL,
                                start_time TIME NOT NULL,
                                end_time TIME NOT NULL
);

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        customer_id INT REFERENCES customers(id),
                        order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        total_amount DECIMAL(10, 2) NOT NULL
);

CREATE TABLE order_items (
                             id SERIAL PRIMARY KEY,
                             order_id INT REFERENCES orders(id),
                             item_type VARCHAR(50) NOT NULL,
                             item_id INT NOT NULL,
                             quantity INT NOT NULL,
                             price DECIMAL(10, 2) NOT NULL
);