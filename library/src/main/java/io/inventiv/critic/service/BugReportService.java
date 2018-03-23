package io.inventiv.critic.service;

import java.util.List;

import io.inventiv.critic.model.BugReport;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface BugReportService {

    @Multipart
    @POST("/api/v2/bug_reports")
    Call<BugReport> create(@Part List<MultipartBody.Part> parts);
}
