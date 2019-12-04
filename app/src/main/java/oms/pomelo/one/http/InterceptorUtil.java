package oms.pomelo.one.http;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Sherry on 2019/12/3
 */

public class InterceptorUtil {

    private static int LOG_MAX_LENGTH = 2000;

    //日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                int strLength = message.length();
                int start = 0;
                int end = LOG_MAX_LENGTH;
                for (int i = 0; i < 100; i++) {
                    //剩下的文本还是大于规定长度则继续重复截取并输出
                    if (strLength > end) {
                        Log.i("requestUrl", "log: " + message.substring(start, end));
                        start = end;
                        end = end + LOG_MAX_LENGTH;
                    } else {
                        Log.i("requestUrl", "log: " + message.substring(start, strLength));
                        break;
                    }
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    public static Interceptor HeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request oldRequest = chain.request();

                HttpUrl.Builder builder = oldRequest.url()
                        .newBuilder()
                        .scheme(oldRequest.url().scheme())
                        .host(oldRequest.url().host())
                        .addQueryParameter("platform", "ios")
                        .addQueryParameter("sign", "891dffd5d78b50c8122514bd8073e502")
                        .addQueryParameter("user_id", "10879580")
                        .addQueryParameter("uuid", "A0AB8A9C-23B6-4D65-A585-D6BAD2546CC3")
                        .addQueryParameter("version", "v4.6.7");

                Request newRequest = oldRequest.newBuilder()
                        .method(oldRequest.method(), oldRequest.body())
                        .url(builder.build())
                        .build();

                Response response = chain.proceed(newRequest);
                return response;
            }
        };
    }

}
