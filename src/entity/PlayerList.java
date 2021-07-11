
package entity;

public class PlayerList
{
    private int RegistrationID;
    private int PlayerID;
    private int TournamentID;
   
    

    
    public PlayerList(int RegistrationID, int PlayerID, int TournamentID)
    {
        this.RegistrationID = RegistrationID;
        this.PlayerID = PlayerID;
        this.TournamentID = TournamentID;
        
    }

    public int getRegistrationID() {
        return RegistrationID;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public int getTournamentID() {
        return TournamentID;
    }

    

    

    @Override
    public String toString() {
        return "PlayerList{" + "RegistrationID=" + RegistrationID + ", PlayerID=" + PlayerID + ", TournamentID=" + TournamentID + "}";
    }
}
