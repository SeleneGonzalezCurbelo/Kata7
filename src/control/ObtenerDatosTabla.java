package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import model.Flight;
import model.FlightList;

public class ObtenerDatosTabla {
    private static final String url = "jdbc:sqlite:flights.db";

    public List<Flight> getFlights(String type, String field) {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT * FROM flights";
            if (type != null && field != null) {
                sql += " WHERE " + type + " = '" + field + "'";
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            FlightList flights = new FlightList();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setDayOfWeek(rs.getInt("DAY_OF_WEEK"));
                flight.setDepTime((LocalTime) rs.getObject("DEP_TIME"));
                flight.setDepDelay(rs.getInt("DEP_DELAY"));
                flight.setArrTime((LocalTime) rs.getObject("ARR_TIME"));
                flight.setArrDelay(rs.getInt("ARR_DELAY"));
                flight.setCancelled(rs.getBoolean("CANCELLED"));
                flight.setDiverted(rs.getBoolean("DIVERTED"));
                flight.setAirTime(rs.getInt("AIR_TIME"));
                flight.setDistance(rs.getInt("DISTANCE"));
                flights.addFlight(flight);
            }
            return flights.getFlights();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Flight> getAllFlights() {
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT * FROM flights";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            FlightList flights = new FlightList();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setDayOfWeek(rs.getInt("DAY_OF_WEEK"));
                flight.setDepTime(LocalTime.ofNanoOfDay(rs.getInt("DEP_TIME") * 1000000000L));
                flight.setDepDelay(rs.getInt("DEP_DELAY"));
                flight.setArrTime(LocalTime.ofNanoOfDay(rs.getInt("ARR_TIME") * 1000000000L));
                flight.setArrDelay(rs.getInt("ARR_DELAY"));
                flight.setCancelled(rs.getBoolean("CANCELLED"));
                flight.setDiverted(rs.getBoolean("DIVERTED"));
                flight.setAirTime(rs.getInt("AIR_TIME"));
                flight.setDistance(rs.getInt("DISTANCE"));
                flights.addFlight(flight);
            }
            return flights.getFlights();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
