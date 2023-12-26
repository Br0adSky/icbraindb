update human
set ethnos = null
where trim(ethnos) = '';

update human
set comment = null
where trim(comment) = '';

alter table test
    alter description drop not null;
update test
set description = null
where trim(description) = '';

update test
set alias = null
where trim(alias) = '';

update testsummary2test
set description = null
where trim(description) = '';

update testsummary2test
set description_en = null
where trim(description_en) = '';