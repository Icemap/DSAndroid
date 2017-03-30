package com.wqz.base;

import android.app.Application;

import com.wqz.pojo.UserPojo;

/**
 * Created by Wqz on 2016/12/23.
 */

public class BaseApplication extends Application
{
    UserPojo userInfo = new UserPojo();

    public void setUserInfo(UserPojo userInfo)
    {
        this.userInfo = userInfo;
    }

    public UserPojo getUserInfo()
    {
        return userInfo;
    }
}
