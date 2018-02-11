package cn.tommyyang.tools.httptool.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TommyYang on 2018/2/11.
 */
public enum Retrofit2Client {

    INSTANCE;

    private final Retrofit retrofit;

    /**
     *
     * 如果有多个baseUrl 则此处创建Retrofit.Builder对象
     *
     * */
    Retrofit2Client(){
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient,如果不设置会提供一个默认的
                .client(OKHttp.INSTANCE.getOkHttpClient())
                //设置baseUrl
                .baseUrl("https://api.github.com/")
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit2Client() {
        return retrofit;
    }

}
