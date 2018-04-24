package com.example.gabriel.agendatelefonicamarcoavaliativo1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListarAgenda extends AppCompatActivity {

    private ListView lista;

    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        BancoController crud = new BancoController(ListarAgenda.this);

        final Cursor cursor = crud.carregaDados();

        final String[] nomeCampos = new String[]{CriaAgenda.ID, CriaAgenda.NOME, CriaAgenda.DDD, CriaAgenda.TELEFONE};

        int[] idViews = new int[]{R.id.IdContato, R.id.IdNome, R.id.Idddd, R.id.IdTelefone};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(ListarAgenda.this,
                R.layout.itens_lista,
                cursor,
                nomeCampos,
                idViews, 0);

        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        String telefones = cursor.getString(cursor.getColumnIndexOrThrow(CriaAgenda.TELEFONE));

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

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                cursor.moveToPosition(position);

                String telefones = cursor.getString(cursor.getColumnIndexOrThrow(CriaAgenda.TELEFONE));

                //Toast.makeText(getApplicationContext(), "Telefone: " + telefones, Toast.LENGTH_LONG).show();

                Uri uri = Uri.parse("tel:"+telefones);

                Intent intent = new Intent(Intent.ACTION_CALL, uri);

                if(ActivityCompat.checkSelfPermission(ListarAgenda.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ListarAgenda.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
                startActivity(intent);
                return true;
            }
        });

    }
}