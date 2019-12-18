package com.example.homes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homes.Host.utilidades;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.example.homes.Host.utilidades.Rhome;

public class RegisterHome extends AppCompatActivity {


    EditText ciudad,tipo,condominio, departamentos, estado, habitaciones, baños,superficie;
    EditText calle, precio, descripcion, fecha_construccion, lat, lng, zona, colegio, contacto;

    Button save, volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_home);

        ciudad=findViewById(R.id.Rciudad);
        tipo = findViewById(R.id.Rtipo);
        condominio = findViewById(R.id.Rcondominio);
        departamentos = findViewById(R.id.Rdepartamento);
        estado = findViewById(R.id.Restado);
        baños = findViewById(R.id.Rbaños);
        habitaciones = findViewById(R.id.Rhabitaciones);
        superficie = findViewById(R.id.Rsuperficie);
        calle = findViewById(R.id.Rcalle);
        precio = findViewById(R.id.Rprecio);
        descripcion = findViewById(R.id.Rdescripcion);
        fecha_construccion = findViewById(R.id.RfechaCons);
        lat = findViewById(R.id.Rlatidud);
        lng = findViewById(R.id.Rlongitud);
        zona = findViewById(R.id.Rzona);
        colegio = findViewById(R.id.Rcolegio);
        contacto = findViewById(R.id.Rcontacto);


        save = findViewById(R.id.btnguardar);
        volver = findViewById(R.id.btnVolerInicioA);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendhome();

            }
        });

        volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RegisterHome.this,MenuAgente.class);
                startActivity(in);
            }
        });

    }

    private void sendhome() {
        AsyncHttpClient clien =  new AsyncHttpClient();
        RequestParams req=new RequestParams();
        req.put("ciudad", ciudad.getText().toString());
        req.put("tipo", tipo.getText().toString());
        //req.put("condominio", condominio.getText().toString());
        req.put("departamentos", departamentos.getText().toString());
        req.put("estado", estado.getText().toString());
        req.put("habitaciones", habitaciones.getText().toString());
        req.put("baños", baños.getText().toString());
        req.put("superficie", superficie.getText().toString());
        req.put("calle", calle.getText().toString());
        req.put("precio", precio.getText().toString());
        req.put("descripcion", descripcion.getText().toString());
        req.put("fecha_construccion", fecha_construccion.getText().toString());
        req.put("lat", lat.getText().toString());
        req.put("lng", lng.getText().toString());
        req.put("zona", zona.getText().toString());
        req.put("colegio", colegio.getText().toString());
        req.put("contacto", contacto.getText().toString());

        clien.post(Rhome+"",req, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Intent in=new Intent(RegisterHome.this,MenuAgente.class);
                Toast.makeText(RegisterHome.this,"GGGaaa",Toast.LENGTH_SHORT).show();
                startActivity(in);
            }
        });

    }

}
