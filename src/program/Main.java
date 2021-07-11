
package program;
import entity.*;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ArrayList;

public class Main {

   
    
    private static DAO tournamentDAO;
    private static DAO playerDAO;
    private static DAO gameDAO;
    private static DAO playerListDAO;
    
    public static void main(String[] args) {

        tournamentDAO = new TournamentDAO();
        playerDAO = new PlayerDAO();
        gameDAO = new GameDAO();
        playerListDAO = new PlayerListDAO();
        
        
        
        
        
        
        boolean active = true;
        Scanner sc = new Scanner(System.in);

        

        
        while(active){
            System.out.println("Select an option below for how you would like to modify the chess database");

            System.out.println("0. Exit application.");
            System.out.println("1. Create a new tournament.");
            System.out.println("2. Create a new player.");
            System.out.println("3. Create a new game.");
            System.out.println("4. Register a player.");
            System.out.println("5. Delete a tournament.");
            System.out.println("6. Delete a player.");
            System.out.println("7. Delete a game");
            System.out.println("8. Unregister a player.");
            System.out.println("9. List all tournaments.");
            System.out.println("10. List all players.");
            System.out.println("11. List all games.");
            System.out.println("12. List all player registrations.");
            System.out.println("13. List all the players registered for a tournament.");
            System.out.println("14. Run tournament.");
            System.out.println("15. Update tournament.");
            System.out.println("16. Update player.");
            System.out.println("17. Update game.");
            System.out.println("18. Update registeed players.");


            
            
            int UserInput = sc.nextInt();

            
            int ID;
            String inS1;
            String inS2;
            String inS3;
            String inS4;
            int inI1;
            int inI2;
            int inI3;
            double inD1;
            
            Tournament tournament;
            Player player;
            Game game;
            PlayerList playerList;

            
            if(UserInput == 0){
                active = false;
            }
            
            if(UserInput == 1){
                
                  
                   ID = -1;
                   boolean item1 = false;
                   
                   while(!item1){
                       
                        System.out.println("Enter an ID.");
                        sc.nextLine();
                        String tempInput = sc.nextLine();

                        try{
                            ID = Integer.parseInt(tempInput);
                            item1 = true;
                        }
                        catch(NumberFormatException e){
                            System.out.println("Please Enter a valid ID.");
                        }
                        
                   
                   }
                 
                    
                    
                    System.out.println("Enter a name.");
                    inS1 = sc.nextLine();
                    System.out.println("Enter a location.");
                    inS2 = sc.nextLine();
                    System.out.println("Enter a time control.");
                    inS3 = sc.nextLine();

                    addTournament(ID, inS1, inS2, inS3);

            }
            
 

            if(UserInput == 2){
                
                boolean item2 = false;
                
                while(!item2){
                    
                
                    System.out.println("Enter an ID.");
                    ID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter a first name.");
                    inS1 = sc.nextLine();
                    System.out.println("Enter a last name");
                    inS2 = sc.nextLine();
                    System.out.println("Enter a country.");
                    inS3 = sc.nextLine();
                    System.out.println("Enter a birthday.");
                    inS4 = sc.nextLine();
                    System.out.println("Enter a rating.");
                    inI1 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter a score.");
                    inD1 = sc.nextDouble();
                    sc.nextLine();
                    
                    
                    List<Player> players = playerDAO.getAll();
                    int numberRows = players.size();
               
                    ArrayList<Integer> IDList = new ArrayList<Integer>();
                    
                    for(int i = 0; i < numberRows; i++){
                        IDList.add(players.get(i).getPlayerID());
                    }
                    if(!IDList.contains(ID)){

                        addPlayer(ID, inS1, inS2, inS3, inS4, inI1, inD1);
                        item2 = true;
                    }
                    else{
                        System.out.println("Invalid player ID.");
                    }
                    
                    
                    
                    
                    
                    
                }

            }

            if(UserInput == 3){

                System.out.println("Enter an ID.");
                ID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter white's player ID.");
                inI1 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter blacks's player ID.");
                inI2 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter a FEN.");
                inS1 = sc.nextLine();
                System.out.println("Enter a clock value.");
                inS2 = sc.nextLine();
                System.out.println("Enter the tournament ID.");
                inI3 = sc.nextInt();
                sc.nextLine();
                
                addGame(ID, inI1, inI2, inS1, inS2, inI3);

            }

            if(UserInput == 4){
               
                boolean item4 = false;
                
                while(!item4){
                    
                System.out.println("Enter a registration ID.");
                ID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the player ID.");
                inI1 = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter the TournamentID.");
                inI2 = sc.nextInt();
                sc.nextLine();
                
                
                List<Tournament> tournaments = tournamentDAO.getAll();
                int numberRows = tournaments.size();
               
                ArrayList<Integer> IDList = new ArrayList<Integer>();
                
                for(int i = 0; i < numberRows; i++){
                    IDList.add(tournaments.get(i).getTournamentID());
                }
                if(IDList.contains(inI2)){
                    
                    addPlayerList(ID, inI1, inI2);
                    item4 = true;
                }
                else{
                    System.out.println("Invalid tournament ID.");
                }
                
                
                }
                
            }
            
            if(UserInput == 5){

                System.out.println("Enter an ID.");
                ID = sc.nextInt();
                sc.nextLine();
               

                tournament = new Tournament(ID, getTournament(ID).getTournamentName(), getTournament(ID).getLocation(), getTournament(ID).getTimeControl());
                tournamentDAO.delete(tournament);
            }

            if(UserInput == 6){

                System.out.println("Enter an ID.");
                ID = sc.nextInt();
                sc.nextLine();
                
                player = new Player(ID, getPlayer(ID).getFirstName(), getPlayer(ID).getLastName(), getPlayer(ID).getCountry(), getPlayer(ID).getBirthday(), getPlayer(ID).getRating(), getPlayer(ID).getScore());
                playerDAO.delete(player);
            }

            if(UserInput == 7){

                System.out.println("Enter an ID.");
                ID = sc.nextInt();
                sc.nextLine();
                

                game = new Game(ID, getGame(ID).getWhitePlayerID(), getGame(ID).getBlackPlayerID(), getGame(ID).getFEN(), getGame(ID).getClock(), getGame(ID).getTournamentID());
                gameDAO.delete(game);
            }

            if(UserInput == 8){

                System.out.println("Enter a registration ID.");
                ID = sc.nextInt();
                sc.nextLine();
                

                playerList = new PlayerList(ID, getPlayerList(ID).getPlayerID(), getPlayerList(ID).getTournamentID());
                playerListDAO.delete(playerList);
            }

            if(UserInput == 9){
                printTournaments();
            }

            if(UserInput == 10){
                printPlayers();
            }

            if(UserInput == 11){
                printGames();
            }

            if(UserInput == 12){
                printPlayerLists();
            }
            
            if(UserInput == 13){
                System.out.println("Enter a tournament ID.");
                
                ID = sc.nextInt();
                sc.nextLine();
                
                
                System.out.println("The list of players registered for this tournament are:");
                
                List<PlayerList> playerLists = playerListDAO.getAll();
                int numberRows = playerLists.size();
                for (int i = 0; i < numberRows; i++) {
                    
                    if(playerLists.get(i).getTournamentID() == ID){
                    System.out.printf("%25s", getPlayer(playerLists.get(i).getPlayerID()).getFirstName() + " " + getPlayer(playerLists.get(i).getPlayerID()).getLastName());
                    System.out.println();
                    }
                    
                }
                System.out.println();
                
            }
            
            if(UserInput == 14){
                
                System.out.println("Enter a tournament ID.");
                ID = sc.nextInt();
                sc.nextLine();
                
                List<PlayerList> playerLists = playerListDAO.getAll();
                int round = 0;
                
                ArrayList<Player> Players = new ArrayList<>();
                int numberRows = playerLists.size();
                for (int i = 0; i < numberRows; i++) {
                    
                    if(playerLists.get(i).getTournamentID() == ID){
                        
                        Players.add(getPlayer(playerLists.get(i).getPlayerID()));
                    
                    }
                    
                }

                //-------------------------------------------------------
                System.out.println("Round 1:");
                System.out.println(Players.get(0).getFirstName() + " " + Players.get(0).getLastName() + " vs " + Players.get(2).getFirstName() + " " + Players.get(2).getLastName());
                System.out.println(Players.get(1).getFirstName() + " " + Players.get(1).getLastName() + " vs " + Players.get(3).getFirstName() + " " + Players.get(3).getLastName());
                
                System.out.println("Enter result for game 1."); 
                inS1 = sc.nextLine();
                
                if(inS1 == Players.get(0).getFirstName() + " " + Players.get(0).getLastName()){
                    Players.get(0).setScore(Players.get(0).getScore() + 1);
                }
                if(inS1 == Players.get(2).getFirstName() + " " + Players.get(2).getLastName()){
                    Players.get(2).setScore(Players.get(2).getScore() + 1);
                }
                else{
                    Players.get(0).setScore(Players.get(0).getScore() + 0.5);
                    Players.get(2).setScore(Players.get(2).getScore() + 0.5);
                }
                
                System.out.println("Enter result for game 2."); 
                inS1 = sc.nextLine();
                
                if(inS1 == Players.get(1).getFirstName() + " " + Players.get(1).getLastName()){
                    Players.get(1).setScore(Players.get(1).getScore() + 1);
                }
                if(inS1 == Players.get(3).getFirstName() + " " + Players.get(3).getLastName()){
                    Players.get(3).setScore(Players.get(3).getScore() + 1);
                }
                else{
                    Players.get(1).setScore(Players.get(1).getScore() + 0.5);
                    Players.get(3).setScore(Players.get(3).getScore() + 0.5);
                }
                //-------------------------------------------------------
                System.out.println("Round 2:");
                System.out.println(Players.get(0).getFirstName() + " " + Players.get(0).getLastName() + " vs " + Players.get(3).getFirstName() + " " + Players.get(3).getLastName());
                System.out.println(Players.get(2).getFirstName() + " " + Players.get(2).getLastName() + " vs " + Players.get(1).getFirstName() + " " + Players.get(1).getLastName());
                
                System.out.println("Enter result for game 1."); 
                inS1 = sc.nextLine();
                
                if(inS1 == Players.get(0).getFirstName() + " " + Players.get(0).getLastName()){
                    Players.get(0).setScore(Players.get(0).getScore() + 1);
                }
                if(inS1 == Players.get(3).getFirstName() + " " + Players.get(3).getLastName()){
                    Players.get(3).setScore(Players.get(3).getScore() + 1);
                }
                else{
                    Players.get(0).setScore(Players.get(0).getScore() + 0.5);
                    Players.get(3).setScore(Players.get(3).getScore() + 0.5);
                }
                
                System.out.println("Enter result for game 2."); 
                inS1 = sc.nextLine();
                
                if(inS1 == Players.get(2).getFirstName() + " " + Players.get(2).getLastName()){
                    Players.get(2).setScore(Players.get(2).getScore() + 1);
                }
                if(inS1 == Players.get(1).getFirstName() + " " + Players.get(1).getLastName()){
                    Players.get(1).setScore(Players.get(1).getScore() + 1);
                }
                else{
                    Players.get(2).setScore(Players.get(2).getScore() + 0.5);
                    Players.get(1).setScore(Players.get(1).getScore() + 0.5);
                }

                //-------------------------------------------------------
                System.out.println("Round 3:");
                System.out.println(Players.get(0).getFirstName() + " " + Players.get(0).getLastName() + " vs " + Players.get(1).getFirstName() + " " + Players.get(1).getLastName());
                System.out.println(Players.get(3).getFirstName() + " " + Players.get(3).getLastName() + " vs " + Players.get(2).getFirstName() + " " + Players.get(2).getLastName());
                
                System.out.println("Enter result for game 1."); 
                inS1 = sc.nextLine();
                
                if(inS1 == Players.get(0).getFirstName() + " " + Players.get(0).getLastName()){
                    Players.get(0).setScore(Players.get(0).getScore() + 1);
                }
                if(inS1 == Players.get(1).getFirstName() + " " + Players.get(1).getLastName()){
                    Players.get(1).setScore(Players.get(1).getScore() + 1);
                }
                else{
                    Players.get(0).setScore(Players.get(0).getScore() + 0.5);
                    Players.get(1).setScore(Players.get(1).getScore() + 0.5);
                }
                
                System.out.println("Enter result for game 2."); 
                inS1 = sc.nextLine();
                
                if(inS1 == Players.get(3).getFirstName() + " " + Players.get(3).getLastName()){
                    Players.get(3).setScore(Players.get(3).getScore() + 1);
                }
                if(inS1 == Players.get(2).getFirstName() + " " + Players.get(2).getLastName()){
                    Players.get(2).setScore(Players.get(2).getScore() + 1);
                }
                else{
                    Players.get(3).setScore(Players.get(3).getScore() + 0.5);
                    Players.get(2).setScore(Players.get(2).getScore() + 0.5);
                }
                
                //-------------------------------------------------------

                int max = 0;
                for(int i = 1; i < Players.size(); i++){
                    if(Players.get(i).getScore() > Players.get(max).getScore()){
                        max = i;
                    } 
                }
                System.out.println("Congrats to " + Players.get(max).getFirstName() + " " + Players.get(max).getLastName() + " on winning the tournament!");

 
                
            }
            
            if (UserInput == 15){ //update tournament
                
                System.out.println("Enter an ID.");
                ID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter a name.");
                inS1 = sc.nextLine();
                System.out.println("Enter a location.");
                inS2 = sc.nextLine();
                System.out.println("Enter a time control.");
                inS3 = sc.nextLine();
                
                tournament = new Tournament(ID, getTournament(ID).getTournamentName(), getTournament(ID).getLocation(), getTournament(ID).getTimeControl());
                tournamentDAO.delete(tournament);
                
                addTournament(ID, inS1, inS2, inS3);
            }
            
            if (UserInput == 16){ //update player
                
                System.out.println("Enter an ID.");
                ID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter a first name.");
                inS1 = sc.nextLine();
                System.out.println("Enter a last name");
                inS2 = sc.nextLine();
                System.out.println("Enter a country.");
                inS3 = sc.nextLine();
                System.out.println("Enter a birthday.");
                inS4 = sc.nextLine();
                System.out.println("Enter a rating.");
                inI1 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter a score.");
                inD1 = sc.nextDouble();
                sc.nextLine();
                
                player = new Player(ID, getPlayer(ID).getFirstName(), getPlayer(ID).getLastName(), getPlayer(ID).getCountry(), getPlayer(ID).getBirthday(), getPlayer(ID).getRating(), getPlayer(ID).getScore());
                playerDAO.delete(player);
                
                addPlayer(ID, inS1, inS2, inS3, inS4, inI1, inD1);
                
            }
            
            if (UserInput == 17){ //update game
                
                System.out.println("Enter an ID.");
                ID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter white's player ID.");
                inI1 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter blacks's player ID.");
                inI2 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter a FEN.");
                inS1 = sc.nextLine();
                System.out.println("Enter a clock value.");
                inS2 = sc.nextLine();
                System.out.println("Enter the tournament ID.");
                inI3 = sc.nextInt();
                sc.nextLine();
                
                game = new Game(ID, getGame(ID).getWhitePlayerID(), getGame(ID).getBlackPlayerID(), getGame(ID).getFEN(), getGame(ID).getClock(), getGame(ID).getTournamentID());
                gameDAO.delete(game);
                
                addGame(ID, inI1, inI2, inS1, inS2, inI3);
                
            }
            
            if (UserInput == 18){ //update playerlist
                
                System.out.println("Enter a registration ID.");
                ID = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the player ID.");
                inI1 = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the TournamentID.");
                inI2 = sc.nextInt();
                sc.nextLine();
                
                playerList = new PlayerList(ID, getPlayerList(ID).getPlayerID(), getPlayerList(ID).getTournamentID());
                playerListDAO.delete(playerList);
                
                addPlayerList(ID, inI1, inI2);
                
            }
            
            

            

            
        }
        
       
       }
    
