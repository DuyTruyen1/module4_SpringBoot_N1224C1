-- Chèn dữ liệu vào bảng department
INSERT INTO department (name)
VALUES ('Human Resources'),
       ('Finance'),
       ('Engineering'),
       ('Sales'),
       ('Marketing');

-- Chèn dữ liệu vào bảng employee
INSERT INTO employee (name, date_of_birth, gender, salary, phone, department_id)
VALUES ('John Doe', '1990-05-15', 'MALE', 55000, '123-456-7890', 1),
       ('Jane Smith', '1985-08-20', 'FEMALE', 62000, '987-654-3210', 2),
       ('Alice Johnson', '1992-11-10', 'FEMALE', 48000, '555-123-4567', 3),
       ('Robert Brown', '1988-02-25', 'MALE', 75000, '777-888-9999', 4),
       ('Emily Davis', '1995-07-05', 'FEMALE', 53000, '222-333-4444', 5);