insert into prod.calendar (date) values
('2025-02-27'),
('2025-02-28'),
('2025-03-01'),
('2025-03-02'),
('2025-03-03');

insert into prod.client (name, login, password, email) values
('Viktor Samohin', 'soh', '123', 'soh123@mail.ru'),
('Nikita Ivanov Sergeevich', 'nekit', '098', 'nekit098@mail.ru');

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
('09:00-10:00', '2025-02-27', 1, 1, 1),
('10:00-11:00', '2025-02-28', 1, 2, 1),
('10:00-11:00', '2025-03-01', 1, 1, 2),
('09:00-10:00', '2025-03-01', 1, 1, 1);

insert into prod.master_calendar (master_id, calendar_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2), (3, 3);