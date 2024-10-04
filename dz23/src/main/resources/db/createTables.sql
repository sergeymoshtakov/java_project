CREATE TABLE IF NOT EXISTS log_data (
                          id SERIAL PRIMARY KEY,
                          level VARCHAR(10) NOT NULL,
                          src TEXT NOT NULL,
                          message TEXT NOT NULL
);
