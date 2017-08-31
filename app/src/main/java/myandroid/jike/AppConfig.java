package myandroid.jike;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 保存应用信息
 */
public class AppConfig {


    private SharedPreferences innerConfig;

    public static final String KEY_PASSWORD="password";
    public static final String KEY_FIRST="WelcomeActivity";
    public static final String KEY_NIGHT_MODE_SWITCH="night_mode_switch";


    public AppConfig(final Context context) {
        innerConfig = context.getSharedPreferences("app_config", Application.MODE_PRIVATE);
    }

    //是否第一次进入app
    public boolean isFirst(){
        return innerConfig.getBoolean(KEY_FIRST, true);
    }

    public void setIsFirst(boolean isFirst) {
        Editor editor = innerConfig.edit();
        editor.putBoolean(KEY_FIRST, isFirst);
        editor.commit();
    }

    //是否设置了手势密码
    public String isSetPassword(){
        return innerConfig.getString(KEY_PASSWORD, " ");
    }

    public void setIsSetPassword(String password) {
        Editor editor = innerConfig.edit();
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }


    //夜间模式
    public boolean getNightModeSwitch() {
        return innerConfig.getBoolean(KEY_NIGHT_MODE_SWITCH, false);
    }
    //夜间模式切换
    public void setNightModeSwitch(boolean on) {
        Editor editor = innerConfig.edit();
        editor.putBoolean(KEY_NIGHT_MODE_SWITCH, on);
        editor.commit();
    }

    /**
     * 清空
     */
    public void clear() {
        Editor editor = innerConfig.edit();
        editor.clear();
        editor.commit();
    }
}
