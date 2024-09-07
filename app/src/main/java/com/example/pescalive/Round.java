package com.example.pescalive;

public class Round {
    private String roundId;
    private String name;
    private String date;

    public Round() {
    }

    public Round(String roundId, String name){
        this.name = name;
        this.roundId = roundId;
    }

    public Round(String name) {
        this.name = name;
    }

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
