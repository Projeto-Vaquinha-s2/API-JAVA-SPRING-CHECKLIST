create table torre(
    id bigint not null auto_increment,
    condominio_id bigint not null,
    numero_torre int not null,
    quantidade_andares int not null,
    quantidade_garagens int not null,
    quantidade_salao_de_festas int not null,
    quantidade_guaritas int not null,
    quantidade_terracos int not null,
    ativo tinyint not null,

    primary key(id),
    constraint fk_torre_condominio_id foreign key(condominio_id) references condominio(id)
)