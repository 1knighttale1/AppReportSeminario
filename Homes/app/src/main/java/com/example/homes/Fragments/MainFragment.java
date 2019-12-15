package com.example.homes.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.homes.ListActivity;
import com.example.homes.LoginActivity;
import com.example.homes.MainActivity;
import com.example.homes.R;
import com.example.homes.RegisterGoogleActivity;
import com.example.homes.SearchActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment /*implements GoogleApiClient.OnConnectionFailedListener*/{
    private View rootView;

    Button btnlista;
    Button btnSearch;
    Button btnSalir;
   /* GoogleApiClient googleApiClient;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;*/

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //boton para redireccionar a listas
        btnlista = rootView.findViewById(R.id.btnList);
        btnlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), ListActivity.class);
                startActivity(intent);
                //Toast.makeText(rootView.getContext(),"Presiona",Toast.LENGTH_SHORT).show();
            }
        });

        //boton para ir a formulario de busqueda
        btnSearch = rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        btnSalir = rootView.findViewById(R.id.btnExit);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), RegisterGoogleActivity.class);
                startActivity(intent);

            }
        });



        return rootView;

    }

}
