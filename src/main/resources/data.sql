--insert into users (id, enabled, last_login, name, password, username) values ('1', 'true', '2018-01-01', 'user1', 'pass', 'User Name');
insert into users (id, enabled, last_login, name, password, username) values ('1', 'true', '2018-01-01', 'User name', '$2a$10$mJTDXWzPafhpSTw8YaiPi.nwHEx2KQNUnUY1F.otA8romisSN6fBy', 'user1');
insert into authorities (username, authority) values ('user1', 'ROLE_USER');