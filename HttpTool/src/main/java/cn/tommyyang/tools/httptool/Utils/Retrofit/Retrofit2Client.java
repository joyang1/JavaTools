package cn.tommyyang.tools.httptool.Utils.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TommyYang on 2018/2/11.
 */
public enum Retrofit2Client {

    INSTANCE_GITHUB("https://api.github.com/"),
    INSTANCE_TEST("http://localhost:8080/");

    private final Retrofit retrofit;

    //private final static String baseUrl = "https://api.github.com/";
    //private final static String baseUrl = "http://webuser.api.****.com/";

    /**
     *
     * 如果有多个baseUrl 则此处创建Retrofit.Builder对象
     *
     * */
    Retrofit2Client(String baseUrl){
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient,如果不设置会提供一个默认的
                .client(OKHttp.INSTANCE.getOkHttpClient())
                //设置baseUrl
                .baseUrl(baseUrl)
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit2Client() {
        return retrofit;
    }

}
