create table condominio(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    cnpj varchar(14) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    quantidade_torres int not null,
    ativo tinyint not null,


    primary key(id)
)