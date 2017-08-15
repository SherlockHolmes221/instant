package myandroid.jike.news;

import java.util.List;

/**
 * Created by quxia on 2017/8/14.
 */

//返回的新闻内容
public class NewsResult {

    private String reason;

    private int stat;
    List<NewsBean> newsBeen ;
    private int error_code;


    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public List<NewsBean> getNewsBeen() {
        return newsBeen;
    }

    public void setNewsBeen(List<NewsBean> newsBeen) {
        this.newsBeen = newsBeen;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "NewsResult{" +
                "reason='" + reason + '\'' +
                ", stat=" + stat +
                ", newsBeen=" + newsBeen +
                ", error_code=" + error_code +
                '}';
    }
}
