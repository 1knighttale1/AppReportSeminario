package com.example.homes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailsItem extends AppCompatActivity {

    private Entidad Item;

    private ListView lvHomes;
    private HomesAdapter adaptor;


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

    }

    private ArrayList<Homes> GetArrayItems(){
        ArrayList<Homes> listHomes = new ArrayList<>();

        listHomes.add(new Homes( R.drawable.casa1,"Potosi"));
        listHomes.add(new Homes(R.drawable.casa2,"Potosi"));
        listHomes.add(new Homes(R.drawable.casa3,"Potosi"));
        return listHomes;
    }
}
