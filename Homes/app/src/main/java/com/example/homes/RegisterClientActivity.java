package com.example.homes;

import androidx.annotation.Nullable;
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

import com.example.homes.Host.utilidades;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class RegisterClientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ArrayList<String> sexo;
    /**REGISTER BASTARD*/
    EditText name,pass,email, address, phone;
    Spinner sex;
    Button save, cancel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
        /**gaa*/
        setSpinnerRegister();
        //register
        name=findViewById(R.id.user);
        pass = findViewById(R.id.password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        sex = findViewById(R.id.spnSexo);
        save = findViewById(R.id.btnSave);
        cancel = findViewById(R.id.btnCancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RegisterClientActivity.this,LoginActivity.class);
                sendRegister();
                startActivity(in);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in=new Intent(RegisterClientActivity.this,LoginActivity.class);
                startActivity(in);
            }
        });
    }



    private void sendRegister() {
        AsyncHttpClient client =  new AsyncHttpClient();
        final RequestParams req=new RequestParams();
        req.put("name", name.getText().toString());
        req.put("password", pass.getText().toString());
        req.put("email", email.getText().toString());
        req.put("address", address.getText().toString());
        req.put("phone", phone.getText().toString());
        req.put("sex", sexo.get(sex.getSelectedItemPosition()));

        client.post(utilidades.ip+"/api/v1.0/user",req, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String r=response.getString("message");
                    Toast.makeText(getApplicationContext(), "GG "+r, Toast.LENGTH_SHORT).show();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void setSpinnerRegister() {
        sexo = new ArrayList<>();
        sexo.add("Hombre");
        sexo.add("Mujer");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sexo);
        Spinner spinner = findViewById(R.id.spnSexo);
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
