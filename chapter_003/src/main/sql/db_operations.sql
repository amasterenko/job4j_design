CREATE DATABASE test_db;

/c test_db

CREATE TABLE tools (
	id serial primary key,
	name varchar(20),
	weight real,
	used boolean
);

INSERT INTO tools (name, weight, used) values ('screwdriver', 120.5, true);

UPDATE tools set weight = 150, used = false;

DELETE from tools;