package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightList {
    private List<Flight> flights;

    public FlightList() {
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public List<Flight> getFlights() {
        return this.flights;
    }
    
    public void addAllFlightTxt() {
        try (BufferedReader reader = new BufferedReader(new FileReader("flights.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int dayOfWeek = "NA".equals(fields[0]) ? 0 : Integer.parseInt(fields[0]);
                LocalTime depTime;
                if ("NA".equals(fields[1])) {
                    depTime = null;
                } else {
                    depTime = LocalTime.ofSecondOfDay(Integer.parseInt(fields[1]));
                }
                int depDelay = "NA".equals(fields[2]) ? 0 : Integer.parseInt(fields[2]);
                LocalTime arrTime;
                if ("NA".equals(fields[1])) {
                    arrTime = null;
                } else {
                    arrTime = LocalTime.ofSecondOfDay(Integer.parseInt(fields[3]));
                }
                int arrDelay = "NA".equals(fields[4]) ? 0 : Integer.parseInt(fields[4]);
                boolean cancelled = Boolean.parseBoolean(fields[5]);
                boolean diverted = Boolean.parseBoolean(fields[6]);
                int airTime = "NA".equals(fields[7]) ? 0 : Integer.parseInt(fields[7]);
                int distance = "NA".equals(fields[8]) ? 0 : Integer.parseInt(fields[8]);
                Flight flight = new Flight(dayOfWeek, depTime, depDelay, arrTime, arrDelay, cancelled, diverted, airTime, distance);
                addFlight(flight);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error al abrir el archivo flights.txt: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo flights.txt: " + ex.getMessage());
        }
    }
}