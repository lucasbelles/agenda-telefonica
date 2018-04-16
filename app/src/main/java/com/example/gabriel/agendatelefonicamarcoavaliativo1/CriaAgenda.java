package com.example.gabriel.agendatelefonicamarcoavaliativo1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaAgenda extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "agenda.db";
    public static final String TABELA = "contato";
    public static final int VERSAO = 1;

    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String DDD = "ddd";
    public static final String TELEFONE = "telefone";


    public CriaAgenda(Context context){

        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ TABELA +" ("
                + ID + " integer primary key autoincrement,"
                + NOME + " text, "
                + DDD + " text, "
                + TELEFONE + " text "
                +")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}

