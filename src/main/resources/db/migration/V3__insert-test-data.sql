insert into prod.calendar (date) values
('2025-03-08'),
('2025-03-09'),
('2025-03-10'),
('2025-03-11'),
('2025-03-12');

insert into prod.client (name, login, password, email, role) values
('Viktor Samohin', 'user', '$2a$05$sfy7csYJVFnb.pyn9CSdsudL2PA1cGtifOfZomhZA/U4/BIpU7siS', 'user@mail.ru', 'USER'),
('Ivanov Nikita Alekseevich', 'admin', '$2a$11$Ajlqx/pZ6zYJqgSSP4EdruQUIKwhmjVcLPLId13aavwjd8p8QTama', 'admin@mail.ru', 'ADMIN');

insert into prod.place (name, address, active) values
('Animal House', 'Pravdi 24', true),
('Everything for animals', 'Tverskaya 1', true);

insert into prod.master (name, level, place_id, active) values
('Alexander Sulikov Alekseevich', 1, 1, true),
('Stan Smith', 2, 2, true),
('Vladimir Petrov Nikolaevich', 3, 1, true);

insert into prod.pet_service (name, cost, level, active) values
('massage', 200, 1, true),
('grooming', 300, 1, true),
('vaccination', 500, 2,  true);

insert into prod.record (timing, date, client_id, master_id, pet_service_id) values
('09:00-10:00', '2025-03-08', 1, 1, 1);

insert into prod.master_calendar (master_id, calendar_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2), (3, 3);