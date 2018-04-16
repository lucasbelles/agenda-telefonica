package com.example.gabriel.agendatelefonicamarcoavaliativo1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarRegistro extends AppCompatActivity {

    private EditText nome;
    private EditText ddd;
    private EditText telefone;

    private Button cancelar;
    private Button alterar;
    private Button deletar;
    // private Button novo;
    private Cursor cursor;
    private BancoController crud;
    private String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new BancoController(this);

        nome = (EditText)findViewById(R.id.edtNome);
        ddd = (EditText)findViewById(R.id.edtDDD);
        telefone = (EditText)findViewById(R.id.edtTelefone);

        alterar = (Button)findViewById(R.id.btnAlterar);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));

        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaAgenda.NOME)));
        ddd.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaAgenda.DDD)));
        telefone.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaAgenda.TELEFONE)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!nome.getText().toString().equals("") && !ddd.getText().toString().equals("") && !telefone.getText().toString().equals("")){

                    crud.alteraRegistro(Integer.parseInt(codigo),
                            nome.getText().toString(),
                            ddd.getText().toString(),
                            telefone.getText().toString()
                    );

                    nome.setText("");
                    ddd.setText("");
                    telefone.setText("");

                    Intent intent = new Intent(EditarRegistro.this, ListarAgenda.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Salvo!",
                            Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Todos os campos devem ser inseridos!", Toast.LENGTH_LONG).show();
                }
            }
        });

        // novo
       /* novo = (Button)findViewById(R.id.btnNovo);
        novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarRegistro.this, MainActivity.class);
                startActivity(intent);
            }
        }); */
       // cancelar

        cancelar = (Button)findViewById(R.id.btnCancelar);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarRegistro.this, ListarAgenda.class);
                startActivity(intent);
            }
        });

        // deletar
        deletar = (Button)findViewById(R.id.btnDeletar);

        deletar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(EditarRegistro.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(EditarRegistro.this);
                }
                builder.setTitle("Deletar registro")
                        .setMessage("Tem certeza que deseja deletar este registro?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                crud.deletaRegistro(Integer.parseInt(codigo));

                                //Chamando a ListarActivity e finalizando esta activity
                                Intent intent = new Intent(EditarRegistro.this, ListarAgenda.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(), "Deletado!",
                                        Toast.LENGTH_SHORT).show();                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                //Chamando o método no banco de dados que exclui o registro

            }
        });

    }
}
