package model;

import java.time.LocalTime;

public class Flight {
    private int dayOfWeek;
    private LocalTime depTime;
    private int depDelay;
    private LocalTime arrTime;
    private int arrDelay;
    private boolean cancelled;
    private boolean diverted;
    private int airTime;
    private int distance;

    public Flight(int dayOfWeek, LocalTime depTime, int depDelay, LocalTime arrTime, int arrDelay, boolean cancelled, boolean diverted, int airTime, int distance) {
        this.dayOfWeek = dayOfWeek;
        this.depTime = depTime;
        this.depDelay = depDelay;
        this.arrTime = arrTime;
        this.arrDelay = arrDelay;
        this.cancelled = cancelled;
        this.diverted = diverted;
        this.airTime = airTime;
        this.distance = distance;
    }

    public Flight() {
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getDepTime() {
        return depTime;
    }

    public int getDepDelay() {
        return depDelay;
    }

    public LocalTime getArrTime() {
        return arrTime;
    }

    public int getArrDelay() {
        return arrDelay;
    }

    public boolean getCancelled() {
        return cancelled;
    }

    public boolean getDiverted() {
        return diverted;
    }

    public int getAirTime() {
        return airTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setDepTime(LocalTime depTime) {
        this.depTime = depTime;
    }

    public void setDepDelay(int depDelay) {
        this.depDelay = depDelay;
    }

    public void setArrTime(LocalTime arrTime) {
        this.arrTime = arrTime;
    }

    public void setArrDelay(int arrDelay) {
        this.arrDelay = arrDelay;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setDiverted(boolean diverted) {
        this.diverted = diverted;
    }

    public void setAirTime(int airTime) {
        this.airTime = airTime;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    public int get(String field){
        switch (field) {
            case "dayOfWeek":
                return getDayOfWeek();
            case "airTime":
                return getAirTime();
            case "depDelay":
                return getDepDelay();
            case "arrDelay":
                return getArrDelay();
            case "distance":
                return getDistance();
            default:
                break;
        }
        return 0;
    } 
}