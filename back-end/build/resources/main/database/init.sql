DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS book CASCADE;
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    alias    VARCHAR(200),
    password VARCHAR(200)
);
CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 4 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS book
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(200),
    author      VARCHAR(200),
    genre       VARCHAR(200),
    description VARCHAR(500),
    image       VARCHAR(200),
    available   Boolean,
    user_id     int,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE SEQUENCE IF NOT EXISTS book_seq START WITH 31 INCREMENT BY 1;
