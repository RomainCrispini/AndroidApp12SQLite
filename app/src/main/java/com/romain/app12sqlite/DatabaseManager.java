package com.romain.app12sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

// Cette classe va concentrer tous les codes relatifs à la BDD, on étend à SQLiteOpenHelper (classe abstraite)
// Donc il faut redéfinir plusieurs méthodes : onCreate, onUpgrade
// Reste une erreur en lien avec les constructeurs : dans le super, il faut un constructeur qui prend 4 paramètres
// On déclare donc deux constantes
// Les paramètres du constructeur sont les suivants : Context (qu'on ajoute), Nom DB, Factory null et Nom Version
public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Game.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // onCreate est la première fois qu'on va déployer l'appli sur un mobile, on créé une BDD
    @Override
    public void onCreate(SQLiteDatabase db) {
        // création de la table en langage SQL, à base de concaténation
        String strSQL = "create table T_Scores ("
                + " idScore integer primary key autoincrement,"
                + " name text not null,"
                + " score integer not null,"
                + " when_ integer not null"
                + ")";

        // On lance l'exécution, pour vérifier que cette méthode n'est invoquée qu'une seule fois, on fait in Log
        db.execSQL(strSQL);
        Log.i("DATABASE", "onCreate invoked");

    }

    // onUpgrade sert à mettre à jour la structure de la BDD si on passe à une nouvelle version du jeu
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // String strSQL = "alter table T_Score add column ...";
        String strSQL = "drop table T_Scores";
        db.execSQL(strSQL);
        this.onCreate(db);
        Log.i("DATABASE", "onUpgrade invoked");

    }

    // Méthode qui permet d'insérer un score dans la BDD
    public void insertScore(String name, int score){
        name = name.replace("'", "''");
        String strSQL = "insert into T_Scores (name, score, when_) values ('"
                + name + "', " + score + ", " + new Date().getTime() + ")";

        // Pour envoyer cet ordre SQL dans ma BDD, sur this (c'est le DatabaseManager), on fait un getWritable
        this.getWritableDatabase().execSQL(strSQL);
        Log.i("DATABASE", "insertScore invoked");
    }


}
