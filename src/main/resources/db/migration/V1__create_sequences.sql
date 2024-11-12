create schema if not exists prod;

create sequence prod.calendar_seqINCREMENT by 1
START with 1
MINVALUE 1
MAXVALUE 1000000
CACHE 1;

create sequence prod.client_seq
INCREMENT by 1
START with 1
MINVALUE 1
MAXVALUE 1000000
CACHE 1;

create sequence prod.master_seq
INCREMENT by 1
START with 1
MINVALUE 1
MAXVALUE 1000000
CACHE 1;

create sequence prod.place_seqINCREMENT by 1
START with 1
MINVALUE 1
MAXVALUE 1000000
CACHE 1;

create sequence prod.record_seq
INCREMENT by 1
START with 1
MINVALUE 1
MAXVALUE 1000000
CACHE 1;

create sequence prod.service_seqINCREMENT by 1
START with 1
MINVALUE 1
MAXVALUE 1000000
CACHE 1;