package com.example.zkx74;

/**
 * Created by zkx74 on 2018/3/2 0002.
 */

public class Score {
    private String object = "";
    private String credit = "";
    private String grade = "";
    private String type = "";
    private String time = "";

    public String getObject() {
        return object;
    }

    public String getCredit() {
        return credit;
    }

    public String getGrade() {
        return grade;
    }

    public String getType() {return type;}

    public String getTime() {return time;}

    public void setAll(String time, String object, String type, String credit, String grade) {
        this.object = object;
        this.credit = credit;
        this.grade = grade;
        this.type = type;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setType(String type) {this.type = type;}

    public void setTime(String time) {this.time = time;}
}
