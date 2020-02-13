package com.romain.app12sqlite;

import androidx.annotation.NonNull;

import java.util.Date;

public class ScoreData {

    private int idScore;
    private String name;
    private int score;
    private Date when;

    // Ajout d'un constructeur
    public ScoreData(int idScore, String name, int score, Date when) {
        this.setiDScore( idScore );
        this.setName( name );
        this.setScore( score );
        this.setWhen( when );
    }

    // Génération des getters et de setters
    public int getiDScore() {
        return idScore;
    }

    public void setiDScore(int iDScore) {
        this.idScore = iDScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @NonNull
    @Override
    public String toString() {
        return idScore + ": " + name + " -> " + score + " at " + when.toString();
    }
}
