--1
--Creating tables
create table auto_body (
                           id serial primary key,
                           model varchar(50),
                           doors int,
                           seats int,
                           color varchar(50),
                           weight float
);

create table engine (
                        id serial primary key,
                        model varchar(50),
                        manufacturer varchar(50),
                        type varchar(50),
                        power float,
                        size float,
                        weight float
);

create table gearbox (
                         id serial primary key,
                         model varchar(50),
                         manufacturer varchar(50),
                         type varchar(50),
                         gears int,
                         weight float
);

create table car (
                     id serial primary key,
                     model varchar(50),
                     body_id int not null references auto_body(id),
                     engine_id int not null references engine(id),
                     gearbox_id int not null references gearbox(id)
);

--filling the tables
insert into auto_body (model, doors, seats, color, weight) values ('sedan', 4, 4, 'black', 350);
insert into auto_body (model, doors, seats, color, weight) values ('coupe', 2, 2, 'black', 250);
insert into auto_body (model, doors, seats, color, weight) values ('universal', 5, 5, 'black', 380);
insert into auto_body (model, doors, seats,color, weight) values ('roadster', 2, 2, 'red', 210);

insert into engine (model, manufacturer, type, power, size, weight) values ('V3S10-G345', 'Mitsubishi', 'petrol', 110, 2.4, 90);
insert into engine (model, manufacturer, type, power, size, weight) values ('BM130-3', 'BMW', 'petrol', 140, 2.8, 88);
insert into engine (model, manufacturer, type, power, size, weight) values ('BVD23d44', 'Perkins', 'diesel', 190, 2.4, 85);

insert into gearbox (model, manufacturer, type, gears, weight) values ('GB1190tr', 'ZF', 'Mechanic', 4, 30);
insert into gearbox (model, manufacturer, type, gears, weight) values ('TRE345', 'Jatco', 'Auto', 5, 35);
insert into gearbox (model, manufacturer, type, gears, weight) values ('MF56R', 'Aisin', 'CVT', 5, 38);

insert into car (model, body_id, engine_id, gearbox_id) values ('car model1', 1, 1, 2);
insert into car (model, body_id, engine_id, gearbox_id) values ('car model2', 3, 2, 1);

--requests
--1
select c.model, ab.model, e.model, g.model from car c
    left join auto_body ab
        on c.body_id = ab.id
    left join engine e
        on c.body_id = e.id
    left join gearbox g
        on c.body_id = g.id
;

--2
select ab.id, ab.model, 'auto body' as type_of_part from auto_body ab
    left join car c
        on ab.id = c.body_id where c.id is null
union
select e.id, e.model, 'engine' as type_of_part from engine e
    left join car c
        on e.id = c.engine_id where c.id is null
union
select g.id, g.model, 'gearbox' as type_of_part from gearbox g
    left join car c
        on g.id = c.gearbox_id where c.id is null