CREATE SEQUENCE pesquisa_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE questionario_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE pesquisa(
  codigo serial not null,
  questionado character varying(255),
  campus character varying(255),
  curso character varying(255),
  CONSTRAINT pk_pesquisa PRIMARY KEY (codigo)
);

CREATE TABLE questionario(
  codigo serial not null,
  cod_pesquisa int not null,
  pergunta character varying(255),
  conceito character varying(255),
  cod_pergunta serial,
  CONSTRAINT pk_questionario PRIMARY KEY (codigo, cod_pesquisa),
  CONSTRAINT fk_pesquisa FOREIGN KEY (cod_pesquisa)
      REFERENCES pesquisa (codigo) MATCH SIMPLE
      ON DELETE CASCADE
);


ALTER TABLE pesquisa OWNER TO spaadmin;

ALTER TABLE questionario OWNER TO spaadmin;