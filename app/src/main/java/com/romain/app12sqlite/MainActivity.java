package com.romain.app12sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView scoresView;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoresView = (TextView) findViewById(R.id.scoresView);

        // On créé une nouvelle instance de DatabaseManager avec this en paramètre car il lui faut un context
        // Car la classe Activity dont on dérive hérite bien de Context
        databaseManager = new DatabaseManager(this);

        // On créé des données bidons
        databaseManager.insertScore("Bruno", 100);
        databaseManager.insertScore("Rigolo", 50);
        databaseManager.insertScore("Pipo", 500);
        databaseManager.insertScore("Paulo", 450);

        List<ScoreData> scores = databaseManager.readTop10();
        for(ScoreData score: scores) scoresView.append( scores.toString() + "\n\n" );

        // On ferme la connexion à la BDD
        databaseManager.close();
    }
}
