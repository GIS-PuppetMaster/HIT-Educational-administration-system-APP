package com.example.zkx74;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.zkx74.dummy.Session;

import java.util.HashMap;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import retrofit2.http.HTTP;

/**
 * Created by zkx74 on 2018/3/10 0010.
 */

public class Login {

    String html;
    String cookie;
    Context context;
    public String getdata(String username, String password,String url,String proxyState,Context context) throws Exception {
        this.context=context;
        return loginValidate(username,password,url,proxyState);
    }
    public String getschedule(String url, String Cookie,int time,String proxyState) throws Exception {
        return getSchedule(url,Cookie,time,proxyState);
    }

    private List<Cookie> mylist;
    private String loginValidate(String username,String password,String url,String proxyState) throws Exception {

        username="1170300216";
        password="trustno1";
        //生成登陆表单
        String data="usercode="+username+"&password="+password+"&code=";
        String judge="";
        String proxyURL="https://vpn.hit.edu.cn/dana-na/auth/url_default/login.cgi";
        String proxyCookie="";
        String proxyData="tz_offset=480"+"&username="+username+"&password="+password+"&realm=学生&btnSubmit=登陆";
        String proxy_JwtsData="usercode="+username+"&password="+password+"&code=";
        String schedule="";
        String score="";
        String cookie = "null";
        if(proxyState.equals("no")) {
            //发送登录请求

            //获取clwz
            String cookie2 = HttpUtil.getcookie2(url, data, false, null, "utf-8");
            //登陆
            judge = HttpUtil.sendPostRequest(url, data, false, null, "utf-8");
            //获取JESSIONID
            String cookie1 = HttpUtil.getCookie(url, data);
            //提取JESSIONID
            int l = cookie1.length();
            l = l - 18;
            cookie1 = cookie1.substring(0, l);
            //提取clwz
            l = cookie2.length();
            l = l - 7;
            cookie2 = cookie2.substring(0, l);
            //构造cookie
            cookie = "name=value; " + cookie2 + "; " + cookie1;
        }
        else{
            SharedPreferences sharedPreferences=context.getSharedPreferences("data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
           proxyCookie=HttpUtil.getVPNCookie(proxyURL,proxyData,false,"");
           judge=HttpUtil.sendVPNPostRequest("https://vpn.hit.edu.cn/,DanaInfo=jwts.hit.edu.cn+loginLdap",proxy_JwtsData,true,proxyCookie,"utf-8");
            //获取课表
           schedule=HttpUtil.sendVPNGetRequest("https://vpn.hit.edu.cn/kbcx/,DanaInfo=jwts.hit.edu.cn+queryGrkb",true,proxyCookie,"utf-8",5000);
           //获取成绩
            score=HttpUtil.sendVPNGetRequest("https://vpn.hit.edu.cn/xfj/,DanaInfo=jwts.hit.edu.cn+queryXfj",true,proxyCookie,"utf-8",5000);
            //储存课表和成绩
            editor.putString("schedule",schedule);
            editor.putString("score",score);
            editor.commit();
           //登出教务处
            HttpUtil.sendGetRequest("https://vpn.hit.edu.cn/,DanaInfo=jwts.hit.edu.cn+logout",true,proxyCookie,"utf-8",5000);
           //登出vpn
            HttpUtil.sendGetRequest("https://vpn.hit.edu.cn/dana-na/auth/logout.cgi",true,proxyCookie,"utf-8",5000);
        }
        //登陆成功判定
        if(judge.contains("您好")) {
            return cookie;
        }
        else {
            return "false";//使用代理仍无法登陆
        }
    }
    public String getcookie(){
        return cookie;
    }
    //post课程表，携带之前获取的setCookie（JESSON）
    private String getSchedule(String gurl,String cookie,int time,String proxyState) throws Exception {
        //发送带cookie的get请求
        String responseSchedule= HttpUtil.sendGetRequest(gurl,true,cookie,"utf-8",time);
        return responseSchedule;
    }
    //登出方法
    /*private boolean logoutValidate() throws Exception
    {
        //这里的url无教程，有待测试
        String html = HttpUtil.sendGetRequest("http://ids.hit.edu.cn/authserver/login?service=http%3A%2F%2Fjwts.hit.edu.cn%2FloginCAS",false,null,"utf-8",5000);
        //http://ids.hit.edu.cn/authserver/logout?service=http://jwts.hit.edu.cn
        if(html.contains("success")) {
            return true;
        }
        else {
            return false;
        }
    }*/
}
