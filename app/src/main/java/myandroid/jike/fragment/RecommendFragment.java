package myandroid.jike.fragment;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import myandroid.jike.R;
import myandroid.jike.activity.MainActivity;
import myandroid.jike.activity.NewsDetailActivity;
import myandroid.jike.adapter.NewsAdapter;
import myandroid.jike.adapter.NewsAdapter2;
import myandroid.jike.interfaces.OnLoadNewsResultListener;
import myandroid.jike.news.NewsBean;
import myandroid.jike.Sqlite.DatabaseHelper;
import myandroid.jike.utils.NewsUtils;

/**
 * Created by caiyiqi on 2017/8/15.
 */

public class RecommendFragment extends Activity implements SwipeRefreshLayout.OnRefreshListener,OnLoadNewsResultListener{

    private Context context;
    private TextView textView;
    NewsUtils newsUtils;
    List<NewsBean> listNewsBean;
    ListView listView;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message message) {
            listNewsBean = (List<NewsBean>) message.obj;
            NewsAdapter2 newsAdapter = new NewsAdapter2(MainActivity.this, listNewsBean);
            listView.setAdapter(newsAdapter);
        }
    };

    private SwipeRefreshLayout swipeRefreshLayout;

    private DatabaseHelper databaseHelper;
    private List<String> attentionList = new ArrayList<>();
    @Override
    public void onCreate(LayoutInflater inflater,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        View view = inflater.inflate(R.layout.recommend);
        context = RecommendFragment.this;
        listView = (ListView)findViewById(R.id.recommend_list);
        newsUtils = new NewsUtils();

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.recommend_srl);
        swipeRefreshLayout.setOnRefreshListener(this);

        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
        attentionList = databaseHelper.getAttentionList();

        //加载数据
        onRefresh();
        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (newsBeanList.size() <= 0) {
                    return;
                }
                NewsBean news = adapter.getItem(position);
                Intent intent =new Intent(getActivity(), NewsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", news.getTitle());
                bundle.putString("url",news.getUrl());
                bundle.putString("image1",news.getThumbnail_pic_s());
                bundle.putString("image2",news.getThumbnail_pic_s02());
                bundle.putString("image3",news.getThumbnail_pic_s03());
            }
        });

    }

}