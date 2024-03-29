DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS notice_board;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS refresh_token;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS schedule_board;

CREATE TABLE `student` (
                           `student_id` bigint NOT NULL AUTO_INCREMENT,
                           `created_date` datetime(6) NOT NULL,
                           `last_modified_date` datetime(6) NOT NULL,
                           `birth` date NOT NULL,
                           `classification` VARCHAR(255) NULL DEFAULT NULL,
                           `department` varchar(255) NOT NULL,
                           `email` varchar(255) NOT NULL,
                           `login_id` varchar(255) NOT NULL,
                           `name` varchar(255) NOT NULL,
                           `password` varchar(255) NOT NULL,
                           `phone_number` varchar(255) NOT NULL,
                           `sex` varchar(255) NOT NULL,
                           `answer_Pw` VARCHAR(255) NOT NULL,
                           `roletype` varchar(255) not null,
                           PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE refresh_token (
                               refresh_token_id bigint not null auto_increment,
                               token_value varchar(255) unique not null,
                               member_id bigint not null,
                               expired_time datetime not null,
                               primary key (refresh_token_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `notice_board` (
    `notice_board_id` bigint NOT NULL AUTO_INCREMENT,
    `student_id` bigint NOT NULL,
    `content` VARCHAR(255) NULL DEFAULT NULL,
    `fix` BIT(1) NULL DEFAULT NULL,
    `title` VARCHAR(45) NULL DEFAULT NULL,
    `views` INTEGER NULL DEFAULT NULL,
    `created_date` datetime(6) NULL DEFAULT NULL,
    `last_modified_date` datetime(6) NULL DEFAULT NULL,
    PRIMARY KEY (`notice_board_id`),
    CONSTRAINT `fk_notice_board_student`
    FOREIGN KEY (`student_id`)
    REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comment` (
    `comment_id` bigint NOT NULL AUTO_INCREMENT,
    `notice_board_id` bigint NOT NULL,
    `student_id` bigint NOT NULL,
    `content` VARCHAR(225) NULL DEFAULT NULL,
    `created_date` datetime(6) NULL DEFAULT NULL,
    `last_modified_date` datetime(6) NULL DEFAULT NULL,
    PRIMARY KEY (`comment_id`),
    CONSTRAINT `fk_comment_notice_board`
    FOREIGN KEY (`notice_board_id`)
        REFERENCES `notice_board` (`notice_board_id`),
    CONSTRAINT `fk_comment_student`
        FOREIGN KEY (`student_id`)
            REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `schedule` (
    `schedule_id` bigint NOT NULL AUTO_INCREMENT,
    `step` VARCHAR(45) not null,
    `start_date` DATE not null,
    `end_date` DATE not null,
    `status` VARCHAR(45) not null,
    `created_date` DATETIME not null,
    `last_modified_date` DATETIME not null,
    PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `schedule_board` (
      `schedule_board_id` BIGINT NOT NULL AUTO_INCREMENT,
      `receive` VARCHAR(2550) not null,
      `proposal` VARCHAR(2550) not null,
      `middle_report` VARCHAR(2550) not null,
      `final_report` VARCHAR(2550) not null,
      `final_pass` VARCHAR(2550) not null,
      `other_qualification` VARCHAR(2550) not null,
      `created_date` DATETIME not null,
      `last_modified_date` DATETIME not null,
      PRIMARY KEY (`schedule_board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO student
VALUES (1, '2023-03-14 12:35:29.857156', '2023-03-14 12:35:29.857156', '1111-11-11',
        'ADMIN', 'dummy',
        'NONE', 'admin1', 'admin1',
        '$2a$10$8XrRK7cpYz4sudDB/iaXQ.VFRq1rdukffklMegISsiQTbT2psxT/a',
        'dummy', 'NONE', 'dummy', 'ADMIN');

INSERT INTO student
VALUES (2, '2023-03-14 12:35:29.857156', '2023-03-14 12:35:29.857156', '2023-09-01',
        'UNDERGRADUATE_STUDENT', '컴퓨터공학부',
        '1234@naver.com', '202013149', '고은아',
        '$2a$10$AotY6FgqoyhzDO2qqkCr4O/rdXvY2GoHtpYt8TGj4bWCo6MB.Z5Ji',
        '010-1234-5678', 'FEMALE', '서울', 'STUDENT');

INSERT INTO student
VALUES (3, '2023-03-14 12:35:29.857156', '2023-03-14 12:35:29.857156', '2023-09-01',
        'UNDERGRADUATE_STUDENT', '컴퓨터공학부',
        '1234@naver.com', '201812709', '한상범',
        '$2a$10$AotY6FgqoyhzDO2qqkCr4O/rdXvY2GoHtpYt8TGj4bWCo6MB.Z5Ji',
        '010-1234-5678', 'MALE', '서울', 'STUDENT');

INSERT INTO refresh_token
VALUES (1, 'refreshToken', 1, '2023-03-14 12:35:29.857156');

INSERT INTO notice_board
VALUES (1, 1, 'content1', false, 'title1', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (2, 1, 'content2', false, 'title2', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (3, 1, 'content3', false, 'title3', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (4, 1, 'content4', false, 'title4', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (5, 1, 'content5', false, 'title5', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (6, 1, 'content6', false, 'title6', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (7, 1, 'content7', false, 'title7', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (8, 1, 'content8', false, 'title8', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (9, 1, 'content9', false, 'title9', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (10, 1, 'content10', false, 'title10', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (11, 1, 'content11', false, 'title11', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (12, 1, 'content12', false, 'title12', 1, '1999-10-13', '1999-10-13');

INSERT INTO notice_board
VALUES (13, 1, 'content13', false, 'title13', 1, '1999-10-13', '1999-10-13');

INSERT INTO comment
VALUES (1, 1, 2, '댓글 1', '1999-10-13', '1999-10-13');
VALUES (1, 'refreshToken', 1, '2023-03-14 12:35:29.857156');

INSERT INTO schedule
VALUES
    (1, 'RECEIVED', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 12:00:00', '2023-08-29 12:00:00'),
    (2, 'ROPOSAL', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 15:30:00', '2023-08-30 10:15:00'),
    (3, 'INTERIM_REPORT', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-31 09:20:00', '2023-08-31 09:20:00'),
    (4, 'FINAL_REPORT', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 12:00:00', '2023-08-29 12:00:00'),
    (5, 'FINAL_PASS', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-29 15:30:00', '2023-08-30 10:15:00'),
    (6, 'OTHER_QUALIFICATIONS', '2023-08-29', '2023-08-31', 'PROCEEDING', '2023-08-31 09:20:00', '2023-08-31 09:20:00');

INSERT INTO `schedule_board`
VALUES (1, 'receive', 'proposal', 'middleReport', 'finalReport', 'finalPass', 'otherQualification', '2023-08-29 12:00:00', '2023-08-29 12:00:00');