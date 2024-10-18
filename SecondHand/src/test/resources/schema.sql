CREATE TABLE USERS (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) UNIQUE,
                       password VARCHAR(100),
                       email VARCHAR(100),
                       name VARCHAR(100),
                       phone VARCHAR(15),
                       address1 VARCHAR(255),
                       address2 VARCHAR(255),
                       avatar VARCHAR(255),
                       role VARCHAR(50)
);
