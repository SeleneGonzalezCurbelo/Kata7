package control;

import java.util.Arrays;
import java.util.List;

import model.Flight;
import model.Histogram;

public class HistogramBuilder {
    private final Iterable<Flight> flights;
    
    public HistogramBuilder(List<Flight> flights) {
        this.flights = flights;
    }
    
    public Histogram build(String type, String filterValues) {
        Histogram histogram = new Histogram();
        String filterType = filterValues.split(":")[0];
        String filterCriteria = filterValues.split(":")[1];
        
        histogram.setDimension(type);
        histogram.setFilters(Arrays.asList(filterType + ": " + filterValues.split(":")[1]));
        
        for (Flight flight : this.flights) {
            boolean filterMatch = true;
            if (filterCriteria.contains(",")) {
                List<String> criteriaList = Arrays.asList(filterCriteria.split(","));
                if (!criteriaList.contains(Integer.toString(flight.get(filterType)))) {
                    filterMatch = false;
                }
            } else {
                if (!Integer.toString(flight.get(filterType)).equals(filterCriteria)) {
                    filterMatch = false;
                }
            }
            if (filterMatch) {
                histogram.increment(flight.get(type));
            }
        }      
        return histogram;
    }
}