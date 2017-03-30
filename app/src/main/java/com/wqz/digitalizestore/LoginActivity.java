package com.wqz.digitalizestore;

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

import okhttp3.Call;

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
        }

        UserPojo userPojo = new Gson().fromJson(respons,UserPojo.class);
        LoginActivity.this.getBaseApplication().setUserInfo(userPojo);
        //Jump
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
}
