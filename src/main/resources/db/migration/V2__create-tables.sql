create table prod.calendar (
    id bigint primary key not null default(nextval('prod.calendar_seq')),
    date date unique not null
);

create table prod.client (
    id bigint primary key not null default(nextval('prod.client_seq')),
    name varchar(50),
    login varchar(20) unique not null,
    password varchar(32) not null,
    email varchar(35) unique
);

create table prod.place (
    id bigint primary key not null default(nextval('prod.place_seq')),
    name varchar(40),
    address varchar(80),
    active boolean
);

create table prod.master (
    id bigint primary key not null default(nextval('prod.master_seq')),
    name varchar(50) not null,
    patronymic varchar(25),
    level int,
    place_id bigint references prod.place(id),
    active boolean
);


create table prod.service (
    id bigint primary key not null default(nextval('prod.service_seq')),
    name varchar(40) not null,
    cost int not null,
    level int,
    active boolean
);

create table prod.record (
    id bigint primary key not null default(nextval('prod.record_seq')),
    timing varchar(20),
    status varchar(20),
    date date,
    client_id bigint references prod.client(id),
    service_id bigint references prod.service(id),
    master_id bigint references prod.master(id)
);

create table prod.master_calendar (
    master_id bigint references prod.master(id),
    calendar_id bigint references prod.calendar(id),
    constraint master_calendar_pk primary key(master_id, calendar_id)
);