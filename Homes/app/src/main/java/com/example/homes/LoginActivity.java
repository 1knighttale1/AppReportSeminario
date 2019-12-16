package com.example.homes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.homes.Host.utilidades;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_IN_CODE = 0;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private ProgressBar progressBar;
    private final int CODE_PERMISSION = 1000;

    /**LOGIN API*/
    EditText email,pass;
    Button reg,log;
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
// codigo para btn google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))  /*Para obtener token*/
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signInButton = (SignInButton) findViewById(R.id.btngoogle);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });
        //Codigo para firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    goRegisterScreen();
                }
            }
        };

        //Codigo btn Registar

        Button btnRegister = findViewById(R.id.btnregistrar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterClientActivity.class);
                startActivity(intent);
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //LOGIN
        email=findViewById(R.id.Etemail);
        pass=findViewById(R.id.Etpassword);
        reg=findViewById(R.id.btnregistrar);
        log=findViewById(R.id.btnconectar);
        reg.setOnClickListener(this);
        log.setOnClickListener(this);
        con=this;

        //Permisos
        requestPermission();

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnregistrar){
            Intent intent=new Intent(con,RegisterClientActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnconectar){
            conectar();
        }
    }

    private void conectar() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams req = new RequestParams();
        req.put("email", email.getText().toString());
        req.put("password", pass.getText().toString());

        client.post(utilidades.ip+"/api/v1.0/login", req, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String res=response.getString("message");
                    if(res.equals("autenticacion exitosa")){
                        utilidades.token=response.getString("token");
                        Toast.makeText(con,""+res,Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(con,FragmentsMapsActivity.class);
                        startActivity(in);
                    }else{
                        Toast.makeText(con,""+res,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            firebaseAuthWhitGoogle(result.getSignInAccount());
        }else{
            Toast.makeText(this, "No se puede conectar", Toast.LENGTH_SHORT).show();
        }

    }

    private void firebaseAuthWhitGoogle(final GoogleSignInAccount signInAccount) {

        progressBar.setVisibility(View.VISIBLE);
        signInButton.setVisibility(View.GONE);

        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    signInButton.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "No se pudo autenticar con Firebase", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void goRegisterScreen() {
        Intent intent = new Intent(this, RegisterGoogleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    //stop para firebase
    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuthListener !=null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    //Permisos

    public Boolean checkPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}, CODE_PERMISSION);
        } else {
            if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                    checkPermission(Manifest.permission.CALL_PHONE)) {
                Toast.makeText(this, "Los permisos fuerón otorgados", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == CODE_PERMISSION) {
            if (grantResults.length > 0){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso de almacenamiento otorgado",
                            Toast.LENGTH_SHORT).show();
                } else {
                }
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso de almacenamiento de llamadas atorgado",
                            Toast.LENGTH_SHORT).show();
                } else {
                }
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso de Localizacion atorgado",
                            Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        }
    }

}
