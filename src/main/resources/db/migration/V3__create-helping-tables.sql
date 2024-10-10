create table prod.place_calendar (
    place_id bigint references prod.place(id),
    calendar_id bigint references prod.calendar(id),
    constraint place_calendar_pk primary key(place_id, calendar_id)
);

create table prod.master_calendar (
    master_id bigint references prod.master(id),
    calendar_id bigint references prod.calendar(id),
    constraint master_calendar_pk primary key(master_id, calendar_id)
);

create table prod.service_calendar (
    service_id bigint references prod.service(id),
    calendar_id bigint references prod.calendar(id),
    constraint service_calendar_pk primary key(service_id, calendar_id)
);

create table prod.service_master (
    service_id bigint references prod.service(id),
    master_id bigint references prod.master(id),
    constraint service_master_pk primary key(service_id, master_id)
);

create table prod.master_place (
    master_id bigint references prod.master(id),
    place_id bigint references prod.place(id),
    constraint master_place_pk primary key(master_id, place_id)
);

create table prod.place_service (
    place_id bigint references prod.place(id),
    service_id bigint references prod.service(id),
    constraint place_service_pk primary key(place_id, service_id)
);