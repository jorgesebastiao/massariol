CREATE TABLE permissions(
    id BIGINT NOT NULL AUTO_INCREMENT,
    creationDate DATETIME NOT NULL,
    lastModification DATETIME NOT NULL,
    permission INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE companies(
    id BIGINT NOT NULL AUTO_INCREMENT,
    creationDate DATETIME NOT NULL,
    lastModification DATETIME NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    corporateName VARCHAR(255) NOT NULL,
    tradeName VARCHAR(255) NOT NULL,
    cellPhone VARCHAR(11),
    email VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    creationDate DATETIME NOT NULL,
    lastModification DATETIME NOT NULL,
	active BIT NOT NULL,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    companyId BIGINT,
    PRIMARY KEY (id),
	FOREIGN KEY (companyId) REFERENCES companies(id)
);

CREATE TABLE userPermissions(
    userId BIGINT NOT NULL,
    permissionId BIGINT NOT NULL,
	FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (permissionId) REFERENCES permissions(id)
);

CREATE TABLE persons(
   id BIGINT NOT NULL AUTO_INCREMENT,
   creationDate DATETIME NOT NULL,
   lastModification DATETIME NOT NULL,
   cpf VARCHAR(11) NOT NULL,
   name VARCHAR(255) NOT NULL,
   cellPhone VARCHAR(11),
   email VARCHAR(255),
   signaturePicture LONGBLOB,
   PRIMARY KEY (id)
);

CREATE TABLE students(
   id BIGINT NOT NULL AUTO_INCREMENT,
   creationDate DATETIME NOT NULL,
   lastModification DATETIME NOT NULL,
   office VARCHAR(255),
   personId BIGINT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (personId) REFERENCES persons(id)
);

CREATE TABLE instructors(
   id BIGINT NOT NULL AUTO_INCREMENT,
   creationDate DATETIME NOT NULL,
   lastModification DATETIME NOT NULL,
   personId BIGINT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (personId) REFERENCES persons(id)
);

CREATE TABLE supervisors(
   id BIGINT NOT NULL AUTO_INCREMENT,
   creationDate DATETIME NOT NULL,
   lastModification DATETIME NOT NULL,
   personId BIGINT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (personId) REFERENCES persons(id)
);

CREATE TABLE courses(
   id BIGINT NOT NULL AUTO_INCREMENT,
   creationDate DATETIME NOT NULL,
   lastModification DATETIME NOT NULL,
   courseIdentification VARCHAR(255),
   guideline VARCHAR(255),
   name VARCHAR(255),
   validityInYears INT NOT NULL,
   workload INT NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE businessStudents(
   id BIGINT NOT NULL AUTO_INCREMENT,
   creationDate DATETIME NOT NULL,
   lastModification DATETIME NOT NULL,
   companyId BIGINT NOT NULL,
   studentId BIGINT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (companyId) REFERENCES companies(id),
   FOREIGN KEY (studentId) REFERENCES students(id)
);

CREATE TABLE trainings(
   id BIGINT NOT NULL AUTO_INCREMENT,
   creationDate DATETIME NOT NULL,
   lastModification DATETIME NOT NULL,
   expirationDate DATE,
   finishDate DATE,
   realizationDate DATE,
   startDate DATE,
   businessStudentId BIGINT NOT NULL,
   courseId BIGINT NOT NULL,
   instructorId BIGINT NOT NULL,
   supervisorId BIGINT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (businessStudentId) REFERENCES businessStudents(id),
   FOREIGN KEY (courseId) REFERENCES courses(id),
   FOREIGN KEY (instructorId) REFERENCES instructors(id),
   FOREIGN KEY (supervisorId) REFERENCES supervisors(id)
);