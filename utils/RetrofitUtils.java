package ru.gb.lesson_5.utils;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.*;

@UtilityClass
public class RetrofitUtils {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new PrettyLogger());

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public Retrofit getRetrofit() {
        loggingInterceptor.setLevel(BODY);
        httpClient.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .baseUrl(ConfigUtils.getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
