
CREATE TABLE users (
                       id INT not null AUTO_INCREMENT PRIMARY KEY ,
                       username varchar(255) not null ,
                       password varchar(255) not null
)
    ENGINE = InnoDB;

CREATE TABLE roles (
                       id INT not null AUTO_INCREMENT PRIMARY KEY ,
                       role varchar(100) not null
)
    ENGINE = InnoDB;

CREATE TABLE user_roles(
                           user_id INT NOT NULL ,
                           role_id INT NOT NULL ,

                           FOREIGN KEY (user_id) REFERENCES users(id),
                           FOREIGN KEY (role_id) REFERENCES roles(id),
                           UNIQUE (user_id,role_id)
)
    ENGINE = InnoDB;

INSERT INTO users
VALUES (1, 'admin', 'admin'),
       (2, 'dasha', 'user'),
       (3, 'sasha', 'admin');

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles
VALUES (1, 2);
INSERT INTO user_roles
VALUES (2, 1);
INSERT INTO user_roles
VALUES (3, 1);
INSERT INTO user_roles
VALUES (3, 2);