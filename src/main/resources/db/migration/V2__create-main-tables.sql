create table prod.calendar (
    id bigint primary key not null default(nextval('prod.calendar_seq')),
    date date
);

create table prod.client (
    id bigint primary key not null default(nextval('prod.client_seq')),
    name varchar(20),
    surname varchar(30),
    patronymic varchar(25),
    phone_number varchar(20) unique
);

create table prod.master (
    id bigint primary key not null default(nextval('prod.master_seq')),
    name varchar(20),
    surname varchar(30),
    patronymic varchar(25),
    level int,
    active boolean
);

create table prod.place (
    id bigint primary key not null default(nextval('prod.place_seq')),
    name varchar(20),
    address varchar(40),
    active boolean
);

create table prod.service (
    id bigint primary key not null default(nextval('prod.service_seq')),
    name varchar(40),
    cost int,
    active boolean
);

create table prod.record (
    id bigint primary key not null default(nextval('prod.record_seq')),
    daily varchar(20),
    client_id bigint references prod.client(id),
    place_id bigint references prod.place(id),
    service_id bigint references prod.service(id),
    master_id bigint references prod.master(id),
    calendar_id bigint references prod.calendar(id)
);