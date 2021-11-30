package com.example.zkx74;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FragmentOfScore extends Fragment {
    @BindView(R.id.totalText)
    TextView totalText;

    String html_Score = "null";
    Unbinder unbinder;
    private ListView listView;
    Score[] ScoreList = new Score[20];
    String[] total = new String[10];
    Context context=getContext();
    String proxyState="no";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_of_score, container, false);
        unbinder = ButterKnife.bind(this, view);
        Login login = new Login();
        //接受传递的cookie
        String cookie = null;
        SharedPreferences preferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        cookie = preferences.getString("cookie", null);
        proxyState=preferences.getString("proxyState","no");
        Context context = getContext();
        boolean flag=false;
        try {
            //抓取的成绩源码
            if(preferences.getString("score","null").equals("null")&&preferences.getString("offnet","true").equals("false")) {
                html_Score = login.getschedule("http://jwts.hit.edu.cn/xfj/queryListXfj", cookie,30000,proxyState);
                //储存源码，一会在成绩解析类中调用
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("score", html_Score);
                edit.commit();
            }
            else if(!preferences.getString("score","null").equals("null")){
                html_Score=preferences.getString("score","null");
            }
            else {
                html_Score="null";
            }

            //解析
            if(!html_Score.equals("null")) {
                ParseScore parseScore = new ParseScore(context);
                total = parseScore.getTotalCredit();
                ScoreList = parseScore.parseScore();
            }
            else{
                Toast.makeText(context, "成绩为空", Toast.LENGTH_SHORT).show();
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "解析成绩异常", Toast.LENGTH_SHORT).show();
            flag=true;
        }
        if(!flag) {
            listView = (ListView) view.findViewById(R.id.listview);
            List<Map<String, Object>> list = getData();
            listView.setAdapter(new MyAdapter(getActivity(), list));
        }
        drawTotal();

        return view;
    }

    private void drawTotal() {
        totalText.setText("平均学分绩："+total[0]+"           专业排名："+total[1]+"\n考查课不及格学分数："+total[4]+"       计算学期数："+total[5]);
    }

    //ListMap不会用(用来存储解析好的成绩数据)
    public List<Map<String, Object>> getData() {
        boolean flag=false;
        //按学期对scoreList排序
        /*-----未测试-----*/
        Score score=new Score();
        int x=0,y=0;
        for(int i=0;i<20;i++){
            for(int j=i;j<20;j++){
                if(!ScoreList[i].getObject().equals("")&&!ScoreList[j].getObject().equals("")) {
                    x = 10 * Integer.parseInt(ScoreList[i].getTime().substring(0, 4));
                    y = 10 * Integer.parseInt(ScoreList[j].getTime().substring(0, 4));
                    if (ScoreList[i].getTime().contains("春")) {
                        x = x + 1;
                    } else if (ScoreList[i].getTime().contains("夏")) {
                        x = x + 2;
                    } else if (ScoreList[i].getTime().contains("秋")) {
                        x = x + 3;
                    } else if (ScoreList[i].getTime().contains("冬")) {
                        x = x + 4;
                    }
                    if (ScoreList[j].getTime().contains("春")) {
                        y = y + 1;
                    } else if (ScoreList[j].getTime().contains("夏")) {
                        y = y + 2;
                    } else if (ScoreList[j].getTime().contains("秋")) {
                        y = y + 3;
                    } else if (ScoreList[j].getTime().contains("冬")) {
                        y = y + 4;
                    }
                    if (y < x) {
                        score = ScoreList[i];
                        ScoreList[j] = ScoreList[j];
                        ScoreList[j] = score;
                        score = new Score();
                    }
                }
            }
        }
        //
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String lastTime="";
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            if(ScoreList[i].getTime().contains("春季")||ScoreList[i].getTime().contains("夏季")||ScoreList[i].getTime().contains("秋季")||ScoreList[i].getTime().contains("冬季")){
                if(!lastTime.equals(ScoreList[i].getTime())){
                    flag=false;
                    lastTime=ScoreList[i].getTime();
                }

                if(!flag){
                    flag=true;
                    map.put("course","                        "+ScoreList[i].getTime());
                    map.put("credit", "");
                    map.put("course_score", "");
                    list.add(map);
                    map = new HashMap<String, Object>();
                }
            }
            if (!ScoreList[i].getObject().equals("")) {
                map.put("course", ScoreList[i].getObject());
                map.put("credit", ScoreList[i].getCredit() + "学分");
                map.put("course_score", ScoreList[i].getGrade());
                list.add(map);
            }
        }
        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}