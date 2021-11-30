package com.example.zkx74;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOfSchedule extends Fragment {
    @BindView(R.id.btn_no1)
    Button btnNo1;
    @BindView(R.id.btn_no2)
    Button btnNo2;
    @BindView(R.id.btn_no3)
    Button btnNo3;
    @BindView(R.id.btn_no4)
    Button btnNo4;
    Unbinder unbinder;
    @BindView(R.id.btn_no5)
    Button btnNo5;
    @BindView(R.id.btn_no6)
    Button btnNo6;
    @BindView(R.id.btn_no7)
    Button btnNo7;
    @BindView(R.id.btn_no8)
    Button btnNo8;
    @BindView(R.id.btn_no9)
    Button btnNo9;
    @BindView(R.id.btn_no10)
    Button btnNo10;
    @BindView(R.id.btn_no11)
    Button btnNo11;
    @BindView(R.id.btn_no12)
    Button btnNo12;
    @BindView(R.id.btn_no13)
    Button btnNo13;
    @BindView(R.id.btn_no14)
    Button btnNo14;
    @BindView(R.id.btn_no15)
    Button btnNo15;
    @BindView(R.id.btn_no16)
    Button btnNo16;
    @BindView(R.id.btn_no17)
    Button btnNo17;
    @BindView(R.id.btn_no18)
    Button btnNo18;

    @BindView(R.id.course_1_1)
    TextView course_1_1;
    @BindView(R.id.course_1_2)
    TextView course_1_2;
    @BindView(R.id.course_1_3)
    TextView course_1_3;
    @BindView(R.id.course_1_4)
    TextView course_1_4;
    @BindView(R.id.course_1_5)
    TextView course_1_5;
    @BindView(R.id.course_1_6)
    TextView course_1_6;
    @BindView(R.id.course_1_7)
    TextView course_1_7;
    @BindView(R.id.course_2_1)
    TextView course_2_1;
    @BindView(R.id.course_2_2)
    TextView course_2_2;
    @BindView(R.id.course_2_3)
    TextView course_2_3;
    @BindView(R.id.course_2_4)
    TextView course_2_4;
    @BindView(R.id.course_2_5)
    TextView course_2_5;
    @BindView(R.id.course_2_6)
    TextView course_2_6;
    @BindView(R.id.course_2_7)
    TextView course_2_7;
    @BindView(R.id.course_3_1)
    TextView course_3_1;
    @BindView(R.id.course_3_2)
    TextView course_3_2;
    @BindView(R.id.course_3_3)
    TextView course_3_3;
    @BindView(R.id.course_3_4)
    TextView course_3_4;
    @BindView(R.id.course_3_5)
    TextView course_3_5;
    @BindView(R.id.course_3_6)
    TextView course_3_6;
    @BindView(R.id.course_3_7)
    TextView course_3_7;
    @BindView(R.id.course_4_1)
    TextView course_4_1;
    @BindView(R.id.course_4_2)
    TextView course_4_2;
    @BindView(R.id.course_4_3)
    TextView course_4_3;
    @BindView(R.id.course_4_4)
    TextView course_4_4;
    @BindView(R.id.course_4_5)
    TextView course_4_5;
    @BindView(R.id.course_4_6)
    TextView course_4_6;
    @BindView(R.id.course_4_7)
    TextView course_4_7;
    @BindView(R.id.course_5_1)
    TextView course_5_1;
    @BindView(R.id.course_5_2)
    TextView course_5_2;
    @BindView(R.id.course_5_3)
    TextView course_5_3;
    @BindView(R.id.course_5_4)
    TextView course_5_4;
    @BindView(R.id.course_5_5)
    TextView course_5_5;
    @BindView(R.id.course_5_6)
    TextView course_5_6;
    @BindView(R.id.course_5_7)
    TextView course_5_7;
    @BindView(R.id.course_6_1)
    TextView course_6_1;
    @BindView(R.id.course_6_2)
    TextView course_6_2;
    @BindView(R.id.course_6_3)
    TextView course_6_3;
    @BindView(R.id.course_6_4)
    TextView course_6_4;
    @BindView(R.id.course_6_5)
    TextView course_6_5;
    @BindView(R.id.course_6_6)
    TextView course_6_6;
    @BindView(R.id.course_6_7)
    TextView course_6_7;
    @BindView(R.id.monday)
    TextView monday;
    @BindView(R.id.tuesday)
    TextView tuesday;
    @BindView(R.id.wednesday)
    TextView wednesday;
    @BindView(R.id.thursday)
    TextView thursday;
    @BindView(R.id.friday)
    TextView friday;
    @BindView(R.id.saturday)
    TextView saturday;
    @BindView(R.id.sunday)
    TextView sunday;
    @BindView(R.id.scrollview)
    HorizontalScrollView scrollView;

    String htmlresponse=null;
    Course courseList[]=new Course[50];
    int session[]=new int[20];
    String courseName="";
    String courseTeacher="";
    String classroom="";
    String text="";
    int weekDay=1;
    int weekMonth=1;
    String proxyState="no";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_of_schedule, container, false);
        unbinder = ButterKnife.bind(this, view);
        btnNo1.setBackgroundResource(R.color.Blue);
        Context context=getContext();
        Login login =new Login();
        String cookie = null;
        SharedPreferences preferences=getActivity().getSharedPreferences("data",Context.MODE_PRIVATE);
        cookie=preferences.getString("cookie",null);
        proxyState=preferences.getString("proxyState","no");
        try {
            if(preferences.getString("schedule","null").equals("null")||preferences.contains("页面过期")) {
                htmlresponse = login.getschedule("http://jwts.hit.edu.cn/kbcx/queryGrkb", cookie,5000,proxyState);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("schedule", htmlresponse);
                edit.commit();
                String tmp0 = preferences.getString("username", "");
                String tmp1 = preferences.getString("lastuser", "null");
                //暂时无法保存courseList
                tmp1 = "null";
                //
            }
            else {
                htmlresponse=preferences.getString("schedule","null");
            }
            //  if(!tmp0.equals(tmp1)||tmp1.equals("null")) {
            ParseCourseList parseCourseList = new ParseCourseList(context);
            courseList = parseCourseList.parseHtmlFragmentFromStringSafe();
            //  }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context,"解析课表异常",Toast.LENGTH_SHORT).show();
        }
        //获取当前周数
        dateCalcu();
        //绘制初始课程表
        draw(weekMonth);
        //周数滚动
        scrollView.setSmoothScrollingEnabled(true);
        final int offset=dp2px(context,10)+dp2px(context,82)*(weekMonth-2);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollBy(offset,0);
            }
        });
        return view;
    }
    private int dp2px(Context context, float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
    private boolean judgeWeek(int week,int i){
        int[] weekS=new int[31];
        weekS=courseList[i].getWeekS();
        for(int j=0;j<21;j++){
            if(weekS[j]==week){
                return true;
            }
        }
        return false;
    }
    public void dateCalcu(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        weekDay=calendar.get(Calendar.DAY_OF_WEEK);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(7);
        calendar.setTime(new Date());
        int now=calendar.get(Calendar.WEEK_OF_YEAR);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = df.parse("2018-09-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int start=calendar.get(Calendar.WEEK_OF_YEAR);
        weekMonth=now-start+1;

    }
    private void setBtnColor(Button btn){
        btnNo1.setBackgroundResource(R.color.Light_Black);
        btnNo1.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo2.setBackgroundResource(R.color.Light_Black);
        btnNo2.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo3.setBackgroundResource(R.color.Light_Black);
        btnNo3.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo4.setBackgroundResource(R.color.Light_Black);
        btnNo4.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo5.setBackgroundResource(R.color.Light_Black);
        btnNo5.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo6.setBackgroundResource(R.color.Light_Black);
        btnNo6.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo7.setBackgroundResource(R.color.Light_Black);
        btnNo7.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo8.setBackgroundResource(R.color.Light_Black);
        btnNo8.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo9.setBackgroundResource(R.color.Light_Black);
        btnNo9.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo10.setBackgroundResource(R.color.Light_Black);
        btnNo10.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo11.setBackgroundResource(R.color.Light_Black);
        btnNo11.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo12.setBackgroundResource(R.color.Light_Black);
        btnNo12.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo13.setBackgroundResource(R.color.Light_Black);
        btnNo13.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo14.setBackgroundResource(R.color.Light_Black);
        btnNo14.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo15.setBackgroundResource(R.color.Light_Black);
        btnNo15.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo16.setBackgroundResource(R.color.Light_Black);
        btnNo16.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo17.setBackgroundResource(R.color.Light_Black);
        btnNo17.setTextColor(getResources().getColor(R.color.Light_Gray));
        btnNo18.setBackgroundResource(R.color.Light_Black);
        btnNo18.setTextColor(getResources().getColor(R.color.Light_Gray));
        btn.setBackgroundResource(R.color.Blue);
        btn.setTextColor(getResources().getColor(R.color.Black));
    }
    private int countSubstring(String string,String sub){
        int counter=0,index=string.indexOf(sub);
        while(string.indexOf(sub)!=-1){
            string=string.substring(0,index)+string.substring(index+sub.length(),string.length());
            counter++;
            index=string.indexOf(sub);
        }
        return counter;
    }
    private void draw(int week){
        //清空课表
        course_1_1.setBackgroundResource(R.color.Black);
        course_2_1.setBackgroundResource(R.color.Black);
        course_3_1.setBackgroundResource(R.color.Black);
        course_4_1.setBackgroundResource(R.color.Black);
        course_5_1.setBackgroundResource(R.color.Black);
        course_6_1.setBackgroundResource(R.color.Black);
        course_1_2.setBackgroundResource(R.color.Black);
        course_2_2.setBackgroundResource(R.color.Black);
        course_3_2.setBackgroundResource(R.color.Black);
        course_4_2.setBackgroundResource(R.color.Black);
        course_5_2.setBackgroundResource(R.color.Black);
        course_6_2.setBackgroundResource(R.color.Black);
        course_1_3.setBackgroundResource(R.color.Black);
        course_2_3.setBackgroundResource(R.color.Black);
        course_3_3.setBackgroundResource(R.color.Black);
        course_4_3.setBackgroundResource(R.color.Black);
        course_5_3.setBackgroundResource(R.color.Black);
        course_6_3.setBackgroundResource(R.color.Black);
        course_1_4.setBackgroundResource(R.color.Black);
        course_2_4.setBackgroundResource(R.color.Black);
        course_3_4.setBackgroundResource(R.color.Black);
        course_4_4.setBackgroundResource(R.color.Black);
        course_5_4.setBackgroundResource(R.color.Black);
        course_6_4.setBackgroundResource(R.color.Black);
        course_1_5.setBackgroundResource(R.color.Black);
        course_2_5.setBackgroundResource(R.color.Black);
        course_3_5.setBackgroundResource(R.color.Black);
        course_4_5.setBackgroundResource(R.color.Black);
        course_5_5.setBackgroundResource(R.color.Black);
        course_6_5.setBackgroundResource(R.color.Black);
        course_1_6.setBackgroundResource(R.color.Black);
        course_2_6.setBackgroundResource(R.color.Black);
        course_3_6.setBackgroundResource(R.color.Black);
        course_4_6.setBackgroundResource(R.color.Black);
        course_5_6.setBackgroundResource(R.color.Black);
        course_6_6.setBackgroundResource(R.color.Black);
        course_1_7.setBackgroundResource(R.color.Black);
        course_2_7.setBackgroundResource(R.color.Black);
        course_3_7.setBackgroundResource(R.color.Black);
        course_4_7.setBackgroundResource(R.color.Black);
        course_5_7.setBackgroundResource(R.color.Black);
        course_6_7.setBackgroundResource(R.color.Black);

        course_1_1.setText("");
        course_2_1.setText("");
        course_3_1.setText("");
        course_4_1.setText("");
        course_5_1.setText("");
        course_6_1.setText("");
        course_1_2.setText("");
        course_2_2.setText("");
        course_3_2.setText("");
        course_4_2.setText("");
        course_5_2.setText("");
        course_6_2.setText("");
        course_1_3.setText("");
        course_2_3.setText("");
        course_3_3.setText("");
        course_4_3.setText("");
        course_5_3.setText("");
        course_6_3.setText("");
        course_1_4.setText("");
        course_2_4.setText("");
        course_3_4.setText("");
        course_4_4.setText("");
        course_5_4.setText("");
        course_6_4.setText("");
        course_1_5.setText("");
        course_2_5.setText("");
        course_3_5.setText("");
        course_4_5.setText("");
        course_5_5.setText("");
        course_6_5.setText("");
        course_1_6.setText("");
        course_2_6.setText("");
        course_3_6.setText("");
        course_4_6.setText("");
        course_5_6.setText("");
        course_6_6.setText("");
        course_1_7.setText("");
        course_2_7.setText("");
        course_3_7.setText("");
        course_4_7.setText("");
        course_5_7.setText("");
        course_6_7.setText("");

        monday.setBackgroundResource(R.drawable.tab);
        tuesday.setBackgroundResource(R.drawable.tab);
        wednesday.setBackgroundResource(R.drawable.tab);
        thursday.setBackgroundResource(R.drawable.tab);
        friday.setBackgroundResource(R.drawable.tab);
        saturday.setBackgroundResource(R.drawable.tab);
        sunday.setBackgroundResource(R.drawable.tab);
        //高亮当天
        if(weekDay==2){
            monday.setBackgroundResource(R.drawable.tab_checked);
            monday.setTextColor(getResources().getColor(R.color.Black));
        }
        else  if(weekDay==3){
            tuesday.setBackgroundResource(R.drawable.tab_checked);
            tuesday.setTextColor(getResources().getColor(R.color.Black));
        }
        else  if(weekDay==4){
            wednesday.setBackgroundResource(R.drawable.tab_checked);
            wednesday.setTextColor(getResources().getColor(R.color.Black));
        }
        else  if(weekDay==5){
            thursday.setBackgroundResource(R.drawable.tab_checked);
            thursday.setTextColor(getResources().getColor(R.color.Black));
        }
        else  if(weekDay==6){
            friday.setBackgroundResource(R.drawable.tab_checked);
            friday.setTextColor(getResources().getColor(R.color.Black));
        }
        else  if(weekDay==7){
            saturday.setBackgroundResource(R.drawable.tab_checked);
            saturday.setTextColor(getResources().getColor(R.color.Black));
        }
        else  if(weekDay==1){
            sunday.setBackgroundResource(R.drawable.tab_checked);
            sunday.setTextColor(getResources().getColor(R.color.Black));
        }
        //显示课表
        for(int i=0;i<courseList.length;i++){
            //检测该课程在week周是否有课
            if(judgeWeek(week,i)) {
                session = courseList[i].getSession();
                courseName = courseList[i].getObject();
                courseTeacher = courseList[i].getTeacher();
                classroom = courseList[i].getClassroom();
                //这里不要改，我也不知道为什么能行
                int cnt=3*(countSubstring(courseName,"[")+countSubstring(courseName,"]"));
                if(cnt!=0){
                    cnt=-1;
                }
                //
                if (courseName.getBytes().length+cnt > 27) {

                    byte[] b = courseName.getBytes();
                    //还有这里
                    b = Arrays.copyOfRange(b, 0, 27+cnt);
                    try {
                        String tempstr = new String(b, "UTF-8");
                        courseName = tempstr;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        Context context = getContext();
                        Toast.makeText(context, "截取课程名字异常(27)", Toast.LENGTH_SHORT).show();
                    }
                    text = courseName + classroom;
                } else if (courseName.getBytes().length > 18) {
                    text = courseName + "\n" + classroom;
                } else if (courseName.getBytes().length > 9) {
                    text = courseName + "\n\n" + classroom;
                } else {
                    text = courseName + "\n\n\n" + classroom;
                }

                char[] ch = new char[10];

                for (int j = 0; j < 20; j++) {
                    for (int ii = 0; ii < 10; ii++) {
                        ch[ii] = '0';
                    }
                    int counter = 0;
                    int tmp = session[j];
                    int[] se=new int[20];
                    for(int jj=0;jj<20;jj++){
                        se[jj]=session[jj];
                    }
                    for (int ii = 0; ii < 10; ii++) {
                        ch[ii] = (char) (tmp % 10 + 48);
                        tmp = (tmp - ch[ii] + 48) / 10;
                        if (ch[ii] != '0') {
                            counter++;
                        }
                        if (tmp == 0) {
                            break;
                        }
                    }
                    //对session进行识别（适配了一天多节课）并进行内容填充
                    for (int k = 0; k < 10; k++) {
                        se[j] = ch[k] - '0';
                        if (se[j] != 0) {
                            if (se[j] == 1 && j == 1) {
                                /*
                                //如果未赋值
                                if(course_1_1.getText().toString().equals("null")) {
                                    //赋值
                                    course_1_1.setBackgroundResource(R.drawable.course);
                                    course_1_1.setText(text);
                                }
                                //如果都是考试课
                                else if(course_1_1.getText().toString().contains("考试")&&text.contains("考试")){
                                        //显示冲突
                                        course_1_1.setBackgroundResource(R.drawable.course_confict);
                                }
                                //如果原来不是考试课后来是考试课
                                else if(!course_1_1.getText().toString().contains("考试")&&text.contains("考试")){
                                    //替换
                                        course_1_1.setBackgroundResource(R.drawable.course);
                                        course_1_1.setText(text);
                                }
                                //否则（原来是考试课后来不是），不做操作
                                */
                                course_1_1.setBackgroundResource(R.drawable.course);
                                course_1_1.setText(text);
                            } else if (se[j] == 2 && j == 1) {
                                course_2_1.setBackgroundResource(R.drawable.course);
                                course_2_1.setText(text);
                            } else if (se[j] == 3 && j == 1) {
                                course_3_1.setBackgroundResource(R.drawable.course);
                                course_3_1.setText(text);
                            } else if (se[j] == 4 && j == 1) {
                                course_4_1.setBackgroundResource(R.drawable.course);
                                course_4_1.setText(text);
                            } else if (se[j] == 5 && j == 1) {
                                course_5_1.setBackgroundResource(R.drawable.course);
                                course_5_1.setText(text);
                            } else if (se[j] == 6 && j == 1) {
                                course_6_1.setBackgroundResource(R.drawable.course);
                                course_6_1.setText(text);
                            } else if (se[j] == 1 && j == 2) {
                                course_1_2.setBackgroundResource(R.drawable.course);
                                course_1_2.setText(text);
                            } else if (se[j] == 2 && j == 2) {
                                course_2_2.setBackgroundResource(R.drawable.course);
                                course_2_2.setText(text);
                            } else if (se[j] == 3 && j == 2) {
                                course_3_2.setBackgroundResource(R.drawable.course);
                                course_3_2.setText(text);
                            } else if (se[j] == 4 && j == 2) {
                                course_4_2.setBackgroundResource(R.drawable.course);
                                course_4_2.setText(text);
                            } else if (se[j] == 5 && j == 2) {
                                course_5_2.setBackgroundResource(R.drawable.course);
                                course_5_2.setText(text);
                            } else if (se[j] == 6 && j == 2) {
                                course_6_2.setBackgroundResource(R.drawable.course);
                                course_6_2.setText(text);
                            } else if (se[j] == 1 && j == 3) {
                                course_1_3.setBackgroundResource(R.drawable.course);
                                course_1_3.setText(text);
                            } else if (se[j] == 2 && j == 3) {
                                course_2_3.setBackgroundResource(R.drawable.course);
                                course_2_3.setText(text);
                            } else if (se[j] == 3 && j == 3) {
                                course_3_3.setBackgroundResource(R.drawable.course);
                                course_3_3.setText(text);
                            } else if (se[j] == 4 && j == 3) {
                                course_4_3.setBackgroundResource(R.drawable.course);
                                course_4_3.setText(text);
                            } else if (se[j] == 5 && j == 3) {
                                course_5_3.setBackgroundResource(R.drawable.course);
                                course_5_3.setText(text);
                            } else if (se[j] == 6 && j == 3) {
                                course_6_3.setBackgroundResource(R.drawable.course);
                                course_6_3.setText(text);
                            } else if (se[j] == 1 && j == 4) {
                                course_1_4.setBackgroundResource(R.drawable.course);
                                course_1_4.setText(text);
                            } else if (se[j] == 2 && j == 4) {
                                course_2_4.setBackgroundResource(R.drawable.course);
                                course_2_4.setText(text);
                            } else if (se[j] == 3 && j == 4) {
                                course_3_4.setBackgroundResource(R.drawable.course);
                                course_3_4.setText(text);
                            } else if (se[j] == 4 && j == 4) {
                                course_4_4.setBackgroundResource(R.drawable.course);
                                course_4_4.setText(text);
                            } else if (se[j] == 5 && j == 4) {
                                course_5_4.setBackgroundResource(R.drawable.course);
                                course_5_4.setText(text);
                            } else if (se[j] == 6 && j == 4) {
                                course_6_4.setBackgroundResource(R.drawable.course);
                                course_6_4.setText(text);
                            } else if (se[j] == 1 && j == 5) {
                                course_1_5.setBackgroundResource(R.drawable.course);
                                course_1_5.setText(text);
                            } else if (se[j] == 2 && j == 5) {
                                course_2_5.setBackgroundResource(R.drawable.course);
                                course_2_5.setText(text);
                            } else if (se[j] == 3 && j == 5) {
                                course_3_5.setBackgroundResource(R.drawable.course);
                                course_3_5.setText(text);
                            } else if (se[j] == 4 && j == 5) {
                                course_4_5.setBackgroundResource(R.drawable.course);
                                course_4_5.setText(text);
                            } else if (se[j] == 5 && j == 5) {
                                course_5_5.setBackgroundResource(R.drawable.course);
                                course_5_5.setText(text);
                            } else if (se[j] == 6 && j == 5) {
                                course_6_5.setBackgroundResource(R.drawable.course);
                                course_6_5.setText(text);
                            } else if (se[j] == 1 && j == 6) {
                                course_1_6.setBackgroundResource(R.drawable.course);
                                course_1_6.setText(text);
                            } else if (se[j] == 2 && j == 6) {
                                course_2_6.setBackgroundResource(R.drawable.course);
                                course_2_6.setText(text);
                            } else if (se[j] == 3 && j == 6) {
                                course_3_6.setBackgroundResource(R.drawable.course);
                                course_3_6.setText(text);
                            } else if (se[j] == 4 && j == 6) {
                                course_4_6.setBackgroundResource(R.drawable.course);
                                course_4_6.setText(text);
                            } else if (se[j] == 5 && j == 6) {
                                course_5_6.setBackgroundResource(R.drawable.course);
                                course_5_6.setText(text);
                            } else if (se[j] == 6 && j == 6) {
                                course_6_6.setBackgroundResource(R.drawable.course);
                                course_6_6.setText(text);
                            } else if (se[j] == 1 && j == 7) {
                                course_1_7.setBackgroundResource(R.drawable.course);
                                course_1_7.setText(text);
                            } else if (se[j] == 2 && j == 7) {
                                course_2_7.setBackgroundResource(R.drawable.course);
                                course_2_7.setText(text);
                            } else if (se[j] == 3 && j == 7) {
                                course_3_7.setBackgroundResource(R.drawable.course);
                                course_3_7.setText(text);
                            } else if (se[j] == 4 && j == 7) {
                                course_4_7.setBackgroundResource(R.drawable.course);
                                course_4_7.setText(text);
                            } else if (se[j] == 5 && j == 7) {
                                course_5_7.setBackgroundResource(R.drawable.course);
                                course_5_7.setText(text);
                            } else if (se[j] == 6 && j == 7) {
                                course_6_7.setBackgroundResource(R.drawable.course);
                                course_6_7.setText(text);
                            }
                        }
                    }
                }
            }
        }
        //按钮着色
        if(weekMonth==1){
            setBtnColor(btnNo1);
        }
        else  if(weekMonth==2){
            setBtnColor(btnNo2);
        }
        else  if(weekMonth==3){
            setBtnColor(btnNo3);
        }
        else  if(weekMonth==4){
            setBtnColor(btnNo4);
        }
        else  if(weekMonth==5){
            setBtnColor(btnNo5);
        }
        else  if(weekMonth==6){
            setBtnColor(btnNo6);
        }
        else  if(weekMonth==7){
            setBtnColor(btnNo7);
        }
        else  if(weekMonth==8){
            setBtnColor(btnNo8);
        }
        else  if(weekMonth==9){
            setBtnColor(btnNo9);
        }
        else  if(weekMonth==10){
            setBtnColor(btnNo10);
        }
        else  if(weekMonth==11){
            setBtnColor(btnNo11);
        }
        else  if(weekMonth==12){
            setBtnColor(btnNo12);
        }
        else  if(weekMonth==13){
            setBtnColor(btnNo13);
        }
        else  if(weekMonth==14){
            setBtnColor(btnNo14);
        }
        else  if(weekMonth==15){
            setBtnColor(btnNo15);
        }
        else  if(weekMonth==16){
            setBtnColor(btnNo16);
        }
        else  if(weekMonth==17){
            setBtnColor(btnNo17);
        }
        else  if(weekMonth==18){
            setBtnColor(btnNo18);
        }

        return;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.btn_no1, R.id.btn_no2, R.id.btn_no3, R.id.btn_no4,R.id.btn_no5, R.id.btn_no6, R.id.btn_no7, R.id.btn_no8, R.id.btn_no9, R.id
            .btn_no10, R.id.btn_no11, R.id.btn_no12, R.id.btn_no13, R.id.btn_no14, R.id.btn_no15,
            R.id.btn_no16, R.id.btn_no17, R.id.btn_no18})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_no1:
                weekMonth=1;
                draw(1);
                break;
            case R.id.btn_no2:
                weekMonth=2;
                draw(2);
                break;
            case R.id.btn_no3:
                weekMonth=3;
                draw(3);
                break;
            case R.id.btn_no4:
                weekMonth=4;
                draw(4);
                break;
            case R.id.btn_no5:
                weekMonth=5;
                draw(5);
                break;
            case R.id.btn_no6:
                weekMonth=6;
                draw(6);
                break;
            case R.id.btn_no7:
                weekMonth=7;
                draw(7);
                break;
            case R.id.btn_no8:
                weekMonth=8;
                draw(8);
                break;
            case R.id.btn_no9:
                weekMonth=9;
                draw(9);
                break;
            case R.id.btn_no10:
                weekMonth=10;
                draw(10);
                break;
            case R.id.btn_no11:
                weekMonth=11;
                draw(11);
                break;
            case R.id.btn_no12:
                weekMonth=12;
                draw(12);
                break;
            case R.id.btn_no13:
                weekMonth=13;
                draw(13);
                break;
            case R.id.btn_no14:
                weekMonth=14;
                draw(14);
                break;
            case R.id.btn_no15:
                weekMonth=15;
                draw(15);
                break;
            case R.id.btn_no16:
                weekMonth=16;
                draw(16);
                break;
            case R.id.btn_no17:
                weekMonth=17;
                draw(17);
                break;
            case R.id.btn_no18:
                weekMonth=18;
                draw(18);
                break;

        }
    }
}
