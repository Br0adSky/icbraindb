alter table human
    add column is_male boolean;

update human
set is_male = true
where sex = 1;

update human
set is_male = false
where sex = 2;

alter table human
    drop sex;

update human
set age = null
where age = 0;

alter table human alter column is_migrant drop not null;

update human
set is_migrant = null
where city is null;
