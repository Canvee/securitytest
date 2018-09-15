--insert into users (id, enabled, last_login, name, password, username) values ('1', 'true', '2018-01-01', 'user1', 'pass', 'User Name');
insert into users (id, enabled, last_login, mail, password, username) 
	values ('1', 'true', '2018-01-01  00:00:00', 'user@mail.com', '$2a$10$mJTDXWzPafhpSTw8YaiPi.nwHEx2KQNUnUY1F.otA8romisSN6fBy', 'user1'); -- password user1
insert into users (id, enabled, last_login, mail, password, username) 
	values ('2', 'true', '2018-01-01  00:00:00', 'admin@mail.com', '$2a$11$1RhDuumrTjOXJyXw3I6ShOj5dNNfpKiuReaSxaC/uta2db1xGLHjW', 'admin'); -- password admin

insert into authorities (id, authority, appuser_id) values (1, 'ROLE_USER', 1);
insert into authorities (id, authority, appuser_id) values (2, 'ROLE_USER', 2);
insert into authorities (id, authority, appuser_id) values (3, 'ROLE_ADMIN', 2);	


insert into lists (listid, completed_date, created_date, name, value, appuser_id)
	values (1, '2001-02-16 20:38:40', '2001-02-16 20:38:40', 'shopping', 14.3, 1);
	
insert into items (itemid, amount, created_date, name, item_list_listid)
	values (1, 1, '2001-02-16 20:38:40', 'butter', 1);