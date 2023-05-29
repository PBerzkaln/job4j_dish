CREATE TABLE IF NOT EXISTS dish
(
    id          SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(100) UNIQUE,
    description VARCHAR(2000)
);