create table diseases
(
    id      bigint primary key generated always as identity,
    disease varchar not null unique
);

insert into diseases (disease)
values ('депрессия');

alter table human_diseases
    add column disease_id bigint references diseases (id);

update human_diseases hd
set disease_id = d.id
from diseases d
where hd.disease = d.disease;

alter table human_diseases
    drop column disease;
alter table human_diseases
    alter disease_id set not null;


