package com.example.gabriel.agendatelefonicamarcoavaliativo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botao;
    private EditText nome;
    private EditText ddd;
    private EditText telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.btnCadastrar);
        nome = (EditText) findViewById(R.id.edtNome);
        nome.requestFocus();
        ddd = (EditText) findViewById(R.id.edtDDD);
        telefone = (EditText) findViewById(R.id.edtTelefone);

        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                BancoController crud = new BancoController(MainActivity.this);


                if(!nome.getText().toString().equals("") && !ddd.getText().toString().equals("") && !telefone.getText().toString().equals("")){

                    String nomeString = nome.getText().toString();
                    String dddString = ddd.getText().toString();
                    String telefoneString = telefone.getText().toString();

                    String resultado = crud.insereDado(nomeString, dddString, telefoneString);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                    nome.setText("");
                    nome.requestFocus();
                    ddd.setText("");
                    telefone.setText("");

                    Intent chamalistar = new Intent(MainActivity.this, ListarAgenda.class);
                    startActivity(chamalistar);

                }else{
                    Toast.makeText(getApplicationContext(), "Todos os campos devem ser inseridos!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void btnCancelar(View view) {
        Intent intent = new Intent(this, ListarAgenda.class); //chama outra tela

        startActivity(intent);

        nome.setText("");
        ddd.setText("");
        telefone.setText("");
    }
}