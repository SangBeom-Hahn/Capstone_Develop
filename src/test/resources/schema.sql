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

CREATE TABLE guidance_board
(
    `guidance_board_id`  BIGINT       NOT NULL auto_increment,
    `created_date`       DATETIME(6)  not NULL,
    `last_modified_date` DATETIME(6)  not NULL,
    `content`            VARCHAR(15000) not NULL,
    PRIMARY KEY (`guidance_board_id`)
);

INSERT INTO `guidance_board`
VALUES (1, '2023-08-29', '2023-08-31', 'content');
