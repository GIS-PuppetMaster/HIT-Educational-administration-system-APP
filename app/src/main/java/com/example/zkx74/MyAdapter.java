package com.example.zkx74;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by zkx74 on 2018/3/8 0008.
 */

public class MyAdapter extends BaseAdapter {

    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public MyAdapter(Context context, List<Map<String,Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    private int dp2px(Context context, float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
    public final class Zujian{
        public TextView course;
        public TextView course_score;
        public TextView credit;
        public LinearLayout LL_List;
        }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        Zujian zujian=null;
        if(convertview==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertview=layoutInflater.inflate(R.layout.listview,null);
            zujian.course=(TextView)convertview.findViewById(R.id.course);
            zujian.course_score=(TextView)convertview.findViewById(R.id.course_score);
            zujian.credit=(TextView)convertview.findViewById(R.id.credit);
            zujian.LL_List=(LinearLayout)convertview.findViewById(R.id.LL_List);
            convertview.setTag(zujian);
        }
        else{
            zujian=(Zujian)convertview.getTag();
        }
        //绑定数据
        zujian.course.setText((String)data.get(position).get("course"));
        zujian.course_score.setText((String)data.get(position).get("course_score"));
        zujian.credit.setText((String)data.get(position).get("credit"));
        if(((String) data.get(position).get("course")).contains("春季")||((String) data.get(position).get("course")).contains("夏季")||((String) data.get(position).get("course")).contains("秋季")||((String) data.get(position).get("course")).contains("冬季")){
            zujian.course.setTextColor(ContextCompat.getColor(context, R.color.White));
            zujian.course.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            zujian.LL_List.setBackgroundResource(R.drawable.score_list_tab);
            zujian.credit.setVisibility(View.GONE);
            zujian.course_score.setVisibility(View.GONE);
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) zujian.course.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams.weight = dp2px(context,250);// 控件的宽强制设成
            zujian.course.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
            //代码bug，原因未知，显示不正常
            /*
            LinearLayout.LayoutParams linearParams2 =(LinearLayout.LayoutParams) zujian.LL_List.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams2.height = dp2px(context,15);// 控件的高强制设成30
            zujian.LL_List.setLayoutParams(linearParams2); //使设置好的布局参数应用到控件
            */
        }
        else {
            double y = Double.valueOf(data.get(position).get("course_score").toString()).doubleValue();
            if (y < 60) {
                zujian.course.setTextColor(ContextCompat.getColor(context, R.color.Black));
                zujian.course_score.setTextColor(ContextCompat.getColor(context, R.color.Black));
                //过期写法
                //zujian.course_score.setTextColor(context.getResources().getColor(R.color.Black));
                zujian.LL_List.setBackgroundResource(R.drawable.score_list_fail);
            }
        }
        return convertview;
    }
}
