-- Таблица Статусы (statuses)
CREATE TABLE IF NOT EXISTS statuses (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL
);

-- Таблица Типы грузов (cargo_types)
CREATE TABLE IF NOT EXISTS cargo_types (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL
);

-- Таблица Пункты назначения (destinations)
CREATE TABLE IF NOT EXISTS destinations (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              name VARCHAR(100) NOT NULL
);

-- Таблица Водители (drivers)
CREATE TABLE IF NOT EXISTS drivers (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         first_name VARCHAR(50) NOT NULL,  -- Имя водителя
                         last_name VARCHAR(50) NOT NULL,   -- Фамилия водителя
                         experience INT NOT NULL,          -- Стаж
                         salary DECIMAL(10, 2) DEFAULT 0.00,
                         status_id INT,                    -- Внешний ключ на статус водителя
                         FOREIGN KEY (status_id) REFERENCES statuses(id)
);

-- Таблица Автомобили (cars)
CREATE TABLE IF NOT EXISTS cars (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      model VARCHAR(100) NOT NULL,
                      load_capacity DECIMAL(10, 2) NOT NULL, -- Грузоподъемность
                      status_id INT,                         -- Внешний ключ на статус автомобиля
                      is_broken BOOLEAN DEFAULT FALSE,       -- Состояние автомобиля (исправна/сломана)
                      FOREIGN KEY (status_id) REFERENCES statuses(id)
);

-- Новая таблица для заявок, которые еще не распределены (pending_requests)
CREATE TABLE IF NOT EXISTS pending_requests (
                                  id INT PRIMARY KEY AUTO_INCREMENT,
                                  destination_id INT,                   -- Внешний ключ на пункт назначения
                                  cargo_type_id INT,                    -- Внешний ключ на тип груза
                                  cargo_weight DECIMAL(10, 2) NOT NULL, -- Вес груза
                                  status_id INT,                        -- Внешний ключ на статус заявки (ожидание, отменена)
                                  creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Время создания заявки
                                  FOREIGN KEY (destination_id) REFERENCES destinations(id),
                                  FOREIGN KEY (cargo_type_id) REFERENCES cargo_types(id),
                                  FOREIGN KEY (status_id) REFERENCES statuses(id)
);

-- Таблица Заявки на рейс (requests)
CREATE TABLE IF NOT EXISTS requests (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          destination_id INT,                   -- Внешний ключ на пункт назначения
                          cargo_type_id INT,                    -- Внешний ключ на тип груза
                          cargo_weight DECIMAL(10, 2) NOT NULL, -- Вес груза
                          driver_id INT,                        -- Внешний ключ на водителя
                          car_id INT,                           -- Внешний ключ на автомобиль
                          status_id INT,                        -- Внешний ключ на статус рейса (в процессе, выполнена)
                          start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Время начала рейса
                          end_time TIMESTAMP,                   -- Время завершения рейса
                          FOREIGN KEY (destination_id) REFERENCES destinations(id),
                          FOREIGN KEY (cargo_type_id) REFERENCES cargo_types(id),
                          FOREIGN KEY (driver_id) REFERENCES drivers(id),
                          FOREIGN KEY (car_id) REFERENCES cars(id),
                          FOREIGN KEY (status_id) REFERENCES statuses(id)
);

-- Таблица Ремонты (repairs)
CREATE TABLE IF NOT EXISTS repairs (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         car_id INT,                           -- Внешний ключ на автомобиль
                         driver_id INT,                        -- Внешний ключ на водителя
                         repair_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Время начала ремонта
                         is_fixed BOOLEAN DEFAULT FALSE,       -- Статус ремонта (завершен/незавершен)
                         FOREIGN KEY (car_id) REFERENCES cars(id),
                         FOREIGN KEY (driver_id) REFERENCES drivers(id)
);

-- Таблица Статистика рейсов (statistics)
CREATE TABLE IF NOT EXISTS statistics (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            driver_first_name VARCHAR(50) NOT NULL, -- Имя водителя
                            driver_last_name VARCHAR(50) NOT NULL,  -- Фамилия водителя
                            destination_id INT,                     -- Внешний ключ на пункт назначения
                            cargo_type_id INT,                      -- Внешний ключ на тип груза
                            cargo_weight DECIMAL(10, 2) NOT NULL,   -- Вес груза
                            income DECIMAL(10, 2) NOT NULL,         -- Выплата водителю за рейс
                            timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Время завершения рейса
                            FOREIGN KEY (destination_id) REFERENCES destinations(id),
                            FOREIGN KEY (cargo_type_id) REFERENCES cargo_types(id)
);
