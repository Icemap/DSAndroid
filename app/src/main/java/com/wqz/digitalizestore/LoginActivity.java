package com.wqz.digitalizestore;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wqz.base.BaseImmersiveActivity;
import com.wqz.pojo.UserPojo;
import com.wqz.utils.UrlUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

public class LoginActivity extends BaseImmersiveActivity
{
    Button btnLogin;
    EditText etUsername,etPassword;

    @Override
    protected void onInitUI()
    {
        setContentView(R.layout.activity_login);
        etUsername = (EditText)findViewById(R.id.et_login_username);
        etPassword = (EditText)findViewById(R.id.et_login_password);
        btnLogin = (Button)findViewById(R.id.btn_login);
    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btn_login:
                    //TODO login
                    String url = UrlUtils.USER_LOGIN.replace("#{username}",etUsername.getText().toString());
                    url = url.replace("#{password}",etPassword.getText().toString());
                    OkHttpUtils.get().url(url)
                            .build()
                            .execute(new MyStringCallback());
                    break;
            }
        }
    };

    @Override
    protected void onSetListener()
    {
        btnLogin.setOnClickListener(listener);
    }

    @Override
    protected void onSetDataLogic(Object data)
    {
        String respons = (String)data;
        respons = respons.replace("(","").replace(")","");
        if(respons == null || respons.isEmpty())
        {
            Toast.makeText(LoginActivity.this,"该用户不存在",Toast.LENGTH_SHORT).show();
            return;
        }

        UserPojo userPojo = new Gson().fromJson(respons,UserPojo.class);
        LoginActivity.this.getBaseApplication().setUserInfo(userPojo);
        setTag(userPojo.getResult().getStoreId() + "");
        //Jump
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        LoginActivity.this.finish();
    }

    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onError(Call call, Exception e, int id)
        {
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id)
        {
            LoginActivity.this.onSetDataLogic(response);
        }
    }

    // 这是来自 JPush Example 的设置别名的 Activity 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
    private void setTag(String tag) {
        if (TextUtils.isEmpty(tag)) {
            Toast.makeText(LoginActivity.this,R.string.error_alias_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        // 调用 Handler 来异步设置别名
        Set<String> s = new HashSet<String>();
        s.add(tag);
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, s));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs ;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }

            Toast.makeText(LoginActivity.this, logs, Toast.LENGTH_SHORT).show();
        }
    };

    private static final int MSG_SET_TAGS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_TAGS:
                    Log.d(TAG, "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            null,
                            (Set<String>)msg.obj,
                            mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
}
