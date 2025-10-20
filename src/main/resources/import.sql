INSERT INTO tb_owner(name, email, phone, birth_date, password) VALUES ('Alex Green', 'alex@gmail.com', '11995618563', '1990-01-01', '123456');
INSERT INTO tb_owner(name, email, phone, birth_date, password) VALUES ('Maria Silva', 'maria@gmail.com', '48995612063', '1981-03-22', '123456');

INSERT INTO tb_pet(name, species, breed, gender, birth_date, weight, owner_id) VALUES ('Bella', 'CAT', 'Ragdoll', 'FEMALE', '2021-01-01', 7.2, 1);
INSERT INTO tb_pet(name, species, breed, gender, birth_date, weight, owner_id) VALUES ('Rex', 'DOG', 'Yorkshire', 'MALE', '2023-08-18', 3.0, 2);

INSERT INTO tb_care_schedule(last_vacine, next_vacine, current_medication, medication_notes, last_appointment, next_appointment, appointment_notes, last_grooming, grooming, notes, pet_id, moment) VALUES ('2021-01-01', '2021-01-01', 'Dipirona', '2x ao dia', '2021-01-01', '2021-01-01', 'nao sei', '2021-01-01', 'Banho', 'banho com hidratante', 1, TIMESTAMP WITH TIME ZONE '2021-01-01T10:00:00Z');
