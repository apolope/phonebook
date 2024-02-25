-- phonebook.User population

INSERT INTO phonebook.User
(id, email, name, password)
VALUES
    (1, 'user1@email.com', 'People 1', '$2a$10$E0BluPeVVk6Z9BOu.nIrQOiGk/vSw.1QzR6SiXuqENbGsbndY0yOO'),
    (2, 'user2@email.com', 'People 2', '$2a$10$E0BluPeVVk6Z9BOu.nIrQOiGk/vSw.1QzR6SiXuqENbGsbndY0yOO'),
    (3, 'user3@email.com', 'People 3', '$2a$10$E0BluPeVVk6Z9BOu.nIrQOiGk/vSw.1QzR6SiXuqENbGsbndY0yOO');

-- password is P@ssw0rd equivalent to hash $2a$10$E0BluPeVVk6Z9BOu.nIrQOiGk/vSw.1QzR6SiXuqENbGsbndY0yOO