DROP DATABASE IF EXISTS contact_list_test;
CREATE DATABASE IF NOT EXISTS contact_list_test;

USE contact_list_test;

CREATE TABLE IF NOT EXISTS Contacts
  (
  ContactID int(11) NOT NULL AUTO_INCREMENT,
  FirstName varchar(50) NOT NULL,
  LastName varchar(50) NOT NULL,
  Company varchar(50) NOT NULL,
  Phone varchar(15) NULL,
  Email varchar(50) NOT NULL,
  PRIMARY KEY(ContactID)
  );

INSERT INTO Contacts (FirstName, LastName, Company, Email, Phone)
  VALUES ('Margo', 'Laruen', 'Oracle', 'ml@oracle.com', '345-987-0283');
