-- phonebook.People definition

CREATE TABLE `People` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `phone` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_People_User` (`user_id`),
    CONSTRAINT `CK_People_User_FK` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;