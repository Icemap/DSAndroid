package com.wqz.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by WangQiZhi on 2017/3/30.
 */

public class JpushReceiver extends BroadcastReceiver
{
    private final String PUSH_MSG_CLICK = "cn.jpush.android.intent.NOTIFICATION_OPENED";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(PUSH_MSG_CLICK))
        {

        }
    }
}
