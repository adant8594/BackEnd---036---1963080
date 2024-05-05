INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Adán', '$2a$10$4T2wC9RS2Flhffqwj3YUeuTtIpYM4oJh7tJZ6w0seg6LoPhuXveDq', 1, 'Adán', 'Treviño', 'test1@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Treviño', '$2a$10$CLiUpq1.vG15B8MK.6YLFO3iJitzawoaro7hUjuscRds6b17nukSK', 1, 'No sé', 'Hernández', 'test2@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_to_roles (user_id, rooles_id) VALUES (1, 1);
INSERT INTO usuarios_to_roles (user_id, rooles_id) VALUES (2, 2);
INSERT INTO usuarios_to_roles (user_id, rooles_id) VALUES (2, 1);