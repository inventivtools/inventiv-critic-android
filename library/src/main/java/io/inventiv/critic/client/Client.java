package io.inventiv.critic.client;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.inventiv.critic.service.ReportService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper for creating Retrofit Service instances.
 */
public final class Client {

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
