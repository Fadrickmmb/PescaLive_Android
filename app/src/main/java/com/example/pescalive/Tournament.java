package com.example.pescalive;

import java.util.List;

public class Tournament {
    private String tournamentId;
    private String name;
    private String clubName;
    private List<Round> rounds;
    private List<Team> teams;


    public Tournament(String name, String clubName, List<Round> rounds, String tournamentId) {
        this.name = name;
        this.clubName = clubName;
        this.rounds = rounds;
        this.tournamentId = tournamentId;
    }

    public Tournament() {
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
