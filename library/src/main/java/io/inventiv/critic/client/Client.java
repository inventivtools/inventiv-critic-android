package io.inventiv.critic.client;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import io.inventiv.critic.model.User;
import io.inventiv.critic.service.UserService;
import io.inventiv.critic.service.AttachmentService;
import io.inventiv.critic.service.MembershipService;
import io.inventiv.critic.service.OrganizationService;
import io.inventiv.critic.service.ProductService;
import io.inventiv.critic.service.ReportService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper for creating Retrofit Service instances.
 */
public final class Client {

    private static String authorizationToken;

    public static final boolean authenticate(String email, String password){

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User.Wrapper wrapper = new User.Wrapper(user);
        Call<User> userCall = Client.userService().login(wrapper);

        Response<User> response = null;
        try {
            response = userCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(Client.class.getCanonicalName(), "Authentication failed! Did you enter the correct credentials?");
            return false;
        }

        String authorizationToken = response.headers().get("Authorization");
        if(authorizationToken == null || authorizationToken.length() == 0) {
            Log.e(Client.class.getCanonicalName(), "Authorization header is blank! No authentication token returned.");
            return false;
        }

        Client.setAuthorizationToken(authorizationToken);
        return true;
    }

    public static String getAuthorizationToken() {
        return Client.authorizationToken;
    }

    public static void setAuthorizationToken(String authorizationToken) {
        Client.authorizationToken = authorizationToken;
    }

    public static UserService userService() {
		return service(UserService.class);
	}

	public static AttachmentService attachmentService() {
		return service(AttachmentService.class);
	}

	public static MembershipService membershipService() {
		return service(MembershipService.class);
	}

	public static OrganizationService organizationService() {
		return service(OrganizationService.class);
	}

	public static ProductService productService() {
		return service(ProductService.class);
	}

	public static ReportService reportService() {
		return service(ReportService.class);
	}

    private static <T> T service(final Class<T> service) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ClientRequestInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(newGson()))
                .baseUrl(Configuration.BASE_URL)
                .client(client)
                .build();

        return retrofit.create(service);
    }

    private static Gson newGson() {

        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {

                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        String name = f.getName();
                        if (name.equals("mId") || name.equals("mTableInfo") || name.equals("idName")) {
                            // members declared in com.activerecord.Model should be skipped.
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }).create();
    }
}
