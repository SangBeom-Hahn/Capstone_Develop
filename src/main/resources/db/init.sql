DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS admin;

CREATE TABLE `student` (
                           `student_id` bigint NOT NULL AUTO_INCREMENT,
                           `created_date` datetime(6) NOT NULL,
                           `last_modified_date` datetime(6) NOT NULL,
                           `birth` date NOT NULL,
                           `department` varchar(255) NOT NULL,
                           `email` varchar(255) NOT NULL,
                           `grade` varchar(255) NOT NULL,
                           `login_id` varchar(255) NOT NULL,
                           `name` varchar(255) NOT NULL,
                           `password` varchar(255) NOT NULL,
                           `phone_number` varchar(255) NOT NULL,
                           `sex` varchar(255) NOT NULL,
                           `student_number` varchar(255) NOT NULL,
                           PRIMARY KEY (`student_id`)
);

CREATE TABLE `admin` (
                         `admin_id` bigint NOT NULL AUTO_INCREMENT,
                         `login_id` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         PRIMARY KEY (`admin_id`)
);

INSERT INTO student
VALUES (1, '2023-03-14 12:35:29.857156', '2023-03-14 12:35:29.857156', '2023-09-01', '컴퓨터공학부',
        '1234@naver.com', 'FOURTH', 'cherry1', '고은아',
        '$2a$10$AotY6FgqoyhzDO2qqkCr4O/rdXvY2GoHtpYt8TGj4bWCo6MB.Z5Ji',
        '010-1234-5678', 'FEMALE', '201811612');

INSERT INTO student
VALUES (2, '2023-03-14 12:35:29.857156', '2023-03-14 12:35:29.857156', '2023-09-01', '컴퓨터공학부',
        '1234@naver.com', 'FOURTH', 'cherry2', '한상범',
        '$2a$10$AotY6FgqoyhzDO2qqkCr4O/rdXvY2GoHtpYt8TGj4bWCo6MB.Z5Ji',
        '010-1234-5678', 'MALE', '201811612');
