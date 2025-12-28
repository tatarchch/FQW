create table prod.calendar (
    id bigint primary key not null default(nextval('prod.calendar_seq')),
    date date unique not null
);

create table prod.client (
    id bigint primary key not null default(nextval('prod.client_seq')),
    name varchar(225),
    login varchar(20) unique,
    password varchar(100),
    email varchar(50) unique,
    chat_id varchar(50) unique,
    role varchar(30)
);

create table prod.place (
    id bigint primary key not null default(nextval('prod.place_seq')),
    name varchar(40),
    address varchar(80) unique,
    active boolean
);

create table prod.master (
    id bigint primary key not null default(nextval('prod.master_seq')),
    name varchar(100) not null unique,
    level int not null,
    place_id bigint references prod.place(id),
    active boolean
);

create table prod.pet_service (
    id bigint primary key not null default(nextval('prod.service_seq')),
    name varchar(40) not null unique,
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
    pet_service_id bigint references prod.pet_service(id),
    master_id bigint references prod.master(id)
);

create table prod.master_calendar (
    master_id bigint references prod.master(id),
    calendar_id bigint references prod.calendar(id),
    constraint master_calendar_pk primary key(master_id, calendar_id)
);