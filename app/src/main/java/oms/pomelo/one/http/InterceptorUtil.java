package oms.pomelo.one.http;

import android.util.Log;

import java.io.IOException;
import java.util.List;

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

    public static Interceptor ParamsInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request oldRequest = chain.request();

                HttpUrl.Builder builder = null;
                Request.Builder rBuilder = oldRequest.newBuilder();
                List<String> urlNameList = oldRequest.headers("urlName");
                if (urlNameList.size() > 0) {
                    //删除原有配置中的值,就是namesAndValues集合里的值
                    rBuilder.removeHeader("urlName");
                    //获取头信息中配置的value,如：one或者judou
                    String urlName = urlNameList.get(0);
                    //根据头信息中配置的value, 来匹配新的base_url地址
                    if ("judou".equals(urlName)) {
                        builder = oldRequest.url()
                                .newBuilder()
                                .scheme(oldRequest.url().scheme())
                                .host(oldRequest.url().host())
                                .addQueryParameter("per_page", "20")
                                .addQueryParameter("app_key", "2a438661-92c0-4a2d-b32e-3fd0c47a0a3c")
                                .addQueryParameter("timestamp", "1575649435")
                                .addQueryParameter("signature", "73c2339db4360b413d7f73e8ff624570");

                    } else if ("one".equals(urlName)) {
                        builder = oldRequest.url()
                                .newBuilder()
                                .scheme(oldRequest.url().scheme())
                                .host(oldRequest.url().host())
                                .addQueryParameter("platform", "ios")
                                .addQueryParameter("sign", "891dffd5d78b50c8122514bd8073e502")
                                .addQueryParameter("user_id", "10879580")
                                .addQueryParameter("uuid", "A0AB8A9C-23B6-4D65-A585-D6BAD2546CC3")
                                .addQueryParameter("version", "v4.6.7");
                    }
                }
                Request newRequest = oldRequest.newBuilder()
                        .method(oldRequest.method(), oldRequest.body())
                        .url(builder.build())
                        .build();

//                HttpUrl.Builder builder = oldRequest.url()
//                        .newBuilder()
//                        .scheme(oldRequest.url().scheme())
//                        .host(oldRequest.url().host())
//                        .addQueryParameter("platform", "ios")
//                        .addQueryParameter("sign", "891dffd5d78b50c8122514bd8073e502")
//                        .addQueryParameter("user_id", "10879580")
//                        .addQueryParameter("uuid", "A0AB8A9C-23B6-4D65-A585-D6BAD2546CC3")
//                        .addQueryParameter("version", "v4.6.7");
//
//                Request newRequest = oldRequest.newBuilder()
//                        .method(oldRequest.method(), oldRequest.body())
//                        .url(builder.build())
//                        .build();

                Response response = chain.proceed(newRequest);
                return response;
            }
        };
    }

    public static Interceptor BaseUrlInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //获取原始的originalRequest
                Request oldRequest = chain.request();
                //获取老的url
                HttpUrl oldUrl = oldRequest.url();
                //获取originalRequest的创建者builder
                Request.Builder builder = oldRequest.newBuilder();
                //获取头信息的集合如：one, judou
                List<String> urlNameList = oldRequest.headers("urlName");
                if (urlNameList.size() > 0) {
                    //删除原有配置中的值,就是namesAndValues集合里的值
                    builder.removeHeader("urlName");
                    //获取头信息中配置的value,如：one或者judou
                    String urlName = urlNameList.get(0);
                    HttpUrl baseURL = null;
                    //根据头信息中配置的value, 来匹配新的base_url地址
                    if ("judou".equals(urlName)) {
                        baseURL = HttpUrl.parse(ApiHelper.JUDOU_BASE_URL);
                    } else if ("one".equals(urlName)) {
                        baseURL = HttpUrl.parse(ApiHelper.ONE_BASE_URL);
                    }
                    //重建新的HttpUrl，需要重新设置的url部分
                    HttpUrl newHttpUrl = oldUrl.newBuilder()
                            .scheme(baseURL.scheme())//http协议如：http或者https
                            .host(baseURL.host())//主机地址
                            .port(baseURL.port())//端口
                            .build();
                    //获取处理后的新newRequest
                    Request newRequest = builder.url(newHttpUrl).build();
                    return chain.proceed(newRequest);
                } else {
                    return chain.proceed(oldRequest);
                }
            }
        };
    }

}
