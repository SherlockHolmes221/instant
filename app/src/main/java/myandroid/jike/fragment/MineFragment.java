package myandroid.jike.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import myandroid.jike.R;
import myandroid.jike.activity.ShowAttentionListActivity;
import myandroid.jike.Sqlite.DatabaseHelper;


public class MineFragment extends Fragment{

        private ListView mListView;
        private ImageButton mImageButton;
        private LinearLayout mLinearLayout;
        private List<String> attentionList = new ArrayList<>();

        private DatabaseHelper databaseHelper;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine,container,false);

        mListView = (ListView) view.findViewById(R.id.id_mine_listView);
        mImageButton = (ImageButton) view.findViewById(R.id.id_mine_setting);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.id_mine_login);
            databaseHelper = new DatabaseHelper(view.getContext());
        InitListView();

        return view;

    }

    private void InitListView() {

        ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();

        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("ItemImage",R.drawable.list_item1);
        map1.put("ItemText","我关注的主题");
        listItem.add(map1);

        HashMap<String,Object> map2 = new HashMap<String,Object>();
        map2.put("ItemImage",R.drawable.list_item2);
        map2.put("ItemText","我的收藏");
        listItem.add(map2);

        HashMap<String,Object> map3 = new HashMap<String,Object>();
        map3.put("ItemImage",R.drawable.list_item3);
        map3.put("ItemText","动态通知");
        listItem.add(map3);

        HashMap<String,Object> map4 = new HashMap<String,Object>();
        map4.put("ItemImage",R.drawable.list_item4);
        map4.put("ItemText","小秘书");
        listItem.add(map4);

        HashMap<String,Object> map5 = new HashMap<String,Object>();
        map5.put("ItemImage",R.drawable.list_item5);
        map5.put("ItemText","创建主题");
        listItem.add(map5);

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(getContext(),listItem,R.layout.mine_list_item,
                new String[]{"ItemImage","ItemText"},
                new int[]{R.id.id_mine_listView_image, R.id.id_mine_listView_text});

        mListView.setAdapter(mSimpleAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:  //我关注的主题
                        Intent intent = new Intent();
                        intent.setClass(getContext(),ShowAttentionListActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
