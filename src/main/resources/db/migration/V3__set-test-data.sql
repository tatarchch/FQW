insert into prod.calendar (date) values
('2024-10-12'), ('2024-10-13');

insert into prod.client (name, patronymic, surname, login, password) values
('Viktor', 'Ivanovich', 'Soh', 'soh', '123'),
('Nikita', 'Ivanovich', 'Suman', 'suman', '098');

insert into prod.place (name, address, active) values
('Dom zhivotnih', 'Pravdi 24', true),
('Vse dlya zhivotnih', 'Tverskay 1', true);

insert into prod.master (name, patronymic, surname, level, place_id, active) values
('Sam',null, 'Sulik', 1, 1, true),
('Stan',null, 'Smith', 2, 2, true),
('Alexander','Nikolaevich', 'Petrov', 3, 1, true);

insert into prod.service (name, cost, level, active) values
('massage', 200, 1, true),
('grooming', 300, 1, true),
('privivka', 500, 2,  true);

insert into prod.record (calendar_id, client_id, daily, master_id, service_id) values
(1, 1, '9.00-10.00', 1, 1),
(2, 1, '9.00-10.00', 1, 1),
(1, 2, '10.00-11.00', 2, 2),
(1, 1, '9.00-10.00', 3, 1);

insert into prod.master_calendar (master_id, calendar_id) values
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(3, 2);