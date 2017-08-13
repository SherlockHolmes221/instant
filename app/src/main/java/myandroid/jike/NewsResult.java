package myandroid.jike;

import java.util.List;

/**
 * Created by quxia on 2017/8/14.
 */

//返回的新闻内容
public class NewsResult {

    private String reason;
    private String stat;
    private List<NewsBean> newsBeanList;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<NewsBean> getNewsBeanList() {
        return newsBeanList;
    }

    public void setNewsBeanList(List<NewsBean> newsBeanList) {
        this.newsBeanList = newsBeanList;
    }

    @Override
    public String toString() {
        return "NewsResult{" +
                "reason='" + reason + '\'' +
                ", stat='" + stat + '\'' +
                ", newsBeanList=" + newsBeanList +
                '}';
    }
}
