package myandroid.jike.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myandroid.jike.R;
import myandroid.jike.Sqlite.DatabaseHelper;
import myandroid.jike.view.FlowLayout;

/**
 * Created by quxia on 2017/8/11.
 */
public class ShowAttentionListActivity extends Activity {

    private FlowLayout mFlowLayout;
    private DatabaseHelper databaseHelper;
    private List<String> attentionList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.mine_show_attention_list);
        mFlowLayout = (FlowLayout) findViewById(R.id.id_mine_showListFlowLayout);
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        databaseHelper = new DatabaseHelper(this);
        attentionList = databaseHelper.getAttentionList();
        Toast.makeText(this,attentionList.toString(),Toast.LENGTH_SHORT).show();

        for(int i = 0;i < attentionList.size(); i++){
            TextView textView = (TextView)layoutInflater.inflate(
                    R.layout.mine_show_attention_list_item,mFlowLayout,false);
            textView.setText(attentionList.get(i));
            mFlowLayout.addView(textView);

        }
    }
}
