package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Histogram<T> implements Histogram_<T>{
    
    public Map<T, Integer> map;
    private String dimension;
    private List<String> filters;
    private int binSize;
    private List<HistogramValue> values;


    public Histogram() {
        this.map =  new HashMap<>();
        this.filters = new ArrayList<>();
    }

    public Histogram(String fieldName, Map<T, Integer> map, String dimension, List<String> filters, int binSize) {
        this.map = map;
        this.dimension = dimension;
        this.filters = filters;
        this.binSize = binSize;
        this.values = new ArrayList<>();
    }

    @Override
    public Integer get(T key) {
        return map.get(key);
    }

    @Override
    public Set<T> keySet() {
        return map.keySet();
    }

    @Override
    public void increment(T key) {
        map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
    }

    public Map<T, Integer> getMap() {
        return map;
    }
    
    public void setMap(Map<T, Integer> map) {
        this.map = map;
    }


    public String getDimension() {
        return dimension;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setBinSize(int binSize) {
        this.binSize = binSize;
    }

    public void setValues(List<HistogramValue> values) {
        this.values = values;
    }
    
    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public int getBinSize() {
        return binSize;
    }
    
    public List<HistogramValue> getValues() {
        return values;
    }

}