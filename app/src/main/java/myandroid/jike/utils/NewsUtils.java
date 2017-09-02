package myandroid.jike.utils;

import android.content.Context;
import android.renderscript.ScriptGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import myandroid.jike.news.NewsBean;

/**
 * Created by caiyiqi on 2017/8/27.
 */

public class NewsUtils {
    public static ArrayList<NewsBean> getNetNews(Context context, String urlString) {
        ArrayList<NewsBean> arrayListNews = new ArrayList<NewsBean>();
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20*1000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //获取请求到的流信息
                InputStream inputStream = connection.getInputStream();
                //通过之前建立StreamUtils工具类转换交换流
                String result = StreamUtils.convertStream(inputStream);

                Gson gson = new Gson();
                List<NewsBean> newsBeanList = gson.fromJson(result, new TypeToken<List<NewsBean>>() {
                }.getType());
                for (NewsBean newsBean : newsBeanList) {
                    System.out.println("uniquekey" + newsBean.getUniquekey() + ";title" + newsBean.getTitle() +
                            ";date" + newsBean.getDate() + ";category" + newsBean.getCategory() + ";author_name" +
                            newsBean.getAuthor_name() + ";url" + newsBean.getUrl() + ";thumbnail_pic_s" +
                            newsBean.getThumbnail_pic_s() + ";thumbnail_pic_s02" + newsBean.getThumbnail_pic_s02() +
                            ";thumbnail_pic_s03" + newsBean.getThumbnail_pic_s03());
                    arrayListNews.add(newsBean);
                }
                inputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayListNews;
    }
}
