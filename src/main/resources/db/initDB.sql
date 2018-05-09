DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);
