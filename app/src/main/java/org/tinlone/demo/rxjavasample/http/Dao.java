package org.tinlone.demo.rxjavasample.http;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class Dao {

    private static RequestService service;

    public static RequestService getService() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addInterceptor(new MyInterceptor())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Api.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            service = retrofit.create(RequestService.class);
        }
        return service;
    }

    private static class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
//            builder.addHeader("token", "123");
            Request request = builder.build();
            Log.i("zjl", " #----- FOCUS -----# " + request.url().toString());
            return chain.proceed(request);
        }
    }
}
