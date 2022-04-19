import com.google.gson.Gson;
import lombok.SneakyThrows;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpTest {
    private static final Gson GSON = new Gson();
    private static final Session REQUESTS = Requests.session();
    @SneakyThrows
    public static void main(String[] args) {
        httpPostTest();
    }
    @SneakyThrows
    private static void httpGetTest(){
        //1.确定要访问爬取的URL
        URL ur1 = new URL("https://btg.2miners.com/api/miners");
        // 创建代理服务器
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",10808);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        //2.获取链接对象
        HttpURLConnection urlConnection = (HttpURLConnection) ur1.openConnection(proxy);

        //3.设置连接信息：请求方式/请求参数/请求头
        urlConnection.setRequestMethod("GET");//请求方式默认GET，大写
        urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Mobile Safari/537.36 Edg/85.0.564.68");
        urlConnection.setRequestProperty("accept","application/json, text/plain, */*");
        urlConnection.setRequestProperty("accept-encoding","gzip, deflate, br");
        urlConnection.setRequestProperty("accept-language","zh-CN,zh;q=0.9,ko;q=0.8,ja;q=0.7");
        urlConnection.setRequestProperty("sec-ch-ua-platform","Windows");
        urlConnection.setConnectTimeout(30000);//设置超时时间，单位毫秒

        //4.获取数据
        InputStream input = urlConnection.getInputStream();//流操作不方便，故使用BufferedReader包装
        BufferedReader reader = new BufferedReader(new InputStreamReader(input)); //一行一行读出来
        String line;
        String all_line = "";
        while((line = reader.readLine()) != null){
            all_line += line + "\n";
        }
        System.out.println(all_line);

        //5.关闭连接
        input.close();
        reader.close();
    }
    @SneakyThrows
    private static void httpPostTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("currency_code", "eth");
        map.put("history_days", "1d");
        map.put("interval", "60m");

        //1.确定要访问爬取的URL
        URL ur1 = new URL("https://www.f2pool.com/coins-chart");
        // 创建代理服务器
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1",10808);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        String content = REQUESTS.post(ur1).body(map)
                .proxy(proxy)
                .send().readToText();
        System.out.println("content = " + content);
    }
}
