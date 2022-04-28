CREATE SCHEMA if not exists grab;

CREATE TABLE if not exists grab.posts(
	id serial primary key,
	name varchar(150),
	text text,
	link varchar(250) UNIQUE,
	created timestamp
);