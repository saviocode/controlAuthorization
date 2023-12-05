create table paciente
(
    id    int primary key,
    name  varchar(50) not null,
    cpf   varchar(11),
    idade int8,
    sexo  char
);


create table procedimento
(
    id        int primary key,
    codigo    int8,
    name      varchar(50) not null,
    descricao varchar(50)
);

create table autorizacao
(
    id              int primary key,
    procedimento_id int8 not null,
    paciente_id     int8 not null,
    data            date,
    autorizado      boolean
);

create table controleAutorizacao
(

);

