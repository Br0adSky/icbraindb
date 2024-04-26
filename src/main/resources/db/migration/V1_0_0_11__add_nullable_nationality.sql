alter table nationalities
    alter column nationality drop not null;
alter table nationalities
    alter column nationality_en drop not null;
alter table nationalities
    add constraint chk_nationality check (nationality is not null or nationality_en is not null);

alter table human
    alter comment drop default;


