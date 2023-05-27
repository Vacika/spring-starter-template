create table cars
(
    id               bigint       not null auto_increment primary key,
    model            varchar(512),
    brand            varchar(128) not null,
    status           tinyint      not null,
    engine_type      tinyint      not null,
    manufacture_year datetime     not null,
    created_date     datetime     not null,
    modified_date    datetime,
    created_by       varchar(255),
    modified_by      varchar(255)
);