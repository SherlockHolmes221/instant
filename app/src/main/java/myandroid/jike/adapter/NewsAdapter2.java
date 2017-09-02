package myandroid.jike.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import myandroid.jike.news.NewsBean;
import myandroid.jike.view.MyImageView;

/**
 * Created by caiyiqi on 2017/8/25.
 */

public class NewsAdapter2 extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private List<NewsBean> datas; //获取到新闻合集
    public NewsAdapter2(Context context, List<NewsBean> listNewsBean) {
        this.layoutInflater = layoutInflater.from(context);
        this.datas = listNewsBean;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.RandA_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.item_img_icon = (ImageView)convertView.findViewById(R.id.item_img_icon);
            viewHolder.item_tv_type = (TextView)convertView.findViewById(R.id.item_tv_type);
            viewHolder.item_tv_time = (TextView)convertView.findViewById(R.id.item_tv_time);
            viewHolder.item_tv_title = (TextView)convertView.findViewById(R.id.item_tv_title);
            viewHolder.item_img_pic1 = (MyImageView)convertView.findViewById(R.id.item_img_pic1);
            viewHolder.item_img_pic2 = (MyImageView)convertView.findViewById(R.id.item_img_pic2);
            viewHolder.item_img_pic3 = (MyImageView)convertView.findViewById(R.id.item_img_pic3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewsBean newsBean = datas.get(position);
        viewHolder.item_img_icon.setImageDrawable(Drawable.newsBean.getCategory());
        viewHolder.item_tv_type.setText(newsBean.getCategory());
        viewHolder.item_tv_time.setText(newsBean.getDate());
        viewHolder.item_tv_title.setText(newsBean.getTitle());
        viewHolder.item_img_pic1.setImageUrl(newsBean.getThumbnail_pic_s());
        viewHolder.item_img_pic2.setImageUrl(newsBean.getThumbnail_pic_s02());
        viewHolder.item_img_pic3.setImageUrl(newsBean.getThumbnail_pic_s03());

    }
    class ViewHolder {
        ImageView item_img_icon;
        TextView item_tv_type;
        TextView item_tv_time;
        TextView item_tv_title;
        MyImageView item_img_pic1,item_img_pic2,item_img_pic3;
    }
}
