package cn.tommyyang.tools.httptool.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TommyYang on 2018/2/11.
 */
public class RetrofitUtils {

    public static Retrofit getInstance(){
        //获取实例
        Retrofit retrofit = new Retrofit.Builder()
                //设置OKHttpClient,如果不设置会提供一个默认的
                .client(new OkHttpClient())
                //设置baseUrl
                .baseUrl("https://api.github.com/")
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
