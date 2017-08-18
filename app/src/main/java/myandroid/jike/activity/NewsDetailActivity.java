package myandroid.jike.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.jh.swipeback.SwipeBackLayout;
import com.example.jh.swipeback.app.SwipeBackActivity;

import myandroid.jike.R;
import myandroid.jike.utils.ImageLoaderUtils;

/**
 * Created by quxia on 2017/8/18.
 */
public class NewsDetailActivity extends SwipeBackActivity {

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private SwipeBackLayout mSwipeBackLayout;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discover_news_detail);
        android.support.v7.widget.Toolbar  toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.id_discover_news_detail_toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.id_discover_news_detail_progress);
        mWebView = (WebView) findViewById(R.id.id_discover_news_detail_webView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("newsInfo");
        final String image1 = bundle.getString("image1");
        final String image2 = bundle.getString("image2");
        final String image3 = bundle.getString("image3");
        String url = bundle.getString("url");
        String title = bundle.getString("title");

        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.id_discover_news_detail_image),image1);
        mWebView.loadUrl(url);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.id_discover_news_detail_collapsing_toolbar);
        collapsingToolbar.setTitle(title);

        showProgress();

        new Handler().postDelayed(new Runnable(){
            public void run() {
                hideProgress();
            }
        }, 3000);
    }


    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    public static final int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }
}
