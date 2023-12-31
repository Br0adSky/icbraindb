create table genes
(
    id         bigint primary key generated always as identity,
    chromosome varchar        not null,
    gene_name  varchar unique not null,
    unique (chromosome, gene_name)
);

-- ВНИМАНИЕ! для данной мутации обнаружено неверное значение -- должно быть LILRA6, а указано в базе GRIN2A
update mutation
set gene = 'LILRA6'
where id = 733884;

insert into genes(chromosome, gene_name)
select distinct m.chromosome, m.gene
from mutation m;

-- нужен ли тут unique констреинт?
create table mutation_snp
(
    id       bigint primary key generated always as identity,
    gene_id  bigint references genes not null,
    position bigint,
    ref_nucl varchar,
    mutation varchar,
    type     integer
);

-- запишем все уникальные SNP в отдельную таблицу -- всего записей в таблице mutation >900k,
-- в то время как уникальных SNP всего ~18_400
insert into mutation_snp(gene_id, position, ref_nucl, mutation, type)
select distinct g.id, m.position, m.ref_nucl, m.mutation, m.type
from mutation m
         join genes g on m.gene = g.gene_name;

alter table mutation
    add column snp_id bigint references mutation_snp;
update mutation m
set snp_id = snp.id
from mutation_snp snp
where m.position = snp.position
  and m.mutation = snp.mutation
  and m.type = snp.type
  and m.ref_nucl = snp.ref_nucl;

alter table mutation
    alter snp_id set not null;

alter table mutation
    drop if exists chromosome;
alter table mutation
    drop if exists mutation;
alter table mutation
    drop if exists ref_nucl;
alter table mutation
    drop if exists type;
alter table mutation
    drop if exists gene;
alter table mutation
    drop if exists position;

alter table mutation
    rename to human_mutations;