    public static int[] getSlice(int[] array, int startIndex, int endIndex)   {   
        // Get the slice of the Array   
        int[] slicedArray = new int[endIndex - startIndex];   
        //copying array elements from the original array to the newly created sliced array  
        for (int i = 0; i < slicedArray.length; i++)   
        {   
        slicedArray[i] = array[startIndex + i];   
        }   
        //returns the slice of an array  
        return slicedArray;   
        }   
    
    static void addTournament(int TournamentID, String TournamentName, String Location, String TimeControl) {
        Tournament tournament;
        tournament = new Tournament(TournamentID, TournamentName, Location, TimeControl);
        tournamentDAO.insert(tournament);
    }
    
    static void addPlayer(int PlayerID, String FirstName, String LastName, String Country, String Birthday, int Rating, double Score) {
        Player player;
        player = new Player(PlayerID, FirstName, LastName, Country, Birthday, Rating, Score);
        playerDAO.insert(player);
    }

    static void addGame(int GameID, int WhitePlayerID, int BlackPlayerID, String FEN, String Clock, int TournamentID) {
        Game game;
        game = new Game(GameID, WhitePlayerID, BlackPlayerID, FEN, Clock, TournamentID);
        gameDAO.insert(game);
    }

    static void addPlayerList(int RegistrationID, int PlayerID, int TournamentID) {
        PlayerList playerList;
        playerList = new PlayerList(RegistrationID, PlayerID, TournamentID);
        playerListDAO.insert(playerList);
    }





    
    static Tournament getTournament(int TournamentID) {
        Optional<Tournament> tournament = tournamentDAO.get(TournamentID);
        return tournament.orElseGet(() -> new Tournament(-1, "Non-exist", "Non-exist", "Non-exist"));
    }
    
