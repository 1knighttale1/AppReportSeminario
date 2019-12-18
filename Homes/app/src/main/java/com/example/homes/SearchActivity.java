package com.example.homes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.homes.Host.utilidades.Rhome;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    ArrayList<String> tipo;
    EditText canth,constA,cantb, sup, min, max;
    Spinner spntipo;
    Button result, vaciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setSpinnerHome();

        min = findViewById(R.id.PrecioMinimo);
        max = findViewById(R.id.PrecioMaximo);
        //sup = findViewById(R.id.superficie);
        spntipo = findViewById(R.id.spnCasa);
        result = findViewById(R.id.btnResults);
        vaciar = findViewById(R.id.btnVaciar);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SearchActivity.this,ResultActivity.class);
                sendResult();
                startActivity(in);
            }
        });

        vaciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in=new Intent(SearchActivity.this,SearchActivity.class);
                startActivity(in);
            }
        });

    }

    private void sendResult() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("estado", tipo.get(spntipo.getSelectedItemPosition()));
        params.put("precio", min.getText().toString());
        params.put("precio", max.getText().toString());
        //Toast.makeText(SearchActivity.this,""+params,Toast.LENGTH_SHORT).show();
        /**final ListView list = (ListView)this.findViewById(R.id.list_main);
         final ArrayList<Item> list_data = new ArrayList<Item>();*/
        client.get(Rhome+"", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                /**recuperar datos para luego poder mandar a ResultActivity*/
                //FALTAA GAA
            }
        });
    }

    private void setSpinnerHome() {
        tipo = new ArrayList<>();
        tipo.add("venta");
        tipo.add("alquier");
        tipo.add("departamento");
        tipo.add("credito");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tipo);
        Spinner spinner = findViewById(R.id.spnCasa);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
