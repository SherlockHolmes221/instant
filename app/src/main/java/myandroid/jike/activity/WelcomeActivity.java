package myandroid.jike.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myandroid.jike.R;
import myandroid.jike.Sqlite.DatabaseHelper;

//欢迎界面，只在开始时出现，用户选择关注的内容
public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    Context context = WelcomeActivity.this;

    private GridView mGridView;

    private Button mButton;

    private List<Map<String, Object>> dataList;

    private int[] icon = {
            R.drawable.movie, R.drawable.constellation, R.drawable.book,
            R.drawable.nba, R.drawable.score, R.drawable.funny,
            R.drawable.news, R.drawable.idiom, R.drawable.tv};
    private String[] iconName = {"影视", "星座", "图书",
                                   "NBA", "足球", "笑话",
                                  "新闻", "成语", "电视"};

    private List<String> attentionList = new ArrayList<String>();

    boolean  []attention = {false,false,false,false,false,false,false,false,false};

    private SimpleAdapter adapter;

   private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        preferences = getSharedPreferences("WelcomeActivity", Context.MODE_PRIVATE);
        //判断是不是首次登录，
        if (preferences.getBoolean("firstStart", true)) {
            editor = preferences.edit();
            //将登录标志位设置为false，下次登录时不在显示首次登录界面
            editor.putBoolean("firstStart", false);
            editor.apply();
        }else{
            Intent intent = new Intent();
            intent.setClass(this,MainActivity.class);
            startActivity(intent);
        }


            mButton = (Button) findViewById(R.id.id_welcome_enter);
           mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                databaseHelper = new DatabaseHelper(context);
                databaseHelper.insertData(attentionList);

                List<String>  list  =databaseHelper.getAttentionList();
               //Toast.makeText(context,list.toString(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mGridView = (GridView) findViewById(R.id.id_welcome_gridView);

        /*1.准备数据源
        2.新建适配器
        3.GridView加载适配器
        4.GridView配置事件监听器 (OnItemClickListener)  */

        dataList = new ArrayList<Map<String, Object>>();
        adapter = new SimpleAdapter(this, getData(), R.layout.welcome_gridview_item, new String[]{"image", "text"}, new int[]{R.id.image, R.id.text});
        mGridView.setAdapter(adapter);


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //view是当前item的view，通过它可以获得该项中的各个组件。
                //i是当前item的ID。这个id根据你在适配器中的写法可以自己定义。
                //arg3是当前的item在listView中的相对位置！

                Button button = (Button) view.findViewById(R.id.id_welcome_button);
                if(!attention[i]){
                    Toast.makeText(context,"已关注"+iconName[i],Toast.LENGTH_SHORT).show();
                    attentionList.add(iconName[i]);
                    attention[i] = true;
                    button.setAlpha(0.8f);
                    button.setBackgroundColor(getResources().getColor(R.color.button_press));
                    button.setText("√");
                    button.setTextColor(Color.BLUE);
                }else {
                    Toast.makeText(context,"已取消关注"+iconName[i], Toast.LENGTH_SHORT).show();
                    attentionList.remove(iconName[i]);
                    attention[i] = false;
                    button.setAlpha(1.0f);
                    button.setBackgroundColor(getResources().getColor(R.color.button_normal));
                    button.setText("+");
                    button.setTextColor(Color.WHITE);
                }
            }
        });
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

}
