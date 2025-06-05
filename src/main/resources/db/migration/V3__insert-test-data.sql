insert into prod.calendar (date) values
('2025-06-09'),
('2025-06-10'),
('2025-06-11'),
('2025-06-12'),
('2025-06-13'),
('2025-06-14'),
('2025-06-15'),
('2025-06-16'),
('2025-06-17'),
('2025-06-18'),
('2025-06-19'),
('2025-06-20'),
('2025-06-21'),
('2025-06-22'),
('2025-06-23');


insert into prod.client (name, login, password, email, role) values
('Самохин Виктор Алексеевич', 'user', '$2a$05$sfy7csYJVFnb.pyn9CSdsudL2PA1cGtifOfZomhZA/U4/BIpU7siS', 'user@mail.ru', 'USER'),
('Иванов Никита Алексеевич', 'admin', '$2a$11$Ajlqx/pZ6zYJqgSSP4EdruQUIKwhmjVcLPLId13aavwjd8p8QTama', 'admin@mail.ru', 'ADMIN'),
('Татарников Артём Юрьевич', 'artem', '$2a$05$WY1ll8xJLKRRrNIlcOmywOwNVU9c4NrNdzeDSe/j6mCM8MXjNJnOe', 'tatarnirov15277@mail.ru', 'USER');


insert into prod.place (name, address, active) values
('Дом животных', 'Правды 24', true),
('Всё для животных', 'Тверская 1', true);

insert into prod.master (name, level, place_id, active) values
('Суликов Александр Алексеевич', 1, 1, true),
('Смит Стен', 2, 2, true),
('Петров Владимир Николаевич', 3, 1, true);

insert into prod.pet_service (name, cost, level, active) values
('массаж', 200, 1, true),
('груминг', 300, 1, true),
('вакцинация', 500, 2,  true);

insert into prod.record (timing, date, client_id, master_id, pet_service_id) values
('09:00-10:00', '2025-05-20', 1, 1, 1);

insert into prod.master_calendar (master_id, calendar_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10), (2, 11), (2, 12),
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 8), (3, 11), (3, 12);