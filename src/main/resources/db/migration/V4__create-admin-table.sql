create sequence prod.admin_seq
increment by 1
start with 1
minvalue 1
maxvalue 1000000
cache 1;

create table prod.admin(
    id bigint primary key not null default(nextval('prod.admin_seq')),
    name varchar(225) not null,
    password varchar(30) not null,
    login varchar(30) not null
);

insert into prod.admin(name, login, password) values
('Ivanov Nikita Alekseevich', 'admin', 'admin');