    static Player getPlayer(int PlayerID) {
        Optional<Player> player = playerDAO.get(PlayerID);
        return player.orElseGet(() -> new Player(-1, "Non-exist", "Non-exist", "Non-exist", "Non-exist", -1, -1.0));
    }

    static Game getGame(int GameID) {
        Optional<Game> game = gameDAO.get(GameID);
        return game.orElseGet(() -> new Game(-1, -1, -1, "Non-exist", "Non-exist", -1));
    }

    static PlayerList getPlayerList(int RegistrationID) {
        Optional<PlayerList> playerList = playerListDAO.get(RegistrationID);
        return playerList.orElseGet(() -> new PlayerList(-1, -1, -1));
    }


    
    
    static void printTournaments() {
        List<String> headers = tournamentDAO.getColumnNames();
        int numberCols = headers.size();
        //Print column names as header
        for (int i = 0; i < numberCols; i++) {
            String header = headers.get(i);
            System.out.printf("%25s", header);
        }
        System.out.println();
        //Print the results
        List<Tournament> tournaments = tournamentDAO.getAll();
        int numberRows = tournaments.size();
        for (int i = 0; i < numberRows; i++) {
            System.out.printf("%25s%25s%25s%25s", tournaments.get(i).getTournamentID(), tournaments.get(i).getTournamentName(), tournaments.get(i).getLocation(), tournaments.get(i).getTimeControl());
            System.out.println();
        }
    }
    
