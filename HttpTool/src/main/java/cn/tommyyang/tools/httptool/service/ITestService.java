package cn.tommyyang.tools.httptool.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

/**
 * Created by TommyYang on 2018/2/24.
 */
public interface ITestService {

    @FormUrlEncoded
    @POST("api/push/push.do")
    Call<Map<String, Object>> test(@Field("type") int type, @Field("pushContent") String pushContent);

}
