package myandroid.jike.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import myandroid.jike.R;
import myandroid.jike.sqlite.DatabaseHelper;
import myandroid.jike.view.FlowLayout;

/**
 * Created by quxia on 2017/8/11.
 */

//流式布局显示关注与不关注的内容,实现点击关注或者点击取消关注
public class ShowAttentionListActivity extends AppCompatActivity {

    private FlowLayout mAttentionFlowLayout; //关注关键词的布局
    private FlowLayout mUnAttentionFlowLayout;//不关注关键词的布局

    private DatabaseHelper databaseHelper;
    private List<String> attentionList = new ArrayList<>();

    private String[] strings = {"头条", "社会", "国内",
                                 "国际", "娱乐", "体育",
                                 "军事", "科技", "财经","时尚"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_show_attention_list);

        mAttentionFlowLayout = (FlowLayout) findViewById(R.id.id_mine_showAttentionListFlowLayout);
        mUnAttentionFlowLayout = (FlowLayout) findViewById(R.id.id_mine_showUnAttentionListFlowLayout);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        databaseHelper = new DatabaseHelper(this);
        attentionList = databaseHelper.getAttentionList();

       // Toast.makeText(this,attentionList.toString(),Toast.LENGTH_SHORT).show();


        for(int i = 0;i < attentionList.size(); i++){

            final TextView textView = (TextView)layoutInflater.inflate(
                    R.layout.mine_show_attention_list_item,mAttentionFlowLayout,false);
            final TextView textView1 = (TextView)layoutInflater.inflate(
                    R.layout.mine_show_attention_list_item,mUnAttentionFlowLayout,false);

            textView.setText(attentionList.get(i));
            textView1.setText(attentionList.get(i));

            mAttentionFlowLayout.addView(textView);


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mUnAttentionFlowLayout.addView(textView1);
                    mUnAttentionFlowLayout.invalidate();
                    mUnAttentionFlowLayout.requestLayout();

                    textView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });

                    mAttentionFlowLayout.removeView(textView);

                    attentionList.remove(textView.getText());
                    databaseHelper.deleteAllData();
                    databaseHelper.insertData(attentionList);
                }
            });
        }


        for(int i = 0;i < strings.length; i++){

            final TextView textView = (TextView)layoutInflater.inflate(
                    R.layout.mine_show_attention_list_item,mAttentionFlowLayout,false);
            final TextView textView1 = (TextView)layoutInflater.inflate(
                    R.layout.mine_show_attention_list_item,mUnAttentionFlowLayout,false);

            String s = strings[i];
            textView.setText(s);
            textView1.setText(s);

            boolean b = true;
            for(int j = 0;j < attentionList.size(); j++){
                if(s.equals(attentionList.get(j))){
                    b = false;
                    break;
                }
            }
            if(b){
                mUnAttentionFlowLayout.addView(textView1);

                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAttentionFlowLayout.addView(textView);
                        mAttentionFlowLayout.invalidate();
                        mAttentionFlowLayout.requestLayout();

                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });

                        mUnAttentionFlowLayout.removeView(textView1);

                        attentionList.add((String) textView1.getText());
                        databaseHelper.deleteAllData();
                        databaseHelper.insertData(attentionList);
                    }
                });
            }
        }
    }

}
