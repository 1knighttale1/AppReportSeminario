package com.example.homes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAgente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_agente);
        //boton ir a registro
        Button regHome = findViewById(R.id.btnRegCasa);
        regHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAgente.this, RegisterHome.class);
                startActivity(intent);
            }
        });

        //boton salir app
        Button salir = findViewById(R.id.btnSalir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAgente.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
