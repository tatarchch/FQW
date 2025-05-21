insert into prod.calendar (date) values
('2025-05-20'),
('2025-05-21'),
('2025-05-22'),
('2025-05-23'),
('2025-05-24'),
('2025-05-25'),
('2025-05-26'),
('2025-05-27'),
('2025-05-28'),
('2025-05-29'),
('2025-05-30'),
('2025-05-31');

insert into prod.client (name, login, password, email, role) values
('Viktor Samohin', 'user', '$2a$05$sfy7csYJVFnb.pyn9CSdsudL2PA1cGtifOfZomhZA/U4/BIpU7siS', 'user@mail.ru', 'USER'),
('Ivanov Nikita Alekseevich', 'admin', '$2a$11$Ajlqx/pZ6zYJqgSSP4EdruQUIKwhmjVcLPLId13aavwjd8p8QTama', 'admin@mail.ru', 'ADMIN'),
('Artem', 'artem', '$2a$05$WY1ll8xJLKRRrNIlcOmywOwNVU9c4NrNdzeDSe/j6mCM8MXjNJnOe', 'tatarnirov15277@mail.ru', 'USER');


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
('09:00-10:00', '2025-05-20', 1, 1, 1);

insert into prod.master_calendar (master_id, calendar_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10), (2, 11), (2, 12),
(3, 1), (3, 2), (3, 3);