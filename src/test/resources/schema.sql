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
    `roletype` varchar(255) not null,
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

CREATE TABLE graduation
(
    `graduation_id` BIGINT NOT NULL AUTO_INCREMENT,
    `method` VARCHAR(45) NULL DEFAULT NULL,
    `status` VARCHAR(45) NULL DEFAULT NULL,
    `step` VARCHAR(45) NULL DEFAULT NULL,
    `capstone_completion` BOOLEAN NULL DEFAULT NULL,
    `graduation_date` DATE NULL DEFAULT NULL,
    `professor_name` VARCHAR(45) NULL DEFAULT NULL,
    `created_date` DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`graduation_id`)
);

CREATE TABLE apply
(
    `apply_id` BIGINT NOT NULL AUTO_INCREMENT,
    `student_id` BIGINT NOT NULL,
    `graduation_id` BIGINT NOT NULL,
    `created_date` DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`apply_id`),
    FOREIGN KEY (`student_id`)
        REFERENCES `student` (`student_id`),
    FOREIGN KEY (`graduation_id`)
        REFERENCES `graduation` (`graduation_id`)
);