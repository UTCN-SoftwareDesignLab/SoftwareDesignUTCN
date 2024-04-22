create table item
(
    id          bigint auto_increment
        primary key,
    name        varchar(512) not null,
    description varchar(512) null
);

create table role
(
    id   int auto_increment
        primary key,
    name enum ('CUSTOMER', 'MANAGER', 'ADMIN') null
);

create table user
(
    id       bigint auto_increment
        primary key,
    username varchar(20)  not null,
    email    varchar(50)  not null,
    password varchar(120) not null,
    constraint UKob8kqyqqgmefl0aco34akdtpe
        unique (email),
    constraint UKsb8bbouer5wak8vyiiy4pf2bx
        unique (username)
);

create table user_roles
(
    role_id int    not null,
    user_id bigint not null,
    primary key (role_id, user_id),
    constraint FK55itppkw3i07do3h7qoclqd4k
        foreign key (user_id) references user (id),
    constraint FKrhfovtciq1l558cw6udg0h0d3
        foreign key (role_id) references role (id)
);

