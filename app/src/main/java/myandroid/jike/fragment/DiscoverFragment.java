package myandroid.jike.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myandroid.jike.R;
import myandroid.jike.activity.NewsDetailActivity;
import myandroid.jike.adapter.NewsAdapter;
import myandroid.jike.interfaces.OnLoadNewsResultListener;
import myandroid.jike.news.NewsBean;
import myandroid.jike.news.NewsResult;
import myandroid.jike.sqlite.DatabaseHelper;
import myandroid.jike.utils.NewsJsonUtils;
import myandroid.jike.view.AutoSwipeRefreshLayout;


/**
 * Created by quxia on 2017/8/15.
 */
public class DiscoverFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,OnLoadNewsResultListener {

    private final static String TAG = "DiscoverFragment";
    private AutoSwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private List<NewsBean> mNewsBeanList = new ArrayList<>();
    private int pageIndex;

    private static int index = 1;

    private DatabaseHelper databaseHelper;
    private List<String> attentionList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover, null);

        mSwipeRefreshWidget = (AutoSwipeRefreshLayout) view.findViewById(R.id.id_discover_swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        databaseHelper = new DatabaseHelper(view.getContext());
        attentionList = databaseHelper.getAttentionList();

        mRecyclerView = (RecyclerView)view.findViewById(R.id.id_discover_recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new NewsAdapter(getContext(),mNewsBeanList);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                   if(mNewsBeanList.size() <= 0){
                       return;
                   }
                NewsBean news = mAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", news.getTitle());
                bundle.putString("url", news.getUrl());
                bundle.putString("image1",news.getThumbnail_pic_s());
                bundle.putString("image2",news.getThumbnail_pic_s02());
                bundle.putString("image3",news.getThumbnail_pic_s03());
                intent.putExtra("newsInfo",bundle);

                View transitionView = view.findViewById(R.id.id_discover_news_Image);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                transitionView,"transition_news_img");
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //滑动到了底部时刷新
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mAdapter.isShowFooter()) {
                    //加载更多
                    mAdapter.setShowFooter(false);
                    onRefresh();
//                    mAdapter.notifyDataSetChanged();
//                    mRecyclerView.requestLayout();
                    Log.e("TAG", "loading more data");
                    Toast.makeText(getContext(),"已加载到最新",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastVisibleItem = linearManager.findLastVisibleItemPosition();
                }
                if( lastVisibleItem + 1 == mAdapter.getItemCount()){
                    mAdapter.setShowFooter(true);
                }else
                    mAdapter.setShowFooter(false);
            }
        });

        //加载数据
        mSwipeRefreshWidget.autoRefresh();
        mAdapter.notifyDataSetChanged();
        return view;
    }

    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    public void addNews(List<NewsBean> newsList) {
        mAdapter.setShowFooter(true);
        if(mNewsBeanList == null) {
            mNewsBeanList = new ArrayList<NewsBean>();
        }
        mNewsBeanList.addAll(newsList);
        if(pageIndex == 0) {
            mAdapter.setmNewsBeanList(mNewsBeanList);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(newsList == null || newsList.size() == 0) {
                mAdapter.setShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += 1;
    }

    public void showLoadFailMsg() {
        if(pageIndex == 0) {
            mAdapter.setShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.id_discover_drawer_layout);
        Snackbar.make(view, "加载数据失败!", Snackbar.LENGTH_SHORT).show();
    }

    //页面的更新
    @Override
    public void onRefresh() {
        showProgress();
       // Toast.makeText(getContext(),"正在加载",Toast.LENGTH_SHORT).show();
        //更新mNewsBeanList
        String type = "top";
        int size = attentionList.size();
        int i = index % size;
        if(attentionList.get(i).equals("头条")){
            type  = "top";
        }
        if(attentionList.get(i).equals("社会")){
            type  = "shehui";
        }
        if(attentionList.get(i).equals("国内")){
            type  = "guonei";
        }
        if(attentionList.get(i).equals("国际")){
            type  = "guoji";
        }
        if(attentionList.get(i).equals("娱乐")){
            type  = "yule";
        }
        if(attentionList.get(i).equals("体育")){
            type  = "tiyu";
        }
        if(attentionList.get(i).equals("军事")){
            type  = "junshi";
        }
        if(attentionList.get(i).equals("科技")){
            type  = "keji";
        }
        if(attentionList.get(i).equals("财经")){
            type  = "caijing";
        }
        if(attentionList.get(i).equals("时尚")){
            type  = "shishang";
        }
        index ++;
        NewsJsonUtils.getNews(type,this);
        mAdapter.notifyDataSetChanged();

        new Handler().postDelayed(new Runnable(){
            public void run() {
                hideProgress();
            }
        }, 1000);
        Log.e(TAG,"onRefresh");

    }

    //加载成功则添加到mNewsBeanList
    @Override
    public void onSuccess(NewsResult newsResult) {
       //addNews(newsResult.getNewsBeen());
        int size  =newsResult.getNewsBeen().size();
        for(int i = 0;i< size ;i++){
            this.mNewsBeanList.add(0,newsResult.getNewsBeen().get(i));
        }
        //Log.e(TAG,"onRefresh");
        mAdapter.setmNewsBeanList(mNewsBeanList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.requestLayout();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        showLoadFailMsg();
        Log.e(TAG,"failure");
    }
}
