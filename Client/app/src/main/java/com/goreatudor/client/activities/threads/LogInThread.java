package com.goreatudor.client.activities.threads;

import android.content.Context;
import android.util.Log;

import com.goreatudor.client.activities.helper.Action;
import com.goreatudor.client.activities.helper.Helper;

public class LogInThread extends Thread {

    private Context context;
    private Action callback;

    public LogInThread (Context context, Action callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    public void run() {
        //TODO: get package
        String s = Helper.getIP(context);
        Log.d("MyThread", s);

        /// CALLBACK ///
        callback.exec();
    }
}
