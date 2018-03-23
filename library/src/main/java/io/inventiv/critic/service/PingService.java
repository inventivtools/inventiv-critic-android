package io.inventiv.critic.service;

import com.google.gson.JsonObject;

import io.inventiv.critic.model.AppInstall;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PingService {

    @POST("/api/v2/ping")
    Call<AppInstall.Wrapper> create(@Body JsonObject parts);
}
