
-- creating data schema...
CREATE SCHEMA tso_user_db;

-- query 2
USE tso_user_db;

-- creating user table
CREATE TABLE tb_user  (
     id BIGINT NOT NULL AUTO_INCREMENT,
     username VARCHAR(50) NOT NULL,
     full_name VARCHAR(150) NOT NULL,
     password VARCHAR(30) NOT NULL,
     email VARCHAR(150),
     phone VARCHAR(150),
     department INT DEFAULT 0,
     role SMALLINT DEFAULT 0,
     status SMALLINT DEFAULT 0,
     PRIMARY KEY (id)
);

-- inserting default admin user
INSERT INTO tb_user (username, full_name, password, email, phone, department, role, status) 
VALUES("admin", "Administrator", "admin123", "admin@mail.com", "", 1, 1, 1);


