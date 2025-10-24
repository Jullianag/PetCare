INSERT INTO tb_owner(name, email, phone, birth_date, password) VALUES ('Alex Green', 'alex@gmail.com', '11995618563', '1990-01-01', '$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');
INSERT INTO tb_owner(name, email, phone, birth_date, password) VALUES ('Maria Silva', 'maria@gmail.com', '48995612063', '1981-03-22', '$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');

INSERT INTO tb_role(authority) VALUES ( 'ROLE_ADMIN');
INSERT INTO tb_role(authority) VALUES ('ROLE_CLIENT');

INSERT INTO tb_owner_role(owner_id, role_id) VALUES (1, 1);
INSERT INTO tb_owner_role(owner_id, role_id) VALUES (2, 1);
INSERT INTO tb_owner_role(owner_id, role_id) VALUES (2, 2);

INSERT INTO tb_pet(name, species, breed, gender, birth_date, weight, owner_id) VALUES ('Bella', 'CAT', 'Ragdoll', 'FEMALE', '2021-01-01', 7.2, 1);
INSERT INTO tb_pet(name, species, breed, gender, birth_date, weight, owner_id) VALUES ('Rex', 'DOG', 'Yorkshire', 'MALE', '2023-08-18', 3.0, 2);
INSERT INTO tb_pet(name, species, breed, gender, birth_date, weight, owner_id) VALUES ('Bianca', 'DOG', 'Yorkshire', 'FEMALE', '2013-05-26', 3.8, 2);
INSERT INTO tb_pet(name, species, breed, gender, birth_date, weight, owner_id) VALUES ('Alfredo', 'DOG', 'Corgi', 'MALE', '2015-10-26', 7.2, 1);
INSERT INTO tb_pet(name, species, breed, gender, birth_date, weight, owner_id) VALUES ('Bolota', 'HAMSTER', 'Roborovski', 'MALE', '2024-12-20', 0.3, 2);

INSERT INTO tb_care_schedule(last_vacine, next_vacine, current_medication, medication_notes, last_appointment, next_appointment, appointment_notes, last_grooming, grooming, notes, pet_id, moment) VALUES ('2021-01-01', '2022-01-01', 'Dipirona', '2x ao dia', '2021-10-30', '2021-11-01', 'Retorno para avaliação.', '2021-01-01', 'Banho e tosa higiênica', 'banho com hidratante', 1, TIMESTAMP WITH TIME ZONE '2021-01-01T10:00:00Z');
INSERT INTO tb_care_schedule(last_vacine, next_vacine, current_medication, medication_notes, last_appointment, next_appointment, appointment_notes, last_grooming, grooming, notes, pet_id, moment) VALUES ('2022-02-05', '2023-02-05', 'Teofilina 28mg', 'A cada 12h', '2024-05-18', '2024-06-18', 'Retorno para avaliação.', '2023-02-07', 'Banho e tosa do corpo', 'banho normal', 3, TIMESTAMP WITH TIME ZONE '2024-01-08T12:00:00Z');
