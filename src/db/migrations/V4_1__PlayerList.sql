CREATE TABLE PlayerList(
    RegistrationID INT PRIMARY KEY,
    PlayerID INT NOT NULL,
    FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID),
    TournamentID INT NOT NULL,
    FOREIGN KEY (TournamentID) REFERENCES Tournament(TournamentID)
);