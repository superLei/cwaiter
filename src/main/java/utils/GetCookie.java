package utils;



import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class GetCookie {
    public static Logger log =  LogManager.getLogger(GetCookie.class.getName());
    public static String get_cookie = "";

    public static String loginAndGetCookie() {

        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    // 使用ConcurrentMap存储cookie信息，因为数据在内存中，所以只在程序运行阶段有效，程序结束后即清空
                    public ConcurrentMap<String, List<Cookie>> storage = new ConcurrentHashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        String host = url.host();
                        if (cookies != null && !cookies.isEmpty()) {
                            storage.put(host, cookies);
                            System.out.println("cookies url: " + url.toString());
                            for (Cookie cookie : cookies)
                            {
                                get_cookie += cookie.toString();
                                System.out.println("cookies: " + cookie.toString());
                            }
                        }
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        String host = url.host();
                        List<Cookie> list = storage.get(host);
                        return list == null ? new ArrayList<Cookie>() : list;
                    }
                })
                .build();

        final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

        // 模拟表单登录
        Request request = new Request.Builder()
                .url("http://pre.login.hualala.com/loginAjax")
                .post(new FormBody.Builder()
                        .add("groupId", "666464585")
                        .add("userId", "sltest")
                        .add("password", "sl123456")
                        .add("redirectURL", "http://pre.shop.hualala.com")
                        .build())
                .addHeader("User-Agent", userAgent)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .build();

        // 执行模拟登录请求
        Response response = null;
        try {
            response = client.newCall(request).execute();
            System.out.println("1" + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

// response status
        if (!response.isSuccessful()) {
            System.out.println("2"  + response.code() + response.message());
        } else {
            System.out.println("登录成功 !");
        }
//        final String rex = "access_token.*?;";
////        Pattern pattern = Pattern.compile(rex);
////        Matcher m = pattern.matcher(get_cookie);
////        if (m.find()) {
////            get_cookie = m.group(0);
//////            System.out.println(m.group(0));
////        }
////        System.out.println(get_cookie);
        return get_cookie.split(";")[0];


//        // 请求包含用户状态信息的网页，观察能否正确请求并取得状态信息
//        request = new Request.Builder()
//                .url("https://www.oschina.net/")
//                .addHeader("User-Agent", userAgent)
//                .get()
//                .build();
//
//// 执行GET请求，并在异步回调中处理请求
//        response = client.newCall(request).execute();
//
//// 打印登录用户名，验证是否获取到了用户的登录信息(状态信息)
//        if (response.isSuccessful()) {
//            String content = response.body().string();
//
//            Matcher matcher = Pattern.compile("<span class=\"name\">(.*)</span>，您好&nbsp;").matcher(content);
//            if (matcher.find()) {
//                log.info("登录用户：{}", matcher.group(1));
//            } else {
//                log.info("用户未登录");
//            }
//
//        }
    }

    public static void main(String[] args) {
        String orginStr = loginAndGetCookie();
        System.out.println(orginStr);
//        String[] strings = orginStr.split(";");
//        System.out.println(strings[0]);
//        final String rex = "access_token.*?;";
//        Pattern pattern = Pattern.compile(rex);
//        Matcher m = pattern.matcher(orginStr);
//        if (m.find()) {
//            System.out.println(m.group(0));
//        }
    }


}
