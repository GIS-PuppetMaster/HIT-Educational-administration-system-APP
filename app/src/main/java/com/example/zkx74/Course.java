package com.example.zkx74;

import java.util.ArrayList;
/**
 * Created by zkx74 on 2018/3/1 0001.
 */
/**
 * 课程参数：
 * 节数 session
 * 学科 object
 * 教师 teacher
 * 教室 classroom
 * 上课周(起始) weekS
 * 上课周(结束) weekE
 */

import java.util.ArrayList;

/**
 * 方法列表
 * 设置课程 setCourse
 * 设置节数 setSession
 * 设置学科 setObject
 * 设置教师 setTeacher
 * 设置教室 setClassroom
 * 设置上课周(起始） setWeekS
 * 设置上课周(结束)  setWeekE
 *
 * 获取节数 setSession
 * 获取学科 setObject
 * 获取教师 setTeacher
 * 获取教室 setClassroom
 * 获取上课周(起始) setWeekS
 * 获取上课周(结束) setWeekE
 */
public class Course {
    private int session[]=new int[20];
    private int week[]=new int[21];
    private String object="";
    private String teacher="";
    private String classroom="";
    public Course(){
        for(int i=0;i<20;i++){
            session[i]=0;
            week[i]=0;
        }
        week[20]=0;
        object="";
        teacher="";
        classroom="";
    }
    public void Course(int[] session,int[] week,String object,String teacher,String classroom){
        this.session=session;
        this.week=week;
        this.object=object;
        this.teacher=teacher;
        this.classroom=classroom;
    }

    /*get函数*/
    public int[] getSession(){
        return session;
    }
    public int[] getWeekS(){
        return week;
    }
    public String getObject(){
        return object;
    }
    public String getTeacher(){
        return teacher;
    }
    public String getClassroom(){
        return classroom;
    }

    /*set函数*/
    public void setSession(int[] session){
        this.session=session;
    }
    public void setWeek(int[] week){
        this.week=week;
    }
    public void setObject(String object){
        if(this.object.length()>9)
        {
            this.object.substring(0,8);//课程名称不能超过9个汉字
        }
        else {
            this.object = object;
        }
    }
    public void setTeacher(String teacher){
        this.teacher=teacher;
    }
    public void setClassroom(String classroom){
        this.classroom=classroom;
    }
}
