-- creating db start

CREATE DATABASE items_db;

--creating db end

\c items_db;

--creating tables start

CREATE TABLE state (
                       id SERIAL PRIMARY KEY,
                       state VARCHAR(20)
);

CREATE TABLE category (
                          id SERIAL PRIMARY KEY,
                          category VARCHAR(20)
);

CREATE TABLE role (
                      id SERIAL PRIMARY KEY,
                      role VARCHAR(255)
);

CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        email VARCHAR(50),
                        role_id INT REFERENCES role(id)
);

CREATE TABLE rule (
                      id SERIAL PRIMARY KEY,
                      rule VARCHAR(255)
);

CREATE TABLE role_rules (
                            id SERIAL PRIMARY KEY,
                            role_id INT REFERENCES role(id),
                            rule_id INT REFERENCES rule(id)
);

CREATE TABLE item (
                      id SERIAL PRIMARY KEY,
                      theme VARCHAR(255),
                      description TEXT,
                      state_id INT REFERENCES state(id),
                      category_id INT REFERENCES category(id),
                      author_id INT REFERENCES "user"(id)
);

CREATE TABLE attaches (
                          id SERIAL PRIMARY KEY,
                          file BYTEA,
                          item_id INT REFERENCES item(id)
);

CREATE TABLE comments (
                          id SERIAL PRIMARY KEY,
                          msg TEXT,
                          item_id INT REFERENCES item(id)
);

--creating tables end

--inserting data into tables start

INSERT INTO state (state) VALUES ('created');
INSERT INTO state (state) VALUES ('active');
INSERT INTO state (state) VALUES ('suspended');
INSERT INTO state (state) VALUES ('refused');
INSERT INTO state (state) VALUES ('closed');

INSERT INTO category (category) VALUES ('category1');
INSERT INTO category (category) VALUES ('category2');
INSERT INTO category (category) VALUES ('category3');

INSERT INTO role (role) VALUES ('user');
INSERT INTO role (role) VALUES ('admin');

INSERT INTO "user" (name, email, role_id) VALUES
('user1', 'email1@gmail.com',(SELECT id FROM role WHERE role = 'user'));

INSERT INTO "user" (name, email, role_id) VALUES
('admin', 'adm@gmail.com',(SELECT id FROM role WHERE role = 'admin'));

INSERT INTO rule (rule) VALUES ('item_create');
INSERT INTO rule (rule) VALUES ('item_read');
INSERT INTO rule (rule) VALUES ('item_update');
INSERT INTO rule (rule) VALUES ('item_delete');

INSERT INTO role_rules (role_id, rule_id)
VALUES ((SELECT id FROM role WHERE role = 'user'),
        (SELECT id FROM rule WHERE rule = 'item_create'));

INSERT INTO role_rules (role_id, rule_id)
VALUES ((SELECT id FROM role WHERE role = 'user'),
        (SELECT id FROM rule WHERE rule = 'item_read'));

INSERT INTO role_rules (role_id, rule_id)
VALUES ((SELECT id FROM role WHERE role = 'admin'),
        (SELECT id FROM rule WHERE rule = 'item_read'));

INSERT INTO role_rules (role_id, rule_id)
VALUES ((SELECT id FROM role WHERE role = 'admin'),
        (SELECT id FROM rule WHERE rule = 'item_create'));

INSERT INTO role_rules (role_id, rule_id)
VALUES ((SELECT id FROM role WHERE role = 'admin'),
        (SELECT id FROM rule WHERE rule = 'item_update'));

INSERT INTO role_rules (role_id, rule_id)
VALUES ((SELECT id FROM role WHERE role = 'admin'),
        (SELECT id FROM rule WHERE rule = 'item_delete'));

INSERT INTO item (theme, description, state_id, category_id, author_id)
VALUES  ('Item 1', 'Test item - 1', (SELECT id FROM state WHERE state = 'created'),
         (SELECT id FROM category WHERE category = 'category1'),
         (SELECT id FROM "user" WHERE name = 'user1'));

INSERT INTO attaches (file, item_id) VALUES (pg_read_binary_file('c:\projects\job4j_design\chapter_003\data\test.JPG')::bytea,
                                             (SELECT id FROM item WHERE theme ='Item 1'));

INSERT INTO comments (msg, item_id) VALUES ('This is a test comment..',
                                            (SELECT id FROM item WHERE theme ='Item 1'));

--inserting data into tables end