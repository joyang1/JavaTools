package cn.tommyyang.tools.httptool;

import cn.tommyyang.tools.httptool.Utils.Retrofit.Retrofit2Client;
import cn.tommyyang.tools.httptool.model.Repo;
import cn.tommyyang.tools.httptool.service.IGithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by TommyYang on 2018/2/11.
 */
public class HttpToolTest {

    private final static Logger logger = LoggerFactory.getLogger(HttpToolTest.class);
    private static IGithubService githubService = Retrofit2Client.INSTANCE.getRetrofit2Client().create(IGithubService.class);

    public static void main(String[] args) {
        sync();
        async();
        System.out.println("end !!!"); //可以测试是否是异步(该句输出在异步结果输出之前输出)
    }

    //同步执行retrofit http请求
    public static void sync() {
        Call<List<Repo>> call = githubService.listRepos("testZyhGitHub");
        try {
            Response<List<Repo>> repos = call.execute();
            logger.info("sync github repos size:" + repos.body().size());
            Integer num = 1;
            for (Repo repo : repos.body()) {
                System.out.println("序号(同步):" + num + "-" + repo.toString());
                num++;
            }
        } catch (IOException e) {
            logger.error("sync execute retrofit http request error:\n", e);
        }
    }

    //异步执行retrofit http请求
    public static void async() {
        Call<List<Repo>> call = githubService.listRepos("joyang1");
        call.enqueue(new Callback<List<Repo>>() {
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                logger.info("async github repos size:" + response.body().size());
                Integer num = 1;
                for (Repo repo : response.body()) {
                    System.out.println("序号(异步):" + num + "-" + repo.toString());
                    num++;
                }
            }

            public void onFailure(Call<List<Repo>> call, Throwable throwable) {
                logger.error("async execute retrofit http request error:\n", throwable);
            }
        });
    }
}
