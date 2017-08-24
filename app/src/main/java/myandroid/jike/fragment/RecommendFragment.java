package myandroid.jike.fragment;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Recommend extends ListActivity {
    public SwipeRefreshLayout refreshLayout;
    ListView recommendLv = null;
    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
    public class item

    public static String getHtml(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(2000);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
        printWriter.write(post);
        InputStream inputStream = connection.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        return outputStream.toString("utf-8");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });

        recommendLv =getListView();

        refreshData();
        refreshLayout.setRefreshing(false);
    }

    public void refreshData() {
        refreshData();
    }

}
