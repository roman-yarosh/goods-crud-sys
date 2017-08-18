CREATE TABLE products (
  id          INTEGER GENERATED BY DEFAULT AS IDENTITY,
  name        VARCHAR(256) DEFAULT NULL,
  manufacturer VARCHAR(256) DEFAULT NULL,
  description VARCHAR(256) DEFAULT NULL,
  PRIMARY KEY (id)
);