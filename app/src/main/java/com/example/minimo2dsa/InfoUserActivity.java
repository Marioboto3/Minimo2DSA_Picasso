package com.example.minimo2dsa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoUserActivity extends AppCompatActivity {

    List<Follower> listDatos = new ArrayList<>();
    RecyclerView recycler;
    RecyclerView.Adapter mAdapter;

    TextView repos_text;
    TextView following_text;
    ImageView image;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    API api = retrofit.create((API.class));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infouseractivity);

        recycler = findViewById(R.id.my_recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler.setHasFixedSize(true);

        repos_text = findViewById(R.id.Repos_text);
        following_text = findViewById(R.id.following_text);
        image = findViewById(R.id.miimagen);

        String tot;
        String tit;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                tit= null;
            } else {
                tit=extras.getString("User");
            }
        } else {
            tit =(String) savedInstanceState.getSerializable("User");
        }
        tot=tit;

        Call<List<Follower>> call = api.followersInfo(tot);
        call.enqueue(new Callback<List<Follower>>() {
            @Override
            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                if (!response.isSuccessful()) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast por defecto", Toast.LENGTH_SHORT);
                    toast1.show();
                    return;
                }
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "oleole", Toast.LENGTH_SHORT);
                toast1.show();
                listDatos = response.body();
                mAdapter = new MyAdapter(listDatos, InfoUserActivity.this);
                recycler.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<List<Follower>> call, Throwable t) {
            }
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Toast onFailure", Toast.LENGTH_SHORT);

        });

        Call<User> call1 = api.userInfo(tot);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast por defecto", Toast.LENGTH_SHORT);
                    toast1.show();
                    return;
                }
            User user = response.body();
            repos_text.setText(Integer.toString(user.getPublic_repos()));
            following_text.setText(Integer.toString(user.getFollowing()));
            Picasso.get().load(user.getAvatar_url()).into(image);


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Toast onFailure", Toast.LENGTH_SHORT);

        });

    }
}
