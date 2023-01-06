package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTabla {
        
    public static void createNewTable() throws SQLException {
        String url = "jdbc:sqlite:flights.db";
                
        if(DatabaseUtils.tableExists(url)) return ;
                
        String sql = "CREATE TABLE IF NOT EXISTS flights (\n" +
            " DAY_OF_WEEK INTEGER,\n" +
            " DEP_TIME INTEGER,\n" +
            " DEP_DELAY INTEGER,\n" +
            " ARR_TIME INTEGER,\n" +
            " ARR_DELAY INTEGER,\n" +
            " CANCELLED INTEGER,\n" +
            " DIVERTED INTEGER,\n" +
            " AIR_TIME INTEGER,\n" +
            " DISTANCE INTEGER\n);";
        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}