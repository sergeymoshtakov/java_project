-- Таблица Статусы рейсов (statuses)
CREATE TABLE statuses (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL
);

-- Таблица Типы грузов (cargo_types)
CREATE TABLE cargo_types (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL
);

-- Таблица Пункты назначения (destinations)
CREATE TABLE destinations (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              name VARCHAR(100) NOT NULL
);

-- Таблица Водители (drivers)
CREATE TABLE drivers (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         first_name VARCHAR(50) NOT NULL,  -- Имя водителя
                         last_name VARCHAR(50) NOT NULL,   -- Фамилия водителя
                         experience INT NOT NULL,          -- Стаж
                         salary DECIMAL(10, 2) DEFAULT 0.00,
                         status_id INT,                    -- Внешний ключ на статус водителя
                         FOREIGN KEY (status_id) REFERENCES statuses(id)
);

-- Таблица Автомобили (cars)
CREATE TABLE cars (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      model VARCHAR(100) NOT NULL,
                      load_capacity DECIMAL(10, 2) NOT NULL, -- Грузоподъемность
                      status_id INT,                         -- Внешний ключ на статус автомобиля
                      is_broken BOOLEAN DEFAULT FALSE,       -- Состояние автомобиля
                      FOREIGN KEY (status_id) REFERENCES statuses(id)
);

-- Таблица Заявки на рейс (requests)
CREATE TABLE requests (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          destination_id INT,                   -- Внешний ключ на пункт назначения
                          cargo_type_id INT,                    -- Внешний ключ на тип груза
                          cargo_weight DECIMAL(10, 2) NOT NULL, -- Вес груза
                          driver_id INT,                        -- Внешний ключ на водителя
                          car_id INT,                           -- Внешний ключ на автомобиль
                          status_id INT,                        -- Внешний ключ на статус рейса
                          start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          end_time TIMESTAMP,
                          FOREIGN KEY (destination_id) REFERENCES destinations(id),
                          FOREIGN KEY (cargo_type_id) REFERENCES cargo_types(id),
                          FOREIGN KEY (driver_id) REFERENCES drivers(id),
                          FOREIGN KEY (car_id) REFERENCES cars(id),
                          FOREIGN KEY (status_id) REFERENCES statuses(id)
);

-- Таблица Ремонты (repairs)
CREATE TABLE repairs (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         car_id INT,                           -- Внешний ключ на автомобиль
                         driver_id INT,                        -- Внешний ключ на водителя
                         repair_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         is_fixed BOOLEAN DEFAULT FALSE,       -- Статус ремонта
                         FOREIGN KEY (car_id) REFERENCES cars(id),
                         FOREIGN KEY (driver_id) REFERENCES drivers(id)
);

-- Таблица Статистика рейсов (statistics)
CREATE TABLE statistics (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            driver_first_name VARCHAR(50) NOT NULL, -- Имя водителя
                            driver_last_name VARCHAR(50) NOT NULL,  -- Фамилия водителя
                            destination_id INT,                     -- Внешний ключ на пункт назначения
                            cargo_type_id INT,                      -- Внешний ключ на тип груза
                            cargo_weight DECIMAL(10, 2) NOT NULL,   -- Вес груза
                            income DECIMAL(10, 2) NOT NULL,         -- Выплата водителю за рейс
                            timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (destination_id) REFERENCES destinations(id),
                            FOREIGN KEY (cargo_type_id) REFERENCES cargo_types(id)
);
