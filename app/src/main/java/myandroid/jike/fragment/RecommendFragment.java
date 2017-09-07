package myandroid.jike.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import myandroid.jike.interfaces.OnLoadNewsResultListener;
import myandroid.jike.news.NewsResult;

/**
 * Created by caiyiqi on 2017/8/15.
 */

public class RecommendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,OnLoadNewsResultListener{

//    private Context context = getContext();
//    private TextView textView;
//    NewsUtils newsUtils;
//    List<NewsBean> listNewsBean;
//    ListView listView;
//    private Adapter adapter;
//    private Handler handler = new Handler() {
//        @Override
//        public void publish(LogRecord logRecord) {
//
//        }
//
//        @Override
//        public void flush() {
//
//        }
//
//        @Override
//        public void close() throws SecurityException {
//
//        }
//
//        public void handleMessage(android.os.Message message) {
//            listNewsBean = (List<NewsBean>) message.obj;
//            NewsAdapter2 newsAdapter = new NewsAdapter2(context, listNewsBean);
//            listView.setAdapter(newsAdapter);
//        }
//    };
//
//    private SwipeRefreshLayout swipeRefreshLayout;
//
//    private DatabaseHelper databaseHelper;
//    private List<String> attentionList = new ArrayList<>();
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.recommend,null);
//
//        listView = (ListView)view.findViewById(R.id.recommend_list);
//        newsUtils = new NewsUtils();
//
//       // swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.recommend_srl);
//        swipeRefreshLayout.setOnRefreshListener(this);
//
//        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
//        attentionList = databaseHelper.getAttentionList();
//
//        //加载数据
//        onRefresh();
////        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(View view, int position) {
////                if (newsBeanList.size() <= 0) {
////                    return;
////                }
////                NewsBean news = adapter.getItem(position);
////                Intent intent =new Intent(getActivity(), NewsDetailActivity.class);
////                Bundle bundle = new Bundle();
////                bundle.putString("title", news.getTitle());
////                bundle.putString("url",news.getUrl());
////                bundle.putString("image1",news.getThumbnail_pic_s());
////                bundle.putString("image2",news.getThumbnail_pic_s02());
////                bundle.putString("image3",news.getThumbnail_pic_s03());
////            }
////        });
//
//        return view;
//    }
//
//
    @Override
    public void onSuccess(NewsResult newsResult) {

    }

    @Override
    public void onFailure(String msg, Exception e) {

    }

    @Override
    public void onRefresh() {

    }
}