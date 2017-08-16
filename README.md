可关注的主题：头条，社会，国内，国际，娱乐，体育，军事，科技，财经，时尚

top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),
keji(科技),caijing(财经),shishang(时尚)

获取的头条的方法：NewsBean newsBean =getNews("top");
其他类推

fragment中获取用户已经关注的主题的代码：
private List<String> attentionList = new ArrayList<>();
 private DatabaseHelper databaseHelper;
 databaseHelper = new DatabaseHelper(view.getContext());
attentionList = databaseHelper.getAttentionList();

