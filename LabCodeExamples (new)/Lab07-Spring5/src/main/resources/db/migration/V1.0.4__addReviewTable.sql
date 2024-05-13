create table review
(
    id      bigint auto_increment
        primary key,
    item_id bigint       null,
    user_id bigint       null,
    text    varchar(500) not null,
    rating  int          not null,
    constraint FK6hb6qqehnsm7mvfgv37m66hd7
        foreign key (item_id) references item (id),
    constraint FKiyf57dy48lyiftdrf7y87rnxi
        foreign key (user_id) references user (id)
);

