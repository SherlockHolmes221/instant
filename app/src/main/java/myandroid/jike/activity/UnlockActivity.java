package myandroid.jike.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import myandroid.jike.AppConfig;
import myandroid.jike.R;
import myandroid.jike.view.LockPatternView;

/**
 * Created by quxia on 2017/8/30.
 */
public class UnlockActivity extends AppCompatActivity{

    private Context context;

    private LockPatternView mLockPatternView;
    private String mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);

        context = UnlockActivity.this;

        mLockPatternView = (LockPatternView) findViewById(R.id.id_activity_unlock_lockView);
        AppConfig appConfig = new AppConfig(context);
        mPassword = appConfig.isSetPassword();

        mLockPatternView.setLockListener(new LockPatternView.OnLockListener() {
            @Override
            public void getStringPassword(String password) {
                if(password.equals(mPassword)){
                    Toast.makeText(UnlockActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    UnlockActivity.this.finish();
                }
                else {
                    Toast.makeText(UnlockActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public boolean isPassword() {
                return false;
            }
        });


    }
}
