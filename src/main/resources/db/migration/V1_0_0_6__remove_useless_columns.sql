alter table test
    drop column alias;
alter table testsummary
    drop column description;

alter table testsummary
    alter column value set not null;

alter table mutation_snp
    alter column ref_nucl set not null;

alter table mutation_snp
    alter column position set not null;

alter table mutation_snp
    alter column mutation set not null;

alter table mutation_snp
    alter column type set not null;
