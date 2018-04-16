package com.example.gabriel.agendatelefonicamarcoavaliativo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {

    private SQLiteDatabase db;
    private CriaAgenda banco;

    public BancoController(Context context) {
        banco = new CriaAgenda(context);
    }

    public String insereDado(String nome, String ddd, String telefone) {

        ContentValues valores;

        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put(CriaAgenda.NOME, nome);
        valores.put(CriaAgenda.DDD, ddd);
        valores.put(CriaAgenda.TELEFONE, telefone);

        long resultado = db.insert(CriaAgenda.TABELA, null, valores);
        db.close();

        if (resultado == -1) {
            return "Não foi possível inserir";
        } else {
            return "Registro inserido!";
        }
    }

    public Cursor carregaDados() {

        Cursor cursor;
        String[] campos = {banco.ID, banco.NOME, banco.DDD, banco.TELEFONE}; //RECUPERA OS CAMPOS

        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id) {

        Cursor cursor;
        String[] campos = {banco.ID, banco.NOME, banco.DDD, banco.TELEFONE};

        String where = CriaAgenda.ID + "=" + id;

        db = banco.getReadableDatabase();
        cursor = db.query(CriaAgenda.TABELA, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public void alteraRegistro(int id, String nome, String ddd, String telefone) {

        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaAgenda.ID + "=" + id;

        valores = new ContentValues();
        valores.put(CriaAgenda.NOME, nome);
        valores.put(CriaAgenda.DDD, ddd);
        valores.put(CriaAgenda.TELEFONE, telefone);

        db.update(CriaAgenda.TABELA, valores, where, null);
        db.close();
    }


    public void deletaRegistro(int id) {

        String where = CriaAgenda.ID + "=" + id;

        db = banco.getReadableDatabase();
        db.delete(CriaAgenda.TABELA, where, null);

        db.close();
    }
}