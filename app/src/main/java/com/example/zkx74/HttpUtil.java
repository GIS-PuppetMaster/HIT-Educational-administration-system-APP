package com.example.zkx74;

/**
 * Created by zkx74 on 2018/3/12 0012.
 */

import android.content.Context;
import android.content.SharedPreferences;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.client.ResponseHandler;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    /**
     * 向对应的网址发送get请求,以String的形式返回服务器的相应
     *
     * @author cjyong at 2017/3/5
     * @param url 发送请求的网址
     * @param usecookie 是否使用cookie
     * @param cookie 需要携带的cookie
     * @param encoding 编码格式
     * @return 以string的形式返回服务器的响应
     * @throws Exception
     */
    static String username = "";
    static String password = "";

    public HttpUtil(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        username = preferences.getString("username", "null");
        password = preferences.getString("password", "null");
    }

    public static String sendGetRequest(final String url, final boolean usecookie, final String cookie, final String encoding, final int time) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                URL turl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) turl.openConnection();
                //设置时间限制,抛出异常
                conn.setConnectTimeout(time);
                conn.setReadTimeout(time);
                conn.setRequestProperty("Connection", "keep-alive");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                if (usecookie) conn.setRequestProperty("Cookie", cookie);
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) sb.append(line + "\n");
                int k=conn.getResponseCode();
                return sb.toString();
            }

        });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }


    /**
     * 向对应的网址发送post请求,以String的形式返回服务器的相应
     *
     * @param url       发送请求的网址
     * @param data      发送post请求携带的数据
     * @param usecookie 是否使用cookie
     * @param cookie    需要携带的cookie
     * @param encoding  编码格式
     * @return 以string的形式返回服务器的响应
     * @throws Exception
     * @author cjyong at 2017/3/5
     */
    public static String sendPostRequest(final String url, final String data, final boolean usecookie, final String cookie, final String encoding) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                URL turl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) turl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                //设置时间限制,抛出异常
                conn.setConnectTimeout(3000);
                conn.setReadTimeout(3000);
                conn.setRequestProperty("Connection", "keep-alive");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                if (usecookie) conn.setRequestProperty("Cookie", cookie);

                OutputStream outStream = conn.getOutputStream();
                outStream.write(data.getBytes());
                outStream.flush();
                outStream.close();
                InputStream is = conn.getInputStream();
                Map responceHead = conn.getHeaderFields();
                int k=conn.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) sb.append(line + "\n");
                return sb.toString();
                //String cookie=conn.getHeaderField("Set-Cookie").toString();
                //return cookie;
            }
        });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }


    /**
     * 向对应的网址发送post请求,获取对应的cookie,以备后用
     *
     * @param url  发送请求的网址
     * @param data 发送post请求携带的数据
     * @return 以string的形式返回服务器的响应
     * @throws Exception
     * @author cjyong at 2017/3/5
     */

    public static String getCookie(final String url, final String data) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                byte[] Data = data.getBytes();
                URL turl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) turl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                //设置连接与读取时间过期返回异常
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStream outStream = conn.getOutputStream();
                outStream.write(Data);
                outStream.flush();
                outStream.close();

                String Cookie = conn.getHeaderField("Set-Cookie");
                return Cookie;
            }

        });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }

    public static String getcookie2(final String url, final String data, final boolean usecookie, final String cookie, final String encoding) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                URL turl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) turl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                //设置时间限制,抛出异常
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                if (usecookie) conn.setRequestProperty("Cookie", cookie);

                OutputStream outStream = conn.getOutputStream();
                outStream.write(data.getBytes());
                outStream.flush();
                outStream.close();
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) sb.append(line + "\n");
                //return sb.toString();
                String cookie = conn.getHeaderField("Set-Cookie").toString();
                return cookie;
            }
        });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }

    public static String sendVPNPostRequest(final String url, final String data, final boolean usecookie, final String cookie, final String encoding) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                URL turl = new URL(url);
                HttpsURLConnection conn = (HttpsURLConnection) turl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                //设置时间限制,抛出异常
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                if (usecookie) conn.setRequestProperty("Cookie", cookie);
                OutputStream outStream = conn.getOutputStream();
                outStream.write(data.getBytes());
                outStream.flush();
                outStream.close();
                InputStream is = conn.getInputStream();
                Map map=conn.getHeaderFields();
                int k=conn.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) sb.append(line + "\n");
                String Cookie="";
                Cookie=conn.getHeaderField("Set-Cookie");
                return sb.toString();

            }
        });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }

    public static String getVPNCookie(final String url, final String data,final boolean usecookie, final String cookie) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                byte[] Data = data.getBytes();
                URL turl = new URL(url);

                HttpsURLConnection conn = (HttpsURLConnection) turl.openConnection();

                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                //设置连接与读取时间过期返回异常
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                conn.setInstanceFollowRedirects(false);
                if(usecookie)conn.setRequestProperty("Cookie",cookie);
                OutputStream outStream = conn.getOutputStream();
                outStream.write(Data);
                outStream.flush();
                outStream.close();
                InputStream is = conn.getInputStream();
                Map responceHead = conn.getHeaderFields();
                List<String> Cookie= (List<String>) responceHead.get("Set-Cookie");
                String cookie="";
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    sb.append(line + "\n");
                for(int i=0;i<Cookie.size();i++){
                    String[] arr=Cookie.get(i).split(";");
                    for(int j=0;j<arr.length;j++){
                        if(arr[j].contains("DSID")){
                            cookie=arr[j];
                            break;
                        }
                    }
                }
                return cookie;
            }

        });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }



    public static String sendVPNGetRequest_returnCookie(final String url, final boolean usecookie, final String cookie, final String encoding, final int time) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                URL turl = new URL(url);
                HttpsURLConnection conn = (HttpsURLConnection) turl.openConnection();
                //设置时间限制,抛出异常
                conn.setConnectTimeout(time);
                conn.setReadTimeout(time);
                if (usecookie) conn.setRequestProperty("Cookie", cookie);
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) sb.append(line + "\n");
                String Cookie = conn.getHeaderField("Set-Cookie");
                return Cookie;
            }

        });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }
    public static String sendVPNGetRequest(final String url, final boolean usecookie, final String cookie, final String encoding, final int time) throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                URL turl = new URL(url);
                HttpsURLConnection conn = (HttpsURLConnection) turl.openConnection();
                //设置时间限制,抛出异常
                conn.setConnectTimeout(time);
                conn.setReadTimeout(time);
                if (usecookie) conn.setRequestProperty("Cookie", cookie);
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) sb.append(line + "\n");
                String Cookie = conn.getHeaderField("Set-Cookie");
                return sb.toString();
            }

        });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }

}
