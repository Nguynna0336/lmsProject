CREATE DATABASE LmsProject;
USE LmsProject;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30),
    fullname VARCHAR(100),
    phone_number VARCHAR(20),
    email VARCHAR(200),
    date_of_birth DATE,
    password VARCHAR(255),
    avatar_url VARCHAR(255),
    role ENUM('Admin', 'Student', 'Lecturer')
);

CREATE TABLE courses (
    course_id VARCHAR(255) PRIMARY KEY,
    lecturer_id INT,
    course_name VARCHAR(255),
    max_quantity INT,
    department_id VARCHAR(255),
    start_date DATE,
    end_date DATE,
    day ENUM('Monday', 'Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'),
    duration FLOAT,
    current_quantity INT,
    FOREIGN KEY (lecturer_id) REFERENCES users(user_id)
);
CREATE TABLE assignments (
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
    course_id VARCHAR(255),
    lecturer_id INT,
    student_id INT,
    grade FLOAT,
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    FOREIGN KEY (lecturer_id) REFERENCES users(user_id),
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


