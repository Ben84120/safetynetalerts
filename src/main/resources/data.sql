/* Setting up PROD DB */



create table person(
ID int PRIMARY KEY AUTO_INCREMENT,
FIRST_NAME varchar(25) NOT NULL,
LAST_NAME varchar (50) NOT NULL,
ADDRESS varchar (75) NOT NULL,
CITY varchar (30) NOT NULL,
ZIP int NOT NULL,
PHONE varchar (20) NOT NULL,
EMAIL varchar (75) NOT NULL 
);

create table firestations(
ADDRESS varchar (75) NOT NULL,
STATION int NOT NULL
);

create table medicalrecords(
BIRTHDATE date NOT NULL,
MEDICATION varchar (75) NOT NULL,
ALLERGIE varchar (75) NOT NULL
);







