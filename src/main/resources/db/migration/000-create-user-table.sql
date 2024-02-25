-- phonebook.`User` definition

CREATE TABLE `User` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_Email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;