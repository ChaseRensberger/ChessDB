CREATE TABLE Player(
    PlayerID INT PRIMARY KEY,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    Country VARCHAR(30) NOT NULL,
    Birthday DATE NOT NULL,
    Rating INT NOT NULL,
    Score DOUBLE NOT NULL
);