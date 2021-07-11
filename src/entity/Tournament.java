
package entity;

public class Tournament
{
    private int TournamentID;
    private String TournamentName;
    private String Location;
    private String TimeControl;
    

    
    public Tournament(int TournamentID, String TournamentName, String Location, String TimeControl)
    {
        this.TournamentID = TournamentID;
        this.TournamentName = TournamentName;
        this.Location = Location;
        this.TimeControl = TimeControl;
        

    }

    public int getTournamentID() {
        return TournamentID;
    }

    public String getTournamentName() {
        return TournamentName;
    }

    public String getLocation() {
        return Location;
    }

    public String getTimeControl() {
        return TimeControl;
    }

    

    

    @Override
    public String toString() {
        return "Tournament{" + "TournamentID=" + TournamentID + ", Tournament Name=" + TournamentName + ", Location=" + Location + ", TimeControl=" + TimeControl + "}";
    }
}
