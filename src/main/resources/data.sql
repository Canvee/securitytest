insert into users (id, enabled, last_login, name, password, username) values ('1', 'true', '2018-01-01', 'user1', '{noop}user', 'User Name');

insert into authorities (username, authority) values ('user1', 'ROLE_USER');