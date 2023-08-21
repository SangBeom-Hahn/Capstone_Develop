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

CREATE TABLE notice_board
(
    `notice_board_id` BIGINT NOT NULL AUTO_INCREMENT,
    `write_date` DATE NULL DEFAULT NULL,
    `content` VARCHAR(45) NULL DEFAULT NULL,
    `fix` BOOLEAN NULL DEFAULT NULL,
    `title` VARCHAR(45) NULL DEFAULT NULL,
    `views` VARCHAR(45) NULL DEFAULT NULL,
    `created_date` DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`notice_board_id`)
);

CREATE TABLE comment
(
    `comment_id`         BIGINT NOT NULL AUTO_INCREMENT,
    `notice_board_id`    BIGINT NOT NULL,
    `student_id`         BIGINT NOT NULL,
    `content`            VARCHAR(45) NULL DEFAULT NULL,
    `write_date`         DATE NULL DEFAULT NULL,
    `created_date`       DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`comment_id`),
    FOREIGN KEY (notice_board_id)
        REFERENCES notice_board (notice_board_id),
    FOREIGN KEY (student_id)
        REFERENCES student (student_id)
);

CREATE TABLE refresh_token
(
   `refresh_token_id` BIGINT NOT NULL AUTO_INCREMENT,
   `token_value` VARCHAR(255) UNIQUE NOT NULL,
   `member_id` BIGINT NOT NULL,
   `expired_time` DATETIME NOT NULL,
   PRIMARY KEY (`refresh_token_id`)
);