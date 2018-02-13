package io.inventiv.critic.client;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class ClientRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder();

        String accept = originalRequest.header("Accept");
        if(accept == null || accept.length() == 0) {
            requestBuilder.addHeader("Accept", "application/json");
        }

        String contentType = originalRequest.header("Content-Type");
        if(contentType == null || contentType.length() == 0) {
            requestBuilder.addHeader("Content-Type", "application/json");
        }

        String userAgent = originalRequest.header("User-Agent");
        if(userAgent == null || userAgent.length() == 0) {
            requestBuilder.addHeader("User-Agent", Configuration.USER_AGENT);
        }

        return chain.proceed(requestBuilder.build());
    }
}
