package myandroid.jike.news;

/**
 * Created by quxia on 2017/8/14.
 */

//类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
public class NewsType {

    private String type;

    public String getType(int id){
        switch (id){
            case 1:
                return "top";
            case 2:
                return "shehui";
            case 3:
                return "guonei";
            case 4:
                return "guoji";
            case 5:
                return "yule";
            case 6:
                return "tiyu";
            case 7:
                return "junshi";
            case 8:
                return "keji";
            case 9:
                return "caijing";
            case 10:
                return "shishang";
            default:
                break;
        }
        return null;
    }
}
