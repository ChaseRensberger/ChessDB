
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PlayerDAO implements DAO<Player>
{   
    public PlayerDAO() {
        
    }
    List<Player> players;
   
    @Override
    public Optional<Player> get(int PlayerID) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Player WHERE PlayerID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, PlayerID);
            rs = stmt.executeQuery();
            Player player = null;
            while (rs.next()) {
                player = new Player(rs.getInt("PlayerID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Country"), rs.getString("Birthday"), rs.getInt("Rating"), rs.getDouble("Score"));
            }
            return Optional.ofNullable(player);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
   
    @Override
    public List<Player> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        players = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Player";
            rs = db.executeQuery(sql);
            Player player = null;
            while (rs.next()) {
                player = new Player(rs.getInt("PlayerID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Country"), rs.getString("Birthday"), rs.getInt("Rating"), rs.getDouble("Score"));
                players.add(player);
            }
            return players;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    
    
    @Override
    public void insert(Player player)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Player(PlayerID, FirstName, LastName, Country, Birthday, Rating, Score) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setInt(1, player.getPlayerID());
            stmt.setString(2, player.getFirstName());
            stmt.setString(3, player.getLastName());
            stmt.setString(4, player.getCountry());
            stmt.setString(5, player.getBirthday());
            stmt.setInt(6, player.getRating());
            stmt.setDouble(7, player.getScore());



            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new player was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    
 
    @Override
    public void update(Player player) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Player SET FirstName=?, LastName=?, Country=?, Birthday=?, Rating=?, Score=? WHERE PlayerID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setString(1, player.getFirstName());
            stmt.setString(2, player.getLastName());
            stmt.setString(3, player.getCountry());
            stmt.setString(4, player.getBirthday());
            stmt.setInt(5, player.getRating());
            stmt.setDouble(6, player.getScore());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing player was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
  
    @Override
    public void delete(Player player) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Player WHERE PlayerID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, player.getPlayerID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A player was deleted successfully!");
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
            String sql = "SELECT * FROM Player WHERE PlayerID = -1";//We just need this sql query to get the column headers
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
