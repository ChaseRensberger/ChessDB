CREATE TABLE Game(
    GameID INT PRIMARY KEY,
    WhitePlayerID INT NOT NULL,
    BlackPlayerID INT NOT NULL,
    FEN VARCHAR(60) NOT NULL,
    CLOCK VARCHAR(30) NOT NULL,
    TournamentID INT NOT NULL,
    FOREIGN KEY (TournamentID) REFERENCES Tournament(TournamentID)
);