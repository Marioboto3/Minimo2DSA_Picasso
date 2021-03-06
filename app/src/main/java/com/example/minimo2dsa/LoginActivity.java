package com.example.minimo2dsa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    RecyclerView recycler;
    RecyclerView.Adapter mAdapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/user")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    API api = retrofit.create((API.class));

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "Mario:Contraseña", "Carlo:Lala"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


    }
}
