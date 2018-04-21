package com.example.gabriel.agendatelefonicamarcoavaliativo1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PreferenciasLigacao extends AppCompatActivity {

    public static final String PREFS_NAME = "preferencias_ligacao";

    private EditText edtCodCidade;
    private EditText edtCodOperadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias_ligacao);

        edtCodCidade = findViewById(R.id.edtCodCidade);
        edtCodOperadora = findViewById(R.id.edtCodOperadora);
    }

    public void salvar(View view){

        if(!edtCodCidade.getText().toString().isEmpty() && !edtCodOperadora.getText().toString().isEmpty()){

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = settings.edit();
            editor.putString("codigoCidade", edtCodCidade.getText().toString());
            editor.putString("codigoOperadora", edtCodOperadora.getText().toString());
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(getApplicationContext(), "Todos os campos precisam ser inseridos!", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
