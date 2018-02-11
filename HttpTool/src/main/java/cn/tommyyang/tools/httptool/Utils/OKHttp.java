package cn.tommyyang.tools.httptool.Utils;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by TommyYang on 2018/2/11.
 */
public enum OKHttp {

    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 15;
    private static final int TIMEOUT_CONNECTION = 15;


    OKHttp() {
        okHttpClient = new OkHttpClient.Builder()
                //失败重连
                .retryOnConnectionFailure(true)
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
