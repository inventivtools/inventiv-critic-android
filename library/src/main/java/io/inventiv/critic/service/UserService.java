package io.inventiv.critic.service;

import java.util.List;

import io.inventiv.critic.client.Configuration;
import io.inventiv.critic.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    // TODO move these static headers to a request interceptor.
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: " + Configuration.USER_AGENT
    })
    @POST("/users/login")
    Call<User> login(@Body User.Wrapper wrapper);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @GET("/users/profile")
    Call<User> profile();

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @POST("/users/")
    Call<User> create(@Body User.Wrapper wrapper);
}
