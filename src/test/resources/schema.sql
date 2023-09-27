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

CREATE TABLE `schedule_board` (
    `schedule_board_id` BIGINT NOT NULL AUTO_INCREMENT,
    `receive` VARCHAR NULL DEFAULT NULL,
    `proposal` VARCHAR NULL DEFAULT NULL,
    `middle_report` VARCHAR NULL DEFAULT NULL,
    `final_report` VARCHAR NULL DEFAULT NULL,
    `final_pass` VARCHAR NULL DEFAULT NULL,
    `other_qualification` VARCHAR NULL DEFAULT NULL,
    `created_date` DATETIME NULL DEFAULT NULL,
    `last_modified_date` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`schedule_board_id`)
);

INSERT INTO `schedule` (`step`, `start_date`, `end_date`, `status`, `created_date`, `last_modified_date`)
VALUES
    ('RECEIVED', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 12:00:00', '2023-08-29 12:00:00'),
    ('ROPOSAL', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 15:30:00', '2023-08-30 10:15:00'),
    ('INTERIM_REPORT', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-31 09:20:00', '2023-08-31 09:20:00'),
    ('FINAL_REPORT', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 12:00:00', '2023-08-29 12:00:00'),
    ('FINAL_PASS', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 15:30:00', '2023-08-30 10:15:00'),
    ('OTHER_QUALIFICATIONS', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-31 09:20:00', '2023-08-31 09:20:00');

INSERT INTO `schedule_board` (`receive`, `proposal`, `middle_report`, `final_report`, `final_pass`, `other_qualification`, `created_date`, `last_modified_date`)
VALUES
    ('receive', 'proposal', 'middleReport', 'finalReport', 'finalPass', 'otherQualification', '2023-08-29 12:00:00', '2023-08-29 12:00:00');
