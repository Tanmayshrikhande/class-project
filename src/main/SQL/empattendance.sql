create database empattendance;
use empattendance;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(100)
);


CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    date DATE,
    present BOOLEAN,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);


