package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistogramData {
    private String dimension;
    private Map<String, String> filters;
    private List<HistogramValue> values;

    public HistogramData() {
        this.values = new ArrayList<>();
        this.filters = new HashMap<>();
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    public List<HistogramValue> getValues() {
        return values;
    }

    public void setValues(List<HistogramValue> values) {
        this.values = values;
    }
}

