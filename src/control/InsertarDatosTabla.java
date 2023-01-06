package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Flight;
import model.FlightList;

public class InsertarDatosTabla {
    public Connection connect() {
        String url = "jdbc:sqlite:flights.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert() throws IOException, SQLException {        
        String sql = "INSERT INTO flights(DAY_OF_WEEK, DEP_TIME, DEP_DELAY, ARR_TIME, "
        + "ARR_DELAY, CANCELLED, DIVERTED, AIR_TIME, DISTANCE) "
        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = connect();
        if(DatabaseUtils.isTableEmpty(conn)){
            FlightList flights_ = new FlightList();
            flights_.addAllFlightTxt();
            List<Flight> flights = flights_.getFlights();
            if (conn == null) {
                return;
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                flights.forEach((flight) -> {
                    try {
                        pstmt.setObject(1, flight.getDayOfWeek());
                        pstmt.setObject(2, flight.getDepTime());
                        pstmt.setInt(3, flight.getDepDelay());
                        pstmt.setObject(4, flight.getArrTime());
                        pstmt.setInt(5, flight.getArrDelay());
                        pstmt.setBoolean(6, flight.getCancelled());
                        pstmt.setBoolean(7, flight.getDiverted());
                        pstmt.setInt(8, flight.getAirTime());
                        pstmt.setInt(9, flight.getDistance());
                        pstmt.executeUpdate();
                    } catch (SQLException ex) {
                        System.out.println("Error al insertar datos: " + ex.getMessage());
                    }
                });
            }
        }
        conn.close();
    }
}