package oms.pomelo.one.http;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import oms.pomelo.one.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sherry on 2019/12/3
 */

public class RetrofitFactory {

    private volatile static RetrofitFactory mInstance;
    private static ApiService mApiService;

    private RetrofitFactory(Context context) {
        initRetrofit();
    }

    public static RetrofitFactory getInstance(Context context) {
        if (mInstance == null) {
            synchronized (RetrofitFactory.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitFactory(context);
                }
            }
        }
        return mInstance;
    }

    private void initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS) //20秒链接超时
                .writeTimeout(20 * 1000, TimeUnit.MILLISECONDS) //写入超时20秒
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(InterceptorUtil.ParamsInterceptor())
                .addInterceptor(InterceptorUtil.BaseUrlInterceptor());

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(InterceptorUtil.LogInterceptor()); //添加日志拦截器
        }

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiHelper.ONE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加RxJava转换器
                .client(builder.build())
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }

}
