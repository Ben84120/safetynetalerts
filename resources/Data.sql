/* Setting up PROD DB */
create database prod;
use prod;


create table persons(
ID int PRIMARY KEY AUTO_INCREMENT,
FIRST_NAME varchar(25) NOT NULL,
LASTE_NAME varchar (50) NOT NULL,
ADRESSE varchar (75) NOT NULL,
CITY varchar (30) NOT NULL,
ZIP int NOT NULL,
PHONE int NOT NULL,
EMAIL varchar (75) NOT NULL 
);

create table firestation(
ADRESSE varchar (75) NOT NULL,
STATION int NOT NULL
);

create table medicalrecords(
BIRTHDATE date NOT NULL
);

create table medications(
MEDICAMENT varchar (75) NOT NULL,
DOSAGE varchar (20) NOT NULL
);

create table allergies(
ALLERGIE varchar (75) NOT NULL
);