package io.inventiv.critic.service;

import io.inventiv.critic.client.Configuration;
import io.inventiv.critic.model.Product;
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


public interface ProductService {

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @GET("/products/{id}")
    Call<Product> get(@Path("id") Long id);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @GET("/products/")
    Call<List<Product>> list(@Query("page") Long page);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @POST("/products/")
    Call<Product> create(@Body Product product);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @PUT("/products/{id}")
    Call<Product> update(@Path("id") Long id, @Body Product product);

    // TODO move these static headers to a request interceptor.
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json",
        "User-Agent: " + Configuration.USER_AGENT
    })
    @DELETE("/products/{id}")
    Call<ResponseBody> delete(@Path("id") Long id);
}
