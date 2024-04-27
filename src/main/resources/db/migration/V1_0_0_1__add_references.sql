-- добавление ссылки на human в таблице eeg_file
update eeg_file
set human = null
where trim(human) = '';
alter table eeg_file
    ADD CONSTRAINT eeg_file_human_fk FOREIGN KEY (human) references human (human);

-- правка опечатки
alter table testsummary2test
    rename column testsammaryalias to testsummaryalias;

-- добавление ссылки на test у testsummary2test
alter table testsummary2test
    add constraint testsummary2test_test_fk foreign key (test) references test (test);

-- добавление ссылки на testsummary2test
alter table testsummary
    add column summary_id integer;

update testsummary as ts
set summary_id = ts2t.id
from testsummary2test as ts2t
where alias = ts2t.testsummaryalias;

alter table testsummary
    alter column summary_id set not null;
alter table testsummary
    add constraint testsummary_testsummary2test_fk foreign key (summary_id) references testsummary2test (id);
alter table testsummary
    drop column test;
alter table testsummary
    drop column alias;


-- добавление ссылки на test у testresponsetype
alter table testresponsetype
    add column test integer;

update testresponsetype
set testresponsetype = 'Тест ведущей руки'
where testresponsetype = 'тест ведущей руки';

update testresponsetype
set test = t.test
from test as t
where testresponsetype = t.name;

alter table testresponsetype
    alter column test set not null;
alter table testresponsetype
    add constraint testresponsetype_test_fk foreign key (test) references test (test);
alter table testresponsetype
    drop column testresponsetype;

-- убирание ненужной ссылки на testresponsetype у testquestion
alter table testquestion
    drop column testresponsetype;


