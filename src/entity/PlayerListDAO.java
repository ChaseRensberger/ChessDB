
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PlayerListDAO implements DAO<PlayerList>
{   
    public PlayerListDAO() {
        
    }
    List<PlayerList> playerLists;
   
    @Override
    public Optional<PlayerList> get(int RegistrationID) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM PlayerList WHERE RegistrationID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, RegistrationID);
            rs = stmt.executeQuery();
            PlayerList playerList = null;
            while (rs.next()) {
                playerList = new PlayerList(rs.getInt("RegistrationID"), rs.getInt("PlayerID"), rs.getInt("TournamentID"));
            }
            return Optional.ofNullable(playerList);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
   
    @Override
    public List<PlayerList> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        playerLists = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PlayerList";
            rs = db.executeQuery(sql);
            PlayerList playerList = null;
            while (rs.next()) {
                playerList = new PlayerList(rs.getInt("RegistrationID"), rs.getInt("PlayerID"), rs.getInt("TournamentID"));
                playerLists.add(playerList);
            }
            return playerLists;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    
    
    @Override
    public void insert(PlayerList playerList)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO PlayerList(RegistrationID, PlayerID, TournamentID) VALUES (?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setInt(1, playerList.getRegistrationID());
            stmt.setInt(2, playerList.getPlayerID());
            stmt.setInt(3, playerList.getTournamentID());
            
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new playerList was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    
 
    @Override
    public void update(PlayerList playerList) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE PlayerList SET PlayerID=?, TournamentID=? WHERE RegistrationID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
        
            stmt.setInt(1, playerList.getPlayerID());
            stmt.setInt(2, playerList.getTournamentID());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing playerList was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
  
    @Override
    public void delete(PlayerList playerList) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM PlayerList WHERE RegistrationID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, playerList.getRegistrationID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An playerList was deleted successfully!");
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
            String sql = "SELECT * FROM PlayerList WHERE RegistrationID = -1";//We just need this sql query to get the column headers
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
