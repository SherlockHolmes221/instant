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

import java.util.ArrayList;
import java.util.List;

import myandroid.jike.R;
import myandroid.jike.activity.NewsDetailActivity;
import myandroid.jike.adapter.NewsAdapter;
import myandroid.jike.interfaces.OnLoadNewsResultListener;
import myandroid.jike.news.NewsBean;
import myandroid.jike.news.NewsResult;
import myandroid.jike.utils.NewsJsonUtils;


/**
 * Created by quxia on 2017/8/15.
 */
public class DiscoverFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,OnLoadNewsResultListener {

    private final static String TAG = "DiscoverFragment";
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private List<NewsBean> mNewsBeanList = new ArrayList<>();
    private int pageIndex;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover, null);

        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.id_discover_swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);


        mRecyclerView = (RecyclerView)view.findViewById(R.id.id_discover_recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new NewsAdapter(getContext(),mNewsBeanList);
        mRecyclerView.setAdapter(mAdapter);
        //加载数据
        onRefresh();

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

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()
                        && mAdapter.isShowFooter()) {
                    //加载更多
                    Log.e("TAG", "loading more data");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
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
        //更新mNewsBeanList
        String type  = "top";
        NewsJsonUtils.getNews(type,this);
        mAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable(){
            public void run() {
                hideProgress();
            }
        }, 3000);
//        if(mNewsBeanList != null) {
//            mNewsBeanList.clear();
//        }
    }

    //加载成功则添加到mNewsBeanList
    @Override
    public void onSuccess(NewsResult newsResult) {
       //addNews(newsResult.getNewsBeen());
        int size  =newsResult.getNewsBeen().size();
        for(int i = 0;i< size ;i++){
            this.mNewsBeanList.add(0,newsResult.getNewsBeen().get(i));
        }
        Log.e(TAG,"onRefresh");
    }

    @Override
    public void onFailure(String msg, Exception e) {
        showLoadFailMsg();
        Log.e(TAG,"failure");
    }
}
