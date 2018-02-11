package cn.tommyyang.tools.httptool.Utils.HttpClient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class PoolConnectionManager {

    private static PoolConnectionManager instance;

    public static synchronized PoolConnectionManager getInstance() {
        if (instance == null) {
            instance = new PoolConnectionManager();
        }
        return instance;
    }

    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = null;

    private PoolConnectionManager() {
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(400);// 连接池大小
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);// 每条通道的并发连接数设置
    }

    public CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(this.poolingHttpClientConnectionManager).build();
    }

}
