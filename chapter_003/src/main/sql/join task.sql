--Задание:
--1. Создать таблицы и заполнить их начальными данными
--2. Выполнить запросы с left, rigth, full, cross соединениями
--3. Используя left join найти работников, которые не относятся ни к одну из департаментов
--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары

--1
create table departments (
                             id serial primary key,
                             name varchar(255)
);

create table employees(
                          id serial primary key,
                          name varchar(255),
                          dep_id int references departments(id)
);

insert into departments (name) values ('dep1');
insert into departments (name) values ('dep2');
insert into departments (name) values ('dep3');
insert into departments (name) values ('dep4');

insert into employees (name, dep_id) values ('employee1', 1);
insert into employees (name, dep_id) values ('employee2', 2);
insert into employees (name, dep_id) values ('employee3', 1);
insert into employees (name) values ('employee4');
insert into employees (name, dep_id) values ('employee5', 3);
insert into employees (name, dep_id) values ('employee6', 1);

--2
select * from departments d left join employees e on d.id = e.dep_id;

select * from departments d right join employees e on d.id = e.dep_id;

select * from departments d full join employees e on d.id = e.dep_id;

select * from departments cross join employees;

--3
select * from employees e left join departments d on e.dep_id = d.id where d.id is null;

--4
select d.id, d.name, e.id, e.name from departments d left join employees e on d.id = e.dep_id;
select d.id, d.name, e.id, e.name from employees e right join departments d on e.dep_id = d.id;

select d.id, d.name, e.id, e.name from employees e left join departments d on e.dep_id = d.id;
select d.id, d.name, e.id, e.name from departments d right join employees e on d.id = e.dep_id;

--5
create table teens (
                       name varchar(255),
                       gender varchar(1)
);

insert into teens (name, gender) values ('Tom', 'M');
insert into teens (name, gender) values ('James', 'M');
insert into teens (name, gender) values ('David', 'M');
insert into teens (name, gender) values ('Emma', 'F');
insert into teens (name, gender) values ('Sophia', 'F');
insert into teens (name, gender) values ('Olivia', 'F');

select distinct t1.name, t2.name, t1.gender, t2.gender
from teens t1 cross join teens t2
where t1.gender != t2.gender;