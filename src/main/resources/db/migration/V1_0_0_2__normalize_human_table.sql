-- создание необходимых в дальнейшем таблиц
create table districts
(
    id         bigint primary key generated always as identity,
    r_country  varchar(3) not null,
    r_district varchar(5),
    unique (r_district, r_country)
);

create table cities
(
    id         bigint primary key generated always as identity,
    r_district bigint references districts not null,
    r_city     varchar,
    unique (r_city, r_district)
);

create table nationalities
(
    id             bigint primary key generated always as identity,
    nationality    varchar not null unique,
    nationality_en varchar not null unique
);

create table human_nationalities
(
    id             bigint primary key generated always as identity,
    nationality_id bigint references nationalities not null,
    human_id       varchar references human
);

create table human_diseases
(
    id       bigint primary key generated always as identity,
    human_id varchar not null references human,
    disease  varchar not null,
    unique (human_id, disease)
);

-- очистка от пустых строк и заполнение соответствующих таблиц
update human
set r_country = null
where trim(r_country) = '';

update human
set r_district = null
where trim(r_district) = '';

update human
set r_city = null
where trim(r_city) = '';

-- для мигрантов добавляем новый атрибут, чтобы r_city был атомарный
alter table human
    add column if not exists is_migrant boolean not null default false;

-- like написан из предположения, что формат данных не изменился. Если это не так, его необходимо доработать с учетом нового формата
update human
set is_migrant = true
where r_city like '%мигрант%';

update human
set r_city = regexp_replace(r_city, '.мигрант', '')
where r_city like '%мигрант';

-- депрессию выносим в отдельную таблицу с болезнями (чтобы можно было добавить больше)
insert into human_diseases(human_id, disease)
select h.human, h.r_city
from human as h
where h.r_city = 'депрессия';

update human
set r_city = null
from human_diseases as d
where r_city = d.disease;

-- заполняем города, страны и области
insert into districts(r_district, r_country)
select distinct h.r_district, h.r_country
from human as h
where h.r_country is not null;

insert into cities (r_district, r_city)
select distinct d.id, h.r_city
from human h
         join districts d on h.r_country = d.r_country and h.r_district = d.r_district
where h.r_country is not null;

alter table human
    add column if not exists city bigint references cities;
update human h
set city = c.id
from cities c,
     districts d
where c.r_city = h.r_city
  and d.r_district = h.r_district
  and d.r_country = h.r_country;

alter table human
    drop column if exists r_city;
alter table human
    drop column if exists r_district;
alter table human
    drop column if exists r_country;

-- национальности
update human
set nationality = null
where trim(nationality) = '';
update human
set nationality_en = null
where trim(nationality_en) = '';

-- ВНИМАНИЕ! тут обнаружил артифакт -- для данного человека CO-Y4-INS-008 национальность на английском Бурят, хотя он русский из Новосибирска с европиодным этносом.
-- Если это не так, можно поменять nationality русский на бурят, необходимо чтобы данные (nationality и nationality_en) соответствовали,
-- чтобы не возникало коллизий с уникальностью
update human
set nationality_en = 'Russian'
where human = 'CO-Y4-INS-008';

insert into nationalities(nationality, nationality_en)
select distinct regexp_split_to_table(nationality, '/'),
                regexp_split_to_table(nationality_en, '/')
from human
where nationality != ''
  and nationality_en != '';
insert into human_nationalities (nationality_id, human_id)
select n.id, h.human
from human h,
     nationalities n
where n.nationality in (select unnest(regexp_split_to_array(h.nationality, '/')));

alter table human
    drop column if exists nationality_en;
alter table human
    drop column if exists nationality;












