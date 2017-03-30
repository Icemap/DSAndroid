package com.wqz.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Wqz on 2016/12/23.
 */

abstract public class BaseActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        onInitUI();
        onSetListener();
    }

    protected abstract void onInitUI();
    protected abstract void onSetListener();
    protected abstract void onSetDataLogic(Object data);

    protected BaseApplication getBaseApplication()
    {
        return ((BaseApplication) getApplication());
    }
}