    static void printPlayers() {
        List<String> headers = playerDAO.getColumnNames();
        int numberCols = headers.size();
        //Print column names as header
        for (int i = 0; i < numberCols; i++) {
            String header = headers.get(i);
            System.out.printf("%25s", header);
        }
        System.out.println();
        //Print the results
        List<Player> players = playerDAO.getAll();
        int numberRows = players.size();
        for (int i = 0; i < numberRows; i++) {
            System.out.printf("%25s%25s%25s%25s%25s%25s%25s", players.get(i).getPlayerID(), players.get(i).getFirstName(), players.get(i).getLastName(), players.get(i).getCountry(), players.get(i).getBirthday(), players.get(i).getRating(), players.get(i).getScore());
            System.out.println();
        }
    }

    static void printGames() {
        List<String> headers = gameDAO.getColumnNames();
        int numberCols = headers.size();
        //Print column names as header
        for (int i = 0; i < numberCols; i++) {
            String header = headers.get(i);
            System.out.printf("%25s", header);
        }
        System.out.println();
        //Print the results
        List<Game> games = gameDAO.getAll();
        int numberRows = games.size();
        for (int i = 0; i < numberRows; i++) {
            System.out.printf("%25s%25s%25s%25s%25s%25s", games.get(i).getGameID(), games.get(i).getWhitePlayerID(), games.get(i).getBlackPlayerID(), games.get(i).getFEN(), games.get(i).getClock(), games.get(i).getTournamentID());
            System.out.println();
        }
    }

    static void printPlayerLists() {
        List<String> headers = playerListDAO.getColumnNames();
        int numberCols = headers.size();
        //Print column names as header
        for (int i = 0; i < numberCols; i++) {
            String header = headers.get(i);
            System.out.printf("%25s", header);
        }

        System.out.println();
        //Print the results
        List<PlayerList> playerLists = playerListDAO.getAll();
        int numberRows = playerLists.size();
        for (int i = 0; i < numberRows; i++) {
            System.out.printf("%25s%25s%25s", playerLists.get(i).getRegistrationID(), playerLists.get(i).getPlayerID(), playerLists.get(i).getTournamentID());
            System.out.println();
        }
    }
}
