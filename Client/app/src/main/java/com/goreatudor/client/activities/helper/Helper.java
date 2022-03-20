package com.goreatudor.client.activities.helper;

import android.content.Context;
import android.util.Log;

import com.goreatudor.client.R;

public class Helper {
    public static String getIP(Context context) {
        return context.getResources().getString(R.string.ip_home);
        //return context.getResources().getString(R.string.ip_univ);
    }
}
