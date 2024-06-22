create table registro(
    id bigint not null auto_increment,
    condominio_id bigint not null,
    torre_id bigint not null,
    data_do_registro datetime not null,
    descricao_problema varchar(230) not null,
    tipo_problema varchar(100) not null,
    ativo tinyint not null,

    primary key(id),
    constraint fk_registro_condominio_id foreign key(condominio_id) references condominio(id),
    constraint fk_registro_torre_id foreign key(torre_id) references torre(id)

)