
package entity;

public class Game
{
    private int GameID;
    private int WhitePlayerID;
    private int BlackPlayerID;
    private String FEN;
    private String Clock;
    private int TournamentID;
   
    

    
    public Game(int GameID, int WhitePlayerID, int BlackPlayerID, String FEN, String Clock, int TournamentID)
    {
        this.GameID = GameID;
        this.WhitePlayerID = WhitePlayerID;
        this.BlackPlayerID = BlackPlayerID;
        this.FEN = FEN;
        this.Clock = Clock;
        this.TournamentID = TournamentID;
        
    }

    public int getGameID() {
        return GameID;
    }

    public int getWhitePlayerID() {
        return WhitePlayerID;
    }

    public int getBlackPlayerID() {
        return BlackPlayerID;
    }

    public String getFEN() {
        return FEN;
    }

    public String getClock() {
        return Clock;
    }

    public int getTournamentID() {
        return TournamentID;
    }

    

    

    @Override
    public String toString() {
        return "Game{" + "GameID=" + GameID + ", WhitePlayerID=" + WhitePlayerID + ", BlackPlayerID=" + BlackPlayerID + ", FEN=" + FEN + ", Clock=" + Clock + ", TournamentID=" + TournamentID + "}";
    }
}
