create table hibernate_sequence
(
    next_val bigint null
);

create table item
(
    id           bigint auto_increment
        primary key,
    date_created datetime      null,
    description  varchar(1024) null,
    name         varchar(512)  not null
);

create table role
(
    id   int auto_increment
        primary key,
    name varchar(20) null
);

create table user
(
    id       bigint       not null
        primary key,
    email    varchar(50)  not null,
    password varchar(120) not null,
    username varchar(20)  not null,
    constraint UKob8kqyqqgmefl0aco34akdtpe
        unique (email),
    constraint UKsb8bbouer5wak8vyiiy4pf2bx
        unique (username)
);

create table review
(
    id      bigint       not null
        primary key,
    rating  int          null,
    text    varchar(255) null,
    item_id bigint       null,
    user_id bigint       null,
    constraint FK6hb6qqehnsm7mvfgv37m66hd7
        foreign key (item_id) references item (id),
    constraint FKiyf57dy48lyiftdrf7y87rnxi
        foreign key (user_id) references user (id)
);

create table user_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    constraint FK55itppkw3i07do3h7qoclqd4k
        foreign key (user_id) references user (id),
    constraint FKrhfovtciq1l558cw6udg0h0d3
        foreign key (role_id) references role (id)
);

