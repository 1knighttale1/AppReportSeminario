package com.example.homes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

   private ListView lvItems;
   private Adaptador adaptador;
   private ArrayList<Entidad> arrayEntidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvItems = findViewById(R.id.lvItems);
        arrayEntidad = GetArrayItems();
        adaptador = new Adaptador(arrayEntidad, this);
        lvItems.setAdapter(adaptador);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this,DetailsItem.class);
                intent.putExtra("objetoData",arrayEntidad.get(i));
                startActivity(intent);
            }
        });

    }

    private ArrayList<Entidad> GetArrayItems(){
        ArrayList<Entidad> listItems = new ArrayList<>();

        listItems.add(new Entidad("Murillo","Potosi"));
        listItems.add(new Entidad("Delicias","Potosi"));
        return listItems;
    }
}
