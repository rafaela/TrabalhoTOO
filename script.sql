CREATE TABLE conceito(
  codigo serial not null,
  nome character varying(255),
  peso int,
  CONSTRAINT pk_conceito PRIMARY KEY (codigo)
);
ALTER TABLE conceito OWNER TO spaadmin;


insert into conceito values (1, 'Não conheço', 0);
insert into conceito values (2, 'Inexistente', 0);
insert into conceito values (3, 'Péssimo', 1);
insert into conceito values (4, 'Ruim', 2);
insert into conceito values (5, 'Satisfatório', 3);
insert into conceito values (6, 'Bom', 4);
insert into conceito values (7, 'Ótimo', 5);


CREATE TABLE titulo_pesquisa(
  codigo serial not null,
  nome character varying(255),
  hash character varying(255),
  CONSTRAINT pk_t_pesquisa PRIMARY KEY (codigo)
);
ALTER TABLE titulo_pesquisa OWNER TO spaadmin;


CREATE TABLE pesquisa(
  codigo serial not null,
  codigo_pesquisa int,
  questionado character varying(255),
  campus character varying(255),
  curso character varying(255),
  CONSTRAINT pk_pesquisa PRIMARY KEY (codigo),
  CONSTRAINT fk_pesquisa FOREIGN KEY (codigo_pesquisa)
  REFERENCES titulo_pesquisa (codigo) MATCH SIMPLE
      ON DELETE CASCADE
);
ALTER TABLE pesquisa OWNER TO spaadmin;


CREATE TABLE questionario(
  codigo serial not null,
  cod_pesquisa int,
  pergunta character varying(255),
  tipo_pergunta character varying(255),
  cod_conceito int,
  CONSTRAINT pk_questionario PRIMARY KEY (codigo),
  CONSTRAINT fk_pesquisa FOREIGN KEY (cod_pesquisa)
      REFERENCES pesquisa (codigo) MATCH SIMPLE
      ON DELETE CASCADE
);
ALTER TABLE questionario OWNER TO spaadmin;


create type quantidade_curso as (curso character varying(255), quantidade int);

create or replace function qtd_curso(nomeP character varying) returns setof quantidade_curso as $$
declare
   tabela quantidade_curso%rowtype;
   i int;

begin
   for tabela in select distinct curso, count(curso) as qtd from pesquisa inner join titulo_pesquisa
      on pesquisa.codigo_pesquisa=titulo_pesquisa.codigo
      where curso != '' and nomeP like titulo_pesquisa.nome group by curso loop
     return next tabela;
   end loop; 
end
$$ language 'plpgsql'

select * from qtd_curso('Pesquisa SPA');


create type qtd_questionados as (questionado character varying(255), quantidade int);

create or replace function qtd_questionado(nomeP character varying(255)) returns setof qtd_questionados as $$
declare
   tabela qtd_questionados%rowtype;
   i int;
begin
   for tabela in select distinct questionado, count(questionado) from 
  pesquisa inner join titulo_pesquisa on pesquisa.codigo_pesquisa=titulo_pesquisa.codigo
  and nomeP like titulo_pesquisa.nome group by questionado loop
     return next tabela;
   end loop;
end
$$ language 'plpgsql'


select * from qtd_questionado('Pesquisa SPA');
 