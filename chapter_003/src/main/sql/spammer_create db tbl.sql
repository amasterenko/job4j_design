--Creation database and tables for importing spammers(class ImportDB)
create database spammer;

\c spammer;

create table users (
                       id serial primary key,
                       name varchar(255),
                       email varchar(255)
);