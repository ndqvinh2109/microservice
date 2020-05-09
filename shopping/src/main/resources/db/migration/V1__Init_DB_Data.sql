create table hibernate_sequence
(
    next_val bigint null
)
    engine = MyISAM;

create table product
(
    id          bigint       not null
        primary key,
    branch_name varchar(50)  null,
    color       varchar(20)  null,
    description varchar(255) null,
    name        varchar(50)  null,
    price       double       not null
)
    engine = MyISAM;

create table product_audit
(
    id                bigint      not null
        primary key,
    audit_action      varchar(20) null,
    audit_action_time datetime    null,
    audit_reference   varchar(20) null
)
    engine = MyISAM;

INSERT INTO shopping.hibernate_sequence (next_val) VALUES (1);

INSERT INTO product VALUES(1, 'ANKER', 'RED', 'CHARGER ANKER 10000', 'CHARGER ANKER 10000', '100');
INSERT INTO product VALUES(2, 'ANKER', 'BLACK', 'CHARGER ANKER 10000', 'CHARGER ANKER 10000', '100');
INSERT INTO product VALUES(3, 'ANKER', 'YELLO', 'CHARGER ANKER 10000', 'CHARGER ANKER 10000', '100');


INSERT INTO product VALUES(4, 'LG', 'BLACK', 'LG MONITOR X1000', 'MONITOR X1000', '110');
INSERT INTO product VALUES(5, 'LG', 'BLACK', 'LG MONITOR X2000', 'MONITOR X2000', '110');
INSERT INTO product VALUES(6, 'LG', 'BLACK', 'LG MONITOR X3000', 'MONITOR X3000', '110');

commit;

