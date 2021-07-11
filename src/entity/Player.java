
package entity;

public class Player
{
    private int PlayerID;
    private String FirstName;
    private String LastName;
    private String Country;
    private String Birthday;
    private int Rating;
    private double Score;


    
    public Player(int PlayerID, String FN, String LN, String Country, String Birthday, int Rating, double Score)
    {
        this.PlayerID = PlayerID;
        this.FirstName = FN;
        this.LastName = LN;
        this.Country = Country;
        this.Birthday = Birthday;
        this.Rating = Rating;
        this.Score = Score;

    }
    
    public void setScore(double n){
        this.Score = n;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getCountry() {
        return Country;
    }

    public String getBirthday() {
        return Birthday;
    }

    public int getRating() {
        return Rating;
    }

    public double getScore() {
        return Score;
    }

    

    @Override
    public String toString() {
        return "Player{" + "PlayerID=" + PlayerID + ", Name=" + FirstName + " " + LastName + ", Country=" + Country + ", Birthday=" + Birthday + ", Rating=" + Rating + ", Score=" + Score + "}";
    }
}
