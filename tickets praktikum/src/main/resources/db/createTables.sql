CREATE TABLE IF NOT EXISTS Customer (
                                        id BIGSERIAL PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS Ticket (
                                      id BIGSERIAL PRIMARY KEY,
                                      cost DECIMAL(10, 2) NOT NULL,
    number INTEGER NOT NULL,
    customer_id BIGINT REFERENCES Customer(id),
    event_id BIGINT REFERENCES Event(id),
    status_id BIGINT REFERENCES Status(id)
    );

CREATE TABLE IF NOT EXISTS Status (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(50) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS Event (
                                     id BIGSERIAL PRIMARY KEY,
                                     event_date TIMESTAMP NOT NULL,
                                     name VARCHAR(100) NOT NULL,
    place_id BIGINT REFERENCES Place(id)
    );

CREATE TABLE IF NOT EXISTS Place (
                                     id BIGSERIAL PRIMARY KEY,
                                     address VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL
    );

-- Таблица Роли (roles)
CREATE TABLE IF NOT EXISTS roles (
                                     id BIGSERIAL PRIMARY KEY,           -- Уникальный идентификатор роли
                                     name VARCHAR(255) UNIQUE NOT NULL  -- Имя роли (например, ADMIN или USER)
    );

CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,             -- Уникальный идентификатор пользователя
                                     username VARCHAR(255) UNIQUE NOT NULL, -- Имя пользователя
    password VARCHAR(255) NOT NULL,       -- Пароль
    role_id INT,                          -- Внешний ключ на роль
    enabled BOOLEAN DEFAULT TRUE,         -- Статус пользователя (активен или нет)
    FOREIGN KEY (role_id) REFERENCES roles(id) -- Связь с таблицей roles
    );