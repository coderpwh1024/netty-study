create table device
(
    id          bigint      not null,
    name        varchar(50) null,
    temperature double      null,
    humidity    double      null,
    time        bigint      null,
    constraint device_id_uindex
        unique (id)
);