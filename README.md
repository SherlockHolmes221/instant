�ɹ�ע�����⣺ͷ������ᣬ���ڣ����ʣ����֣����������£��Ƽ����ƾ���ʱ��

top(ͷ����Ĭ��),shehui(���),guonei(����),guoji(����),yule(����),tiyu(����)junshi(����),
keji(�Ƽ�),caijing(�ƾ�),shishang(ʱ��)

��ȡ��ͷ���ķ����� 
��fragment��implements OnLoadNewsResultListener �ӿڣ�
public interface OnLoadNewsResultListener {
    void onSuccess(NewsResult newsResult);
    void onFailure(String msg, Exception e);
}

��fragment�д��룺
String type = "top";
NewsJsonUtils.getNews(type,this);
��onSuccess������ʵ�� 
 @Override
    public void onSuccess(NewsResult newsResult) {
      
        int size  =newsResult.getNewsBeen().size();
        for(int i = 0;i< size ;i++){
            this.mNewsBeanList.add(0,newsResult.getNewsBeen().get(i));
        }
    }

��������

fragment�л�ȡ�û��Ѿ���ע������Ĵ��룺
private List<String> attentionList = new ArrayList<>();
 private DatabaseHelper databaseHelper;
 databaseHelper = new DatabaseHelper(view.getContext());
attentionList = databaseHelper.getAttentionList();

