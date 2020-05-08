create table person
(
    id       serial not null,
    login    varchar(255),
    password varchar(255),
    primary key (id)
);

insert into person (login, password)
values ('parsentev', '123');
insert into person (login, password)
values ('ban', '123');
insert into person (login, password)
values ('ivan', '123');