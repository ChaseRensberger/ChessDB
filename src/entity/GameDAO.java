
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class GameDAO implements DAO<Game>
{   
    public GameDAO() {
        
    }
    List<Game> games;
   
    @Override
    public Optional<Game> get(int GameID) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Game WHERE GameID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, GameID);
            rs = stmt.executeQuery();
            Game game = null;
            while (rs.next()) {
                game = new Game(rs.getInt("GameID"), rs.getInt("WhitePlayerID"), rs.getInt("BlackPlayerID"), rs.getString("FEN"), rs.getString("Clock"), rs.getInt("TournamentID"));
            }
            return Optional.ofNullable(game);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
   
    @Override
    public List<Game> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        games = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Game";
            rs = db.executeQuery(sql);
            Game game = null;
            while (rs.next()) {
                game = new Game(rs.getInt("GameID"), rs.getInt("WhitePlayerID"), rs.getInt("BlackPlayerID"), rs.getString("FEN"), rs.getString("Clock"), rs.getInt("TournamentID"));
                games.add(game);
            }
            return games;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    
    
    @Override
    public void insert(Game game)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Game(GameID, WhitePlayerID, BlackPlayerID, FEN, Clock, TournamentID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setInt(1, game.getGameID());
            stmt.setInt(2, game.getWhitePlayerID());
            stmt.setInt(3, game.getBlackPlayerID());
            stmt.setString(4, game.getFEN());
            stmt.setString(5, game.getClock());
            stmt.setInt(6, game.getTournamentID());

            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new game was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    
 
    @Override
    public void update(Game game) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Game SET WhitePlayerID=?, BlackPlayerID=?, FEN=?, Clock=?, TournamentID=? WHERE GameID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setInt(1, game.getWhitePlayerID());
            stmt.setInt(2, game.getBlackPlayerID());
            stmt.setString(3, game.getFEN());
            stmt.setString(4, game.getClock());
            stmt.setInt(5, game.getTournamentID());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing game was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
  
    @Override
    public void delete(Game game) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Game WHERE GameID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, game.getGameID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An game was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    
    
    
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Game WHERE GameID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }
}
