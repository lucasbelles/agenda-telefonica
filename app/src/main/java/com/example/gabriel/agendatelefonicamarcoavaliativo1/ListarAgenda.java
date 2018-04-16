package com.example.gabriel.agendatelefonicamarcoavaliativo1;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListarAgenda extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        BancoController crud = new BancoController(ListarAgenda.this);

        final Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[]{CriaAgenda.ID, CriaAgenda.NOME, CriaAgenda.DDD, CriaAgenda.TELEFONE};

        int[] idViews = new int[]{R.id.IdContato, R.id.IdNome, R.id.Idddd, R.id.IdTelefone};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(ListarAgenda.this,
                R.layout.itens_lista,
                cursor,
                nomeCampos,
                idViews, 0);

        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);

                String codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaAgenda.ID));

                Intent intent = new Intent(ListarAgenda.this, EditarRegistro.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });

    }

    //botao = (Button) findViewById(R.id.cadastrarNovoContato);

    public void cadastrarNovoContato(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


