package myandroid.jike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import myandroid.jike.R;
import myandroid.jike.news.NewsBean;
import myandroid.jike.utils.ImageLoaderUtils;

/**
 * Created by quxia on 2017/8/15.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

     private LayoutInflater mInflater;
    private List<NewsBean> mNewsBeanList = new ArrayList<>();
      private Context mContext;
     private OnItemClickListener mOnItemClickListener;
     private boolean isShowFooter = true;

    public NewsAdapter(Context context, List<NewsBean> newsBeanList) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mNewsBeanList = newsBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if(!isShowFooter) {//不是最后
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {//最后
            return TYPE_FOOTER;
        } else {//不是最后
            return TYPE_ITEM;
        }
    }

    //接口
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setmNewsBeanList(List<NewsBean> mNewsBeanList) {
        this.mNewsBeanList = mNewsBeanList;
        this.notifyDataSetChanged();
    }

    public boolean isShowFooter() {
        return isShowFooter;
    }

    public void setShowFooter(boolean showFooter) {
        isShowFooter = showFooter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.discover_news_item, parent, false);
            return  new ItemViewHolder(v);
        } else {//加载下拉刷新页面
             View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.discover_footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            NewsBean news = mNewsBeanList.get(position);
            if(news == null) {
                return;
            }
            //((ItemViewHolder) holder).mNewsImg.setTag(news.getThumbnail_pic_s());
            ((ItemViewHolder) holder).mTitle.setText(news.getTitle());

            String s = news.getAuthor_name()+"  "+news.getDate();
            ((ItemViewHolder) holder).mDate.setText(s);

          //  if(news.getThumbnail_pic_s()!=null && ((ItemViewHolder) holder).mNewsImg.getTag() != null && ((ItemViewHolder) holder).mNewsImg.getTag().equals(news.getThumbnail_pic_s())){
                ImageLoaderUtils.display(mContext,((ItemViewHolder) holder).mNewsImg, news.getThumbnail_pic_s());
          //  }
        }else{
           // ((FooterViewHolder) holder).mTitle.setText("正在加载...");
        }

    }

    @Override
    public int getItemCount() {
        if(mNewsBeanList != null){
            return mNewsBeanList.size();
        }
        return 0;
    }

    public NewsBean getItem(int position) {
        if(mNewsBeanList != null){
            return mNewsBeanList.get(position);
        }
        return null;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{
        //public TextView mTitle;
        public FooterViewHolder(View itemView) {
            super(itemView);
          //  mTitle = (TextView) itemView.findViewById(R.id.id_discover_more_data_msg);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTitle;
        public TextView mDate;
        public ImageView mNewsImg;

        public ItemViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.id_discover_news_title);
            mDate = (TextView) itemView.findViewById(R.id.id_discover_news_date);
            mNewsImg = (ImageView) itemView.findViewById(R.id.id_discover_news_Image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener != null) {
                //接口，向外部传递view和position
                mOnItemClickListener.onItemClick(view,this.getPosition());
            }
        }
    }

    // 添加到链表头
    public void appendNewsBeanList(List<NewsBean> newsBeans) {
        LinkedList<NewsBean> linkedList =new LinkedList<>();
        int size = mNewsBeanList.size();
        for(int k = 0;k<size;k++){
            linkedList.add(mNewsBeanList.get(k));
        }
      //  Log.e("appendNewsBeanList", String.valueOf(size));
        // Log.e("appendNewsBeanList", String.valueOf(linkedList.size()));
        if(size != 0){
          mNewsBeanList.clear();
        }
      Log.e("appendNewsBeanList", String.valueOf(newsBeans.size()));
        boolean isHas = false;

        for (int i = 0; i < newsBeans.size(); i++) {
            NewsBean bean = newsBeans.get(i);
            for(int j= 0;j<size;j++){
               if(bean.getUrl().equals(linkedList.get(j).getUrl())) {
                   isHas = true;
                   break;
               }
            }
            if(!isHas){
                mNewsBeanList.add(0,bean);
                isHas = false;
            }

        }
        if(0 == mNewsBeanList.size()){
            for(int k = 0;k<size;k++){
               mNewsBeanList.add(linkedList.get(k));
            }
            Toast.makeText(mContext,"已加载到最新",Toast.LENGTH_SHORT).show();
        }
        setmNewsBeanList(mNewsBeanList);
    }
}
