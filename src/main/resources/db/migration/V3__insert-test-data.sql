insert into prod.calendar (date) values
('2025-01-23'),
('2025-01-24'),
('2025-01-25'),
('2025-01-26'),
('2025-01-27');

insert into prod.client (name, login, password, email) values
('Viktor Soh', 'soh', '123', 'soh123@mail.ru'),
('Nikita Suman', 'suman', '098', 'suman098@mail.ru');

insert into prod.place (name, address, active) values
('Dom zhivotnih', 'Pravdi 24', true),
('Vse dlya zhivotnih', 'Tverskay 1', true);

insert into prod.master (name, patronymic, level, place_id, active) values
('Sam Sulik', null, 1, 1, true),
('Stan Smith',null, 2, 2, true),
('Alexander Petrov','Nikolaevich', 3, 1, true);

insert into prod.service (name, cost, level, active) values
('massage', 200, 1, true),
('grooming', 300, 1, true),
('privivka', 500, 2,  true);

insert into prod.record (timing, date, client_id, master_id, service_id) values
('09:00-10:00', '2025-01-23', 1, 1, 1),
('10:00-11:00', '2025-01-24', 1, 2, 1),
('10:00-11:00', '2025-01-25', 1, 1, 2),
('09:00-10:00', '2025-01-26', 1, 1, 1);

insert into prod.master_calendar (master_id, calendar_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2), (3, 3);