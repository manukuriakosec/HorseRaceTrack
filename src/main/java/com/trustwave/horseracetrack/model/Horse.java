package com.trustwave.horseracetrack.model;

public class Horse {

    private int id;
    private String name;
    private int odds;
    private String status;

    public Horse(int id, String name, int odds, String status) {
        this.id = id;
        this.name = name;
        this.odds = odds;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOdds() {
        return odds;
    }

    public void setOdds(int odds) {
        this.odds = odds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
