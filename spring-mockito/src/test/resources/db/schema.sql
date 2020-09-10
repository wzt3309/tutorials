CREATE TABLE IF NOT EXISTS t_user
(
    `id`           bigint primary key auto_increment not null,
    `gmt_create`   datetime                          not null default CURRENT_TIMESTAMP,
    `gmt_modified` datetime                          not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `name`         varchar(128)                      not null
);