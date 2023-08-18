CREATE TABLE admin
(
    `admin_id` BIGINT NOT NULL AUTO_INCREMENT,
    `login_id` VARCHAR(45) NULL DEFAULT NULL,
    `password` VARCHAR(45) NULL DEFAULT NULL,
    `name` VARCHAR(45) NULL DEFAULT NULL,
    `created_date` DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`admin_id`)
);

CREATE TABLE student
(
    `student_id` BIGINT NOT NULL AUTO_INCREMENT,
    `login_id` VARCHAR(45) NULL DEFAULT NULL,
    `password` VARCHAR(255) NULL DEFAULT NULL,
    `birth` DATE NULL DEFAULT NULL,
    `department` VARCHAR(45) NULL DEFAULT NULL,
    `grade` VARCHAR(45) NULL DEFAULT NULL,
    `phone_number` VARCHAR(45) NULL DEFAULT NULL,
    `sex` VARCHAR(45) NULL DEFAULT NULL,
    `name` VARCHAR(45) NULL DEFAULT NULL,
    `email` VARCHAR(45) NULL DEFAULT NULL,
    `student_number` VARCHAR(45) NULL DEFAULT NULL,
    `created_date` DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`student_id`)
);

CREATE TABLE refresh_token
(
   `refresh_token_id` BIGINT NOT NULL AUTO_INCREMENT,
   `token_value` VARCHAR(255) UNIQUE NOT NULL,
   `member_id` BIGINT NOT NULL,
   `expired_time` DATETIME NOT NULL,
   PRIMARY KEY (`refresh_token_id`)
);