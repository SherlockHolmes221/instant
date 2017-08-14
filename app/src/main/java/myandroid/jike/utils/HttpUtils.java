package myandroid.jike.utils;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import myandroid.jike.NewsResult;

/**
 * Created by quxia on 2017/7/1.
 */
public class HttpUtils {
    private static final  String URL = "http://v.juhe.cn/toutiao/index";
    private static final  String API_KEY = "74ad46381138f7933836b8267aa48b58";

    //通过调用sendMessage返回NewsResult类型的信息
    public static NewsResult sendMessage(String msg){
        NewsResult newsResult = new NewsResult();
        try {
            String jsonRes = doGet(msg);

            Gson gson = new Gson();
            newsResult = gson.fromJson(jsonRes,NewsResult.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return newsResult;
    }

    public static String doGet(String msg) throws MalformedURLException {
        String result = "";
        String url = setParams(msg);
        InputStream input = null;
        ByteArrayOutputStream baos = null;
        try {
            java.net.URL urlNet= new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");//请求方式为get或者post

            input = conn.getInputStream();
            int len = -1;
            byte[] buf = new byte[128];
            baos = new ByteArrayOutputStream();

            while((len = input.read(buf))!=-1){
                baos.write(buf,0,len);
            }
            baos.flush();
            result = new String(baos.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    //将meg转化成请求实例的格式的url
    private static String setParams(String msg) {
        String url = null;
        url = URL+ "?type="+msg+"&key="+API_KEY;
        return url;
    }
}
