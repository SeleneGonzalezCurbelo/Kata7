package control;

import java.sql. Connection;
import java.sql.DatabaseMetaData;
import java.sql. ResultSet;
import java.sql.SQLException;
import java.sql. Statement;
import java.sql.DriverManager;

public class DatabaseUtils {
    public static boolean isTableEmpty(Connection connection) {
        try ( Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM flights")) {
          if (rs.next()) {
            int count = rs.getInt(1);
            if (count == 0) {
                return true;
            }
          }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public static boolean tableExists(String jdbcUrl) throws SQLException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "flights", null);
            if (tables.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
