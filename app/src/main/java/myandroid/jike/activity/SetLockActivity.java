package myandroid.jike.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import myandroid.jike.AppConfig;
import myandroid.jike.R;
import myandroid.jike.view.LockPatternView;

/**
 * Created by quxia on 2017/8/30.
 */
public class SetLockActivity extends AppCompatActivity{

    private Context context ;

    private TextView mTextView;
    private LockPatternView mLockPatternView;
    private Button mClearButton;
    private Button mEnterButton;

    private Boolean isFirst = true;  //判断是否为第一次设置密码
    private String mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lock);

        context = SetLockActivity.this;

        mTextView = (TextView) findViewById(R.id.id_activity_set_lock_text);

;        mEnterButton = (Button) findViewById(R.id.id_activity_set_lock_enter);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        //清空按钮
        mClearButton = (Button) findViewById(R.id.id_activity_set_lock_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFirst = true;
                mPassword = "";
                mClearButton.setVisibility(View.GONE);
            }
        });

        mLockPatternView = (LockPatternView) findViewById(R.id.id_activity_set_lock_lockView);
        mLockPatternView.setLockListener(new LockPatternView.OnLockListener() {
            @Override
            public void getStringPassword(String password) {
                if (isFirst) {
                    mPassword = password;
                    mTextView.setText("再次输入手势密码");
                    isFirst = false;
                    mClearButton.setVisibility(View.VISIBLE);
                } else {
                    if (password.equals(mPassword)) {

                        AppConfig appConfig = new AppConfig(context);
                        appConfig.setIsSetPassword(mPassword);

                        startActivity(new Intent(context, MainActivity.class));
                        SetLockActivity.this.finish();
                    }else {
                        Toast.makeText(SetLockActivity.this,"两次密码不一致，请重新设置",Toast.LENGTH_SHORT).show();
                        mPassword = "";
                        mTextView.setText("设置手势密码");
                        isFirst = false;
                        mClearButton.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public boolean isPassword() {
                return false;
            }
        });


    }
}
