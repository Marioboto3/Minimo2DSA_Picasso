package com.example.minimo2dsa;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    @GET("users/{Username}")
    Call<User> userInfo (@Path("Username") String username);

    @GET("users/{Username}/followers")
    Call<List<Follower>> followersInfo (@Path("Username") String username);

}
