package io.inventiv.critic.client;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import io.inventiv.critic.Critic;
import io.inventiv.critic.model.AppInstall;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PingCreator {

    public void create(Context context) throws PingCreationException {

        JsonObject body = new JsonObject();
        body.addProperty("api_token", Critic.getProductAccessToken());
        body.add("app", Critic.getAppJson());
        body.add("device", Critic.getDeviceJson());
        body.add("device_status", Critic.getDeviceStatusJson());

        Client.pingService().create(body).enqueue(new Callback<AppInstall.Wrapper>() {
            @Override
            public void onResponse(Call<AppInstall.Wrapper> call, Response<AppInstall.Wrapper> response) {

                if (response.code() != 200) {
                    throw new PingCreationException("Invalid response code: " + response.code());
                }

                AppInstall.Wrapper appInstallWrapper = response.body();
                if (appInstallWrapper.getAppInstall() == null) {
                    throw new PingCreationException("No report returned from server.");
                }

                Log.w("HMMM", appInstallWrapper.getAppInstall().toString());
                Critic.setAppInstallId(appInstallWrapper.getAppInstall().getId());
            }

            @Override
            public void onFailure(Call<AppInstall.Wrapper> call, Throwable t) {
                throw new PingCreationException("Invalid response from server: " + t.getMessage(), t);
            }
        });
    }

    public static class PingCreationException extends RuntimeException {

        public PingCreationException(String message) {
            super(message);
        }

        public PingCreationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
