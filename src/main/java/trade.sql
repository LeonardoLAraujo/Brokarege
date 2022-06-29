CREATE DATABASE trade;

USE trade;

--Table do trader (Comerciante)
create table trader(
    code int primary key auto_increment,
    nome varchar(25)
);

--Table do Papel (Empresa)
create table trade(
    idPapel int primary key auto_increment,
    nomePapel varchar(25)
);

--Table das Transações
create table orde(
    idOrder int primary key auto_increment,
    papel varchar(25) not null,
    trader varchar(25) not null,
    quantidade int not null,
    valor decimal(5,2)
);

--View do table View Final
create view v_final AS
select * from orde where valor=(quantidade*valor);