CREATE TABLE users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   name VARCHAR,
   email VARCHAR,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

INSERT
INTO
  users
  (name, email)
VALUES
  ('Ana da Silva', 'ana@gmail.com');