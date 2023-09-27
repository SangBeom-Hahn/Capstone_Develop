CREATE TABLE student
(
    `student_id` BIGINT NOT NULL AUTO_INCREMENT,
    `login_id` VARCHAR(45) NULL DEFAULT NULL,
    `password` VARCHAR(255) NULL DEFAULT NULL,
    `birth` DATE NULL DEFAULT NULL,
    `classification` VARCHAR(45) NULL DEFAULT NULL,
    `department` VARCHAR(45) NULL DEFAULT NULL,
    `phone_number` VARCHAR(45) NULL DEFAULT NULL,
    `sex` VARCHAR(45) NULL DEFAULT NULL,
    `name` VARCHAR(45) NULL DEFAULT NULL,
    `email` VARCHAR(45) NULL DEFAULT NULL,
    `answer_Pw` varchar(255) not null,
    `roletype` varchar(255) not null,
    `created_date` DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`student_id`)
);

CREATE TABLE notice_board
(
    `notice_board_id` BIGINT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(45) NULL DEFAULT NULL,
    `fix` BOOLEAN NULL DEFAULT NULL,
    `title` VARCHAR(45) NULL DEFAULT NULL,
    `views` VARCHAR(45) NULL DEFAULT NULL,
    `student_id` BIGINT NOT NULL,
    `created_date` DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`notice_board_id`),
    FOREIGN KEY (student_id)
        REFERENCES student (student_id)
);

CREATE TABLE comment
(
    `comment_id`         BIGINT NOT NULL AUTO_INCREMENT,
    `notice_board_id`    BIGINT NOT NULL,
    `student_id`         BIGINT NOT NULL,
    `content`            VARCHAR(45) NULL DEFAULT NULL,
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

ALTER TABLE apply ALTER COLUMN apply_id RESTART WITH 1;

CREATE TABLE `submit_form` (
    `submit_form_id` BIGINT NOT NULL AUTO_INCREMENT,
    `apply_id` BIGINT NOT NULL,
    `professor_name` VARCHAR(45) NOT NULL,
    `graduation_date` DATE NOT NULL,
    `capstone_completion` BOOLEAN NOT NULL,
    `approval` VARCHAR(45) NOT NULL,
    `reject_reason` VARCHAR(45) NULL DEFAULT NULL,
    `created_date` DATETIME NOT NULL,
    `last_modified_date` DATETIME NOT NULL,
    PRIMARY KEY (`submit_form_id`)
);

CREATE TABLE `proposal_form` (
    `proposal_form_id` BIGINT NOT NULL AUTO_INCREMENT,
    `apply_id` BIGINT NOT NULL,
    `title` VARCHAR(45) NOT NULL,
    `division` VARCHAR(45) NOT NULL,
    `qualification` VARCHAR(45) NOT NULL,
    `keyword` VARCHAR(45) NOT NULL,
    `content` VARCHAR(255) NOT NULL,
    `approval` VARCHAR(45) NOT NULL,
    `reject_reason` VARCHAR(45) NULL DEFAULT NULL,
    `created_date` DATETIME NOT NULL,
    `last_modified_date` DATETIME NOT NULL,
    PRIMARY KEY (`proposal_form_id`)
);

CREATE TABLE `interim_form` (
    `interim_form_id` BIGINT NOT NULL AUTO_INCREMENT,
    `apply_id` BIGINT NOT NULL,
    `title` VARCHAR(45) NOT NULL,
    `division` VARCHAR(45) NOT NULL,
    `text` VARCHAR(255) NOT NULL,
    `plan` VARCHAR(255) NOT NULL,
    `approval` VARCHAR(45) NOT NULL,
    `reject_reason` VARCHAR(45) NULL DEFAULT NULL,
    `created_date` DATETIME NOT NULL,
    `last_modified_date` DATETIME NOT NULL,
    PRIMARY KEY (`interim_form_id`)
);

CREATE TABLE `final_form` (
    `final_form_id` BIGINT NOT NULL AUTO_INCREMENT,
    `apply_id` BIGINT NOT NULL,
    `title` VARCHAR(45) NOT NULL,
    `division` VARCHAR(45) NOT NULL,
    `qualification` VARCHAR(45) NOT NULL,
    `page_number` INTEGER NOT NULL,
    `approval` VARCHAR(45) NOT NULL,
    `reject_reason` VARCHAR(45) NULL DEFAULT NULL,
    `created_date` DATETIME NOT NULL,
    `last_modified_date` DATETIME NOT NULL,
    PRIMARY KEY (`final_form_id`)
);