package com.example.zkx74;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.panpf.sketch.request.DisplayOptions;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.editText_name)
    EditText editTextName;
    @BindView(R.id.editText_password)
    EditText editTextPassword;
    @BindView(R.id.btn_signin)
    Button btnSignin;
    @BindView(R.id.btn_login)
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏代码
        Window window=getWindow();
        requestWindowFeature((Window.FEATURE_NO_TITLE));
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        window.setFlags(flag,flag);
        //
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ButterKnife.bind(this);

    }

    /*延时函数*/
    private void delay(int ms) {
        try {
            Thread.currentThread();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Login login = new Login();

    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unused")
    @OnClick({R.id.btn_signin, R.id.btn_login})
    public void onViewClicked(View view) {
        SharedPreferences preferences = this.getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();
        String lastuser = preferences.getString("lastuser", "null");
        String lastpass = preferences.getString("lastpass", "null");
        SharedPreferences.Editor edit = preferences.edit();
        switch (view.getId()) {
            case R.id.btn_signin:
                edit.putString("mianze",username);
                edit.commit();
                Intent intent3 = new Intent(MainActivity.this, Mianze.class);
                Intent intent2 = new Intent(MainActivity.this, FunctionActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_login:

                //自动填充
                if(editTextName.getText().toString().equals("")&&editTextPassword.getText().toString().equals("")) {
                    username = preferences.getString("lastuser", "null");
                    password = preferences.getString("lastpass", "null");
                    editTextPassword.setText("******");
                }
                //判断该用户是否已经登陆（解析）过了
                if(!lastuser.equals(username)){
                    edit.putString("schedule","null");
                    edit.putString("score","null");
                    edit.commit();
                }
                edit.putString("username", username);
                edit.commit();
                String proxyState="no";

                //检测是否在校园网内
                try {
                    String temp= HttpUtil.sendGetRequest("http://jwts.hit.edu.cn",false,"","utf-8",100);
                } catch (Exception e) {
                    proxyState="yes";
                }
                //更新代理状态
                edit.putString("proxyState",proxyState);
                edit.commit();
                //尝试使用vpn登陆
                try {
                    if (preferences.getString("mianze","null").equals(username)) {
                        String cookie = login.getdata(username, password, "http://jwts.hit.edu.cn/loginLdap",proxyState,this);
                        if (cookie != "false") {
                            showToast("登录成功");
                            edit.putString("cookie", cookie);
                            edit.putString("lastuser",username);
                            edit.putString("lastpass",password);
                            edit.commit();
                            Intent intent = new Intent(MainActivity.this, FunctionActivity.class);
                            startActivity(intent);
                        } else {
                            showToast("登陆错误");
                        }
                    } else {
                        showToast("请先阅读免责声明!");
                        edit.putString("mianze",username);
                        edit.commit();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                /**
                                 *要执行的操作
                                 */
                                Intent intent3 = new Intent(MainActivity.this, Mianze.class);
                                Intent intent2 = new Intent(MainActivity.this, FunctionActivity.class);
                                startActivity(intent3);
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(task, 1500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    if(username.equals(lastuser)&&password.equals(lastpass)) {
                        showToast("离线模式");
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                /**
                                 *要执行的操作
                                 */
                                Intent intent = new Intent(MainActivity.this, FunctionActivity.class);
                                startActivity(intent);
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(task, 1500);

                    }
                    else {
                        showToast("(请连接校园网）");
                    }
                }
                break;
        }
    }

}
