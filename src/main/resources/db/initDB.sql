DROP TABLE IF EXISTS voting;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
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

CREATE TABLE user_roles
(
  user_id         INTEGER                 NOT NULL,
  role            VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) MATCH FULL ON DELETE CASCADE
);

CREATE TABLE restaurants(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name            VARCHAR                 NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON users (name);

CREATE TABLE menu(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date            DATE                    NOT NULL,
  restaurant_id   INTEGER                 NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) MATCH FULL ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_unique_restaurant_date_idx ON menu (restaurant_id, date);

CREATE TABLE dishes(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name            VARCHAR                 NOT NULL,
  price           INTEGER                 NOT NULL,
  menu_id         INTEGER                 NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menu (id) MATCH FULL ON DELETE CASCADE,
  CONSTRAINT dishes_price_check CHECK (price <= 1000000 AND price >= 0)
);
CREATE UNIQUE INDEX dishes_unique_menu_name_idx ON dishes (menu_id, name);


CREATE TABLE voting(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  menu_id         INTEGER                 NOT NULL,
  user_id         INTEGER                 NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES public.menu (id) MATCH FULL ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES public.users (id) MATCH FULL ON DELETE CASCADE
);
CREATE UNIQUE INDEX voting_unique_menu_user_idx ON voting (menu_id, user_id);
