package com.example.homes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class DetailsItem extends AppCompatActivity {

    private Entidad Item;

    private ListView lvHomes;
    private HomesAdapter adaptor;
    private ArrayList<Homes> arrayHomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_item);
        //recibiendo de ListActivity
        Item = (Entidad) getIntent().getSerializableExtra("objetoData");



        //Lista de Casas del vecindario
        lvHomes = findViewById(R.id.lvHomes);
        adaptor = new HomesAdapter(GetArrayItems() ,this);
        lvHomes.setAdapter(adaptor);

        lvHomes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DetailsItem.this,DetailHome.class);
                startActivity(intent);
            }
        });

    }

    private ArrayList<Homes> GetArrayItems(){
        ArrayList<Homes> listHomes = new ArrayList<>();

        listHomes.add(new Homes( R.drawable.casa1,"Potosi"));
        listHomes.add(new Homes(R.drawable.casa2,"Potosi"));
        listHomes.add(new Homes(R.drawable.casa3,"Potosi"));
        return listHomes;
    }
}
