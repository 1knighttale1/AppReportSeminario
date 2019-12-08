package com.example.log_in;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.example.log_in.Service.HOST;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_IN_CODE = 777;

    /**intento 2 login*/
    EditText email, pass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**intento 2 login*/
        email=findViewById(R.id.Etemail);
        pass=findViewById(R.id.Etpassword);
        btn=findViewById(R.id.btnconectar);
        btn.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        signInButton = (SignInButton) findViewById(R.id.btngoogle);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });

        /**login
        loadcomponents();*/

    }

    /**intento 2 login*/
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.btnconectar){
            sendlogin();
        }
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnregistrar:
                intent = new Intent(this, RegisterActivity.class);
        }
        startActivity(intent);
    }
    private void sendlogin() {
        AsyncHttpClient clien=new AsyncHttpClient();
        RequestParams req=new RequestParams();
        req.put("email",email.getText().toString());
        req.put("password",pass.getText().toString());
        clien.post(HOST+"/api/v1.0/login/", req, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                try {
                    String resp=response.getString("message");
                    if(resp.equals("autenticacion exitosa")){
                        Intent in=new Intent(LoginActivity.this,MainActivity.class);
                        Service.token=response.getString("token");
                        startActivity(in);
                    }else if(resp.equals("el password es incorrecto")){
                        Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,""+connectionResult.getErrorMessage(),Toast.LENGTH_SHORT ).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_CODE){
           GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
           handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            goMainScreen();
        }else{
            Toast.makeText(this, "No se puede conectar", Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    /**login

    private void loadcomponents(){
        Button btnconectar = findViewById(R.id.btnconectar);
        Button btnregistar = findViewById(R.id.btnregistrar);
        btnconectar.setOnClickListener(this);
        btnregistar.setOnClickListener(this);
        //boton de google
        SignInButton logingoogle = findViewById(R.id.btngoogle);
        logingoogle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnconectar:{
                Toast.makeText(LoginActivity.this, "Conectando", Toast.LENGTH_SHORT).show();
                sendlogin();
                break;
            }
            case R.id.btnregistrar:{
                Intent registro = new Intent(LoginActivity.this, Register.class);
                startActivity(registro);
                break;
            }
            case R.id.btngoogle:{
                signin();
                break;
            }
        }
    }

    private void signin(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent( googleApiClient );
        startActivityForResult(intent,SIGN_IN_CODE);
    }

    private void sendlogin(){
        final EditText email = findViewById(R.id.Etemail);
        final EditText password = findViewById(R.id.Etpassword);

        AsyncHttpClient client =new AsyncHttpClient();
        final RequestParams params = new RequestParams();

        params.add("email", email.getText().toString());
        params.add("password", password.getText().toString());

        client.post(Service.LOGIN_SERVICE, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                if(response.has("token")){
                    try {
                        Service.TOKEN=response.getString("token");
                        Service.LOGIN_SERVICE=email.getText().toString();
                        Toast.makeText(LoginActivity.this, "Login Exitoso", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(main);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }*/
}
