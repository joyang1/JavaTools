package cn.tommyyang.tools.httptool;

import cn.tommyyang.tools.httptool.Utils.RetrofitUtils;
import cn.tommyyang.tools.httptool.model.Repo;
import cn.tommyyang.tools.httptool.service.IGithubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by TommyYang on 2018/2/11.
 */
public class HttpToolTest {

    private final static Logger logger = LoggerFactory.getLogger(HttpToolTest.class);

    public static void main(String[] args) {
        IGithubService githubService = RetrofitUtils.getInstance().create(IGithubService.class);
        Call<List<Repo>> call = githubService.listRepos("joyang1");
        try {
            Response<List<Repo>> repos = call.execute();
            logger.info("github repos size:" + repos.body().size());
            Integer num = 1;
            for (Repo repo : repos.body()) {
                System.out.println("序号:" + num +"-" + repo.toString());
                num++;
            }
        } catch (IOException e) {
            logger.error("execute retrofit http request error:\n", e);
        }
    }

}
