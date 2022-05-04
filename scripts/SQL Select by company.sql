drop table IF EXISTS company, person;

drop table IF EXISTS countPersons, maxCountPersons;

CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company(id, name) values(1, 'Рога и копыта'), (2, 'Ромашка'), (3, 'Завод стали'), (4, 'Фабрика мечты'), (5, 'Спорттовары');

INSERT INTO person(id, name, company_id) values
(1,'Фадеев Ростислав Еремеевич', '1'),
(2,'Меркушев Кондрат Тимурович', '1'),
(3,'Красильников Виталий Сергеевич', '1'),
(4,'Савин Аркадий Николаевич', '2'),
(5,'Котов Осип Альбертович', '2'),
(6,'Горбунов Лев Артёмович', '2'),
(7,'Селезнёв Витольд Яковлевич', '2'),
(8,'Быков Моисей Федосеевич', '2'),
(9,'Дьячков Илларион Романович', '2'),
(10,'Тетерин Леонтий Макарович', '3'),
(11,'Никонов Михаил Георгиевич', '3'),
(12,'Костин Лавр Кириллович', '3'),
(13,'Марков Мирослав Геннадьевич', '3'),
(14,'Баранов Эрнест Рудольфович', '3'),
(15,'Комаров Иннокентий Тимурович', '4'),
(16,'Моисеев Богдан Аркадьевич', '4'),
(17,'Горбунов Сергей Георгиевич', '4'),
(18,'Суворов Климент Геннадиевич', '4'),
(19,'Баранов Павел Семёнович', '5'),
(20,'Никифоров Ким Геннадьевич', '5'),
(21,'Иванов Иван Иванович', '3')
;

/*В одном запросе получить
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.*/
select 
	person.id,
	person.name, 
	company.name as org
from 
	person 
	join company 
		on person.company_id = company.id
where 
	company_id != 5
;

/*Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании
(нужно учесть, что таких компаний может быть несколько)*/
select 
	company.name,
	count(person.id) as count
INTO countPersons
from 
	person 
	join company 
		on person.company_id = company.id
group by
	company.name
;
select 
	max(count) as count
INTO maxCountPersons
from 
	 countPersons
;
select
	countPersons.name,
	countPersons.count
from
	countpersons
where
	countpersons.count in (select 6);
		