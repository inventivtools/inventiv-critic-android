package io.inventiv.critic.service;

import java.util.List;

import io.inventiv.critic.model.Report;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ReportService {

    @Multipart
    @POST("/api/v1/reports/")
    Call<Report> create(@Part List<MultipartBody.Part> parts);
}
