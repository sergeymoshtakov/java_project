CREATE TABLE IF NOT EXISTS item_types (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS positions (
                                         id SERIAL PRIMARY KEY,
                                         name VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS menu_items (
                                          id SERIAL PRIMARY KEY,
                                          name_en VARCHAR(100) NOT NULL,
    name_de VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    item_type_id INT REFERENCES item_types(id)
    );

CREATE TABLE IF NOT EXISTS staff (
                                     id SERIAL PRIMARY KEY,
                                     first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15),
    email VARCHAR(255),
    position_id INT REFERENCES positions(id)
    );

CREATE TABLE IF NOT EXISTS customers (
                                         id SERIAL PRIMARY KEY,
                                         first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    phone_number VARCHAR(15),
    email VARCHAR(255),
    discount DECIMAL(5, 2) DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS staff_schedule (
                                              id SERIAL PRIMARY KEY,
                                              staff_id INT REFERENCES staff(id),
    work_day DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL
    );

CREATE TABLE IF NOT EXISTS orders (
                                      id SERIAL PRIMARY KEY,
                                      customer_first_name VARCHAR(100) NOT NULL,
    customer_last_name VARCHAR(100) NOT NULL,
    order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS order_items (
                                           id SERIAL PRIMARY KEY,
                                           order_id INT REFERENCES orders(id),
    menu_item_id INT REFERENCES menu_items(id),
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
    );
