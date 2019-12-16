package com.example.homes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailHome extends AppCompatActivity {

    private Homes Item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);
        //lista recibida de serializable
      Item = (Homes) getIntent().getSerializableExtra("objetoData");
        //boton para volver a lista de casas en vecindario
        Button volver = findViewById(R.id.btnBack);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailHome.this,DetailsItem.class);
                startActivity(intent);
            }
        });


    }

    //llamadas
     private void initCall() {
        Button btn = findViewById(R.id.btnLlamar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
            }
        });
    }
    public void callPhone () {
        TextView phone = findViewById(R.id.contacto);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText()));
        startActivity(intent);
    }
}
