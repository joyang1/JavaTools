package cn.tommyyang.tools.httptool.service;

import cn.tommyyang.tools.httptool.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by TommyYang on 2018/2/11.
 */
public interface IGithubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

}
