
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TournamentDAO implements DAO<Tournament>
{   
    public TournamentDAO() {
        
    }
    List<Tournament> tournaments;
   
    @Override
    public Optional<Tournament> get(int TournamentID) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Tournament WHERE TournamentID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, TournamentID);
            rs = stmt.executeQuery();
            Tournament tournament = null;
            while (rs.next()) {
                tournament = new Tournament(rs.getInt("TournamentID"), rs.getString("TournamentName"), rs.getString("Location"), rs.getString("TimeControl"));
            }
            return Optional.ofNullable(tournament);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
   
    @Override
    public List<Tournament> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        tournaments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Tournament";
            rs = db.executeQuery(sql);
            Tournament tournament = null;
            while (rs.next()) {
                tournament = new Tournament(rs.getInt("TournamentID"), rs.getString("TournamentName"), rs.getString("Location"), rs.getString("TimeControl"));
                tournaments.add(tournament);
            }
            return tournaments;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    
    
    @Override
    public void insert(Tournament tournament)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Tournament(TournamentID, TournamentName, Location, TimeControl) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setInt(1, tournament.getTournamentID());
            stmt.setString(2, tournament.getTournamentName());
            stmt.setString(3, tournament.getLocation());
            stmt.setString(4, tournament.getTimeControl());
           
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new tournament was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    
 
    @Override
    public void update(Tournament tournament) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Tournament SET TournamentName=?, Location=?, TimeControl=? WHERE TournamentID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);

            stmt.setString(1, tournament.getTournamentName());
            stmt.setString(2, tournament.getLocation());
            stmt.setString(3, tournament.getTimeControl());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing tournament was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
  
    @Override
    public void delete(Tournament tournament) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Tournament WHERE TournamentID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, tournament.getTournamentID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An tournament was deleted successfully!");
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
            String sql = "SELECT * FROM Tournament WHERE TournamentID = -1";//We just need this sql query to get the column headers
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
