package io.inventiv.critic.service;

import java.util.List;

import io.inventiv.critic.client.Configuration;
import io.inventiv.critic.model.Report;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ReportService {

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @GET("/api/v1/reports/{id}")
    Call<Report> get(@Path("id") Long id);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @GET("/api/v1/reports/")
    Call<List<Report>> list(@Query("page") Long page);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @Multipart
    @POST("/api/v1/reports/")
    Call<Report> create(@Part List<MultipartBody.Part> parts);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @PUT("/api/v1/reports/{id}")
    Call<Report> update(@Path("id") Long id, @Body Report report);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @DELETE("/api/v1/reports/{id}")
    Call<ResponseBody> delete(@Path("id") Long id);
}
