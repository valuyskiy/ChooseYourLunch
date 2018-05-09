DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', 'admin'),
  ('Администратор', 'admin1@mail.ru', 'admin'),
  ('User', 'user@google.ru', 'password'),
  ('Пользователь', 'user1@yandex.ru', 'password'),
  ('User 3', 'user3@yahoo.com', 'password');


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_USER', 100004);
