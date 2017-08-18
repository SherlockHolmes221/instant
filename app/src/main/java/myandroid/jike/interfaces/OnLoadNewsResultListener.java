package myandroid.jike.interfaces;

import myandroid.jike.news.NewsResult;

/**
 * 新闻列表加载回调
 */

public interface OnLoadNewsResultListener {
    void onSuccess(NewsResult newsResult);
    void onFailure(String msg, Exception e);
}
