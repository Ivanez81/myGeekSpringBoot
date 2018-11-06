DROP TABLE IF EXISTS role;

CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO role (name)
VALUES
       ('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  password char(80) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO user (username, password, first_name, last_name, email)
VALUES
       ('admin', '$2a$10$BkCOLDojqQ43F6.TkKvGb.ty4WMztARbndG8VmSYIC..KONk68PlO', 'Ivan', 'Blinov', 'ivan@blinov.ru'),
       ('manager', '$2a$10$BkCOLDojqQ43F6.TkKvGb.ty4WMztARbndG8VmSYIC..KONk68PlO', 'Vasya', 'Pupkin', 'vasya@blinov.ru'),
       ('employee', '$2a$10$BkCOLDojqQ43F6.TkKvGb.ty4WMztARbndG8VmSYIC..KONk68PlO', 'Boris', 'Britva', 'boris@blinov.ru');

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,

  PRIMARY KEY (user_id,role_id),

  KEY FK_ROLE_idx (role_id),

  CONSTRAINT FK_USER_05 FOREIGN KEY (user_id)
  REFERENCES user (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_ROLE FOREIGN KEY (role_id)
  REFERENCES role (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users_roles (user_id, role_id)
VALUES
       (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 1);

DROP TABLE IF EXISTS student;
CREATE TABLE student (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=latin1;

INSERT INTO student (name)
VALUES
       ('Ivan'),
       ('Bob'),
       ('Alex'),
       ('Boris'),
       ('Theo'),
       ('Irina');

DROP TABLE IF EXISTS course;
CREATE TABLE course (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  duration INT NOT NULL,
  description VARCHAR(255),
  PRIMARY KEY (id)
) ENGINE=InnoDB CHARSET=latin1;

INSERT INTO course (name, duration, description)
VALUES
       ('Java', 7, 'The best Java course'),
       ('C++', 6, 'C++ for professional'),
       ('Assembler', 12, 'Very hard'),
       ('Python', 8, 'Ideal for web programming'),
       ('.Net', 5, 'for Windows');

DROP TABLE IF EXISTS student_course;
CREATE TABLE student_course (
  student_id INT NOT NULL,
  course_id INT NOT NULL,
  grade INT NOT NULL DEFAULT 0,
  lessons INT NOT NULL DEFAULT 0,
  PRIMARY KEY (student_id, course_id),
  INDEX fk_course_idx (course_id ASC),
  CONSTRAINT fk_student FOREIGN KEY (student_id)
  REFERENCES student (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_course FOREIGN KEY (course_id)
  REFERENCES course (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB CHARSET=latin1;

INSERT INTO student_course (student_id, course_id)
VALUES
       (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 3),
       (2, 4),
       (2, 5),
       (3, 1),
       (3, 4),
       (4, 2),
       (5, 3),
       (6, 4);