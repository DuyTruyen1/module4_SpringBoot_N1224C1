

CREATE TABLE department
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE employee
(
    id            BINARY(16) DEFAULT (UUID_TO_BIN(UUID())) PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    gender        ENUM ('MALE', 'FEMALE', 'OTHER'),
    salary        DOUBLE       NOT NULL,
    phone         VARCHAR(20),
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE SET NULL
);