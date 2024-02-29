CREATE DATABASE LmsProject;
USE LmsProject;

CREATE TABLE users (
	user_id INT PRIMARY KEY auto_increment,
	username varchar(50),
    fullname nvarchar(100) default'',
    phone_number varchar(10) not null,
    email varchar(100),
    password varchar(30) not null,
    dob date,
    avatar_url varchar(200),
    role varchar(20)
);
CREATE TABLE courses (
    course_id VARCHAR(255) PRIMARY KEY,
    lecture_id INT,
    course_name VARCHAR(255),
    max_quantity INT,
    department_id VARCHAR(255),
    start_date DATE,
    end_date DATE,
    day VARCHAR(20),
    duration VARCHAR(255),
    current_quantity INT,
    FOREIGN KEY (lecture_id) REFERENCES users(user_id)
);
CREATE TABLE assignment (
    assignment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    assignment_name VARCHAR(255),
    description TEXT,
    course_id VARCHAR(255),
    start_date DATE,
    end_date DATE,
    file_path VARCHAR(255),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);
CREATE TABLE course_details (
    course_detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    courseId VARCHAR(255),
    lecture_id INT,
    student_id INT,
    grade FLOAT,
    FOREIGN KEY (courseId) REFERENCES courses(course_id),
    FOREIGN KEY (lecture_id) REFERENCES users(user_id),
    FOREIGN KEY (student_id) REFERENCES users(user_id)
);
CREATE TABLE submissions (
    submission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    assignment_id BIGINT,
    student_id INT,
    grade FLOAT,
    FOREIGN KEY (assignment_id) REFERENCES assignments(assignment_id),
    FOREIGN KEY (student_id) REFERENCES users(user_id)
);



