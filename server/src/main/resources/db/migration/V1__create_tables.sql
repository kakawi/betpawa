create table user
(
    id   int primary key,
    name varchar(128) not null,
    constraint table_name_name_uindex
        unique (name)
);

create table wallet
(
    id       int auto_increment
        primary key,
    amount   int        not null,
    currency varchar(3) not null,
    user_id  int        not null,
    constraint wallet_u1
        unique (user_id, currency),
    constraint wallet_user_id_fk
        foreign key (user_id) references user (id)
);

