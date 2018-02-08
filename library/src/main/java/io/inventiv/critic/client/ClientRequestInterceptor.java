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

        // send an Authorization header if Client.getAuthorizationToken() exists.
        String authorizationToken = Client.getAuthorizationToken();
        if( authorizationToken != null && authorizationToken.length() > 0 ) {
            requestBuilder.addHeader("Authorization", authorizationToken );
        }

        return chain.proceed(requestBuilder.build());
    }
}
