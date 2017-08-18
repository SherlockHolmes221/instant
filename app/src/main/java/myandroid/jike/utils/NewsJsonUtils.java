package myandroid.jike.utils;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import myandroid.jike.interfaces.OnLoadNewsResultListener;
import myandroid.jike.news.NewsBean;
import myandroid.jike.news.NewsResult;

/**
 * Created by quxia on 2017/7/1.
 */
public class NewsJsonUtils {
    private static final  String URL = "http://v.juhe.cn/toutiao/index";
    private static final  String API_KEY = "74ad46381138f7933836b8267aa48b58";


    //将meg转化成请求实例的格式的url
    private static String setParams(String msg) {
        String url = null;
        url = URL+ "?type="+msg+"&key="+API_KEY;
        return url;
    }

    //获取新闻结果类
    public static void getNews(String msg, final OnLoadNewsResultListener listener){
        String url = setParams(msg);

        OkHttpUtils.ResultCallback<String> loadNewsCallback =new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsResult newsResult = readJsonNewsBeans(response);
                Log.e("getNews",newsResult.toString());
                listener.onSuccess(newsResult);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("getNews","获取新闻失败");
            }
        };
        OkHttpUtils.get(url,loadNewsCallback);
    }


    // 将获取到的json转换为新闻列表对象
    public static NewsResult readJsonNewsBeans(String res) {

        NewsResult newsResult = new NewsResult();
        String jsonRes =res ;
        //Log.e("TAG",jsonRes);
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(jsonRes).getAsJsonObject();

        newsResult  =JsonUtils.deserialize(jsonObj,NewsResult.class);

        JsonElement jsonElementResult = jsonObj.get("result");
        String s= jsonElementResult.toString();

        JsonObject jsonObjectResult = parser.parse(s).getAsJsonObject();
        JsonElement jsonElementStat = jsonObjectResult.get("stat");
        newsResult.setStat(jsonElementStat.getAsInt());

        JsonElement jsonElementData= jsonObjectResult.get("data");
        JsonArray jsonArrayData = jsonElementData.getAsJsonArray();


        List<NewsBean> newsBeens = new ArrayList<>();
        for(int i = 0;i<jsonArrayData.size();i++){
            JsonObject jo = jsonArrayData.get(i).getAsJsonObject();
            NewsBean news = JsonUtils.deserialize(jo, NewsBean.class);
            newsBeens.add(news);
        }
        newsResult.setNewsBeen(newsBeens);
        //Log.e("readJsonNewsBeans",newsResult.toString());
        //Log.e("readJsonNewsBeans",newsResult.getNewsBeen().get(0).getUrl());
        return newsResult;
    }

}
