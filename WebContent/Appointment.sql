//creating tables
create table appointment(
appointmentID int NOT NULL AUTO_INCREMENT,
Name varchar(25) NOT NULL,
Address varchar(55) NOT NULL,
Gender varchar(10) NOT NULL,
Doctor varchar(10) NOT NULL,
Phone  varchar(10) NOT NULL,
Date date  NOT NULL,
Email varchar(55) NOT NULL,
Comments varchar(55) NOT NULL,
PRIMARY KEY (appointmentID)
);


//insert table
INSERT INTO `appointment`(`appointmentID`, `Name`, `Address`, `Gender`, `Doctor`, `Phone`, `Date`, `Email`, `Comments`);

//update table
UPDATE `appointment` SET `appointmentID`=[value-1],`Name`=[value-2],`Address`=[value-3],`Gender`=[value-4],`Doctor`=[value-5],`Phone`=[value-6],`Date`=[value-7],`Email`=[value-8],`Comments`=[value-9] WHERE 1

