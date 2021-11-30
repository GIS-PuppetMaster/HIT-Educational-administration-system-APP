package com.example.zkx74;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.jar.Attributes;

/**
 * Created by zkx74 on 2018/3/1 0001.
 */

public class ScheduleLayout extends RelativeLayout {

    private int todaynum;//周日=0
    private int[] US_daynum={7,1,2,3,4,5,6};
    private  String[] datesOfmonth;

    public ScheduleLayout(Context context, AttributeSet attrs,int defStyleAttr) {
        super(context, attrs,defStyleAttr);
    }
    public ScheduleLayout(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }
    public ScheduleLayout(Context context) {
        super(context, null);
    }
    private void init(){
        Calendar todayCal=Calendar.getInstance();

        todayCal.setTimeInMillis((System.currentTimeMillis()));//设置为今天
        todayCal.get(Calendar.DAY_OF_WEEK);//设置今天为美历周几
        todaynum=todayCal.get(Calendar.DAY_OF_WEEK)-1;
        //datesOfmonth=getOneWeekDatesOfMonth();

        //drawFrame();
    }
    private int totalDay=7;
    private String preMonth;

   /* private String[] getOneWeekDatesOfMonth(){
        Calendar tempCal=Calendar.getInstance();
        String[] temp=new String[totalDay];
    }
   */
}
