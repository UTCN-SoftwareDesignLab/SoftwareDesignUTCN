create table if not exists item_details
(
    id      bigint auto_increment,
    details varchar(1024) not null,
    primary key (id)
);

alter table spring1.item
    add item_details bigint not null default 0;

alter table spring1.item
    add constraint fk_details_id foreign key (item_details) references item_details (id);


DROP PROCEDURE IF EXISTS DETAILS_MIG;
DELIMITER ;;
CREATE PROCEDURE DETAILS_MIG()
BEGIN
    DECLARE n INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    SELECT COUNT(*) FROM item INTO n;
    SET i = 0;
    WHILE i < n
        DO
            INSERT INTO item_details(details) SELECT details FROM item it LIMIT i,1;
            update spring1.item set item_details = LAST_INSERT_ID() where id = it.id;
            SET i = i + 1;
        END WHILE;
End;
;;

alter table spring1.item
    drop column `details`;