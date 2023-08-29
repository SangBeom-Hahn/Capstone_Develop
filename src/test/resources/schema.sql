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

CREATE TABLE `schedule` (
     `schedule_id` BIGINT NOT NULL AUTO_INCREMENT,
     `step` VARCHAR(45) NULL DEFAULT NULL,
     `start_date` DATE NULL DEFAULT NULL,
     `end_date` DATE NULL DEFAULT NULL,
     `status` VARCHAR(45) NULL DEFAULT NULL,
     `created_date` DATETIME NULL DEFAULT NULL,
     `last_modified_date` DATETIME NULL DEFAULT NULL,
     PRIMARY KEY (`schedule_id`)
);

INSERT INTO `schedule` (`step`, `start_date`, `end_date`, `status`, `created_date`, `last_modified_date`)
VALUES
    ('RECEIVED', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 12:00:00', '2023-08-29 12:00:00'),
    ('ROPOSAL', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 15:30:00', '2023-08-30 10:15:00'),
    ('INTERIM_REPORT', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-31 09:20:00', '2023-08-31 09:20:00'),
    ('FINAL_REPORT', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 12:00:00', '2023-08-29 12:00:00'),
    ('FINAL_PASS', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 15:30:00', '2023-08-30 10:15:00'),
    ('OTHER_QUALIFICATIONS', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-31 09:20:00', '2023-08-31 09:20:00');
