package io.inventiv.critic.service;

import io.inventiv.critic.client.Configuration;
import io.inventiv.critic.model.Attachment;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface AttachmentService {

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @GET("/attachments/{id}")
    Call<Attachment> get(@Path("id") Long id);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @GET("/attachments/")
    Call<List<Attachment>> list(@Query("page") Long page);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @POST("/attachments/")
    Call<Attachment> create(@Body Attachment attachment);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @PUT("/attachments/{id}")
    Call<Attachment> update(@Path("id") Long id, @Body Attachment attachment);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @DELETE("/attachments/{id}")
    Call<ResponseBody> delete(@Path("id") Long id);
}
