package cn.tommyyang.tools.httptool;

import cn.tommyyang.tools.httptool.Utils.Retrofit.Retrofit2Client;
import cn.tommyyang.tools.httptool.model.Repo;
import cn.tommyyang.tools.httptool.model.TestModel;
import cn.tommyyang.tools.httptool.service.IGithubService;
import cn.tommyyang.tools.httptool.service.ITestService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by TommyYang on 2018/2/11.
 */
public class HttpToolTest {

    private final static Logger logger = LoggerFactory.getLogger(HttpToolTest.class);
    private static IGithubService githubService = Retrofit2Client.INSTANCE_GITHUB.getRetrofit2Client().create(IGithubService.class);
    private static ITestService testService = Retrofit2Client.INSTANCE_TEST.getRetrofit2Client().create(ITestService.class);

    public static void main(String[] args) {
        sync();
        //async();
        syncTest();
        System.out.println("end !!!"); //可以测试是否是异步(该句输出在异步结果输出之前输出)
    }

    //同步执行retrofit http请求
    public static void sync() {
        Call<List<Repo>> call = githubService.listRepos("joyang1");
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

    public static void syncTest() {
        try {
            String pushId = "pushid";
            int taskId = 10001;
            String appName = "title";
            String pushTitle = "content";
            String platformType = "102";
            String pushType = "精准";
            String infoType = "";
            String channelId = "";
            String playId = "";
            int typeId = 0;
            Date pushTime = new Date();
            Date expirationTime = new Date();
            List<String> tokenids = new ArrayList<String>();
            tokenids.add("1111");
            tokenids.add("1112");
            tokenids.add("1113");
            tokenids.add("1114");
            tokenids.add("1115");
            int badgeNum = 10;
            int week = 12;
            String actionType = "";
            String action = "";
            String actionParam = "1";
            String sectionid = "";
            String imgUrl = "";
            TestModel testModel = new TestModel(pushId, taskId, appName, pushTitle, platformType, pushType, infoType,
                    channelId, playId, typeId, pushTime, expirationTime, tokenids, badgeNum, week, actionType,
                    action, actionParam, sectionid, imgUrl);
            String pushContent = new Gson().toJson(testModel);
            Call<Map<String, Object>> call = testService.test(1, pushContent);
            try {
                Response<Map<String, Object>> res = call.execute();
                System.out.println(res);
            } catch (IOException e) {
                logger.error("sync execute retrofit http request error:\n", e);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
