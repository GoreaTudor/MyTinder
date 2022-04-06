package com.goreatudor.client.activities.threads;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.goreatudor.client.activities.helper.Action;
import com.goreatudor.client.activities.helper.Helper;
import com.goreatudor.client.activities.helper.Messages;

import java.io.IOException;
import java.net.Socket;

public class SelectThread extends Thread {
    private Context context;
    private Action callback;
    private Handler handler;

    private String ip;
    private int port;

    private int ageMin;
    private int ageMax;
    private boolean sMale;
    private boolean sFemale;
    private boolean sOther;

    public SelectThread (int ageMin, int ageMax, boolean sMale, boolean sFemale, boolean sOther,
                         Context context, Handler handler, Action callback) {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.sMale = sMale;
        this.sFemale = sFemale;
        this.sOther = sOther;

        this.context = context;
        this.handler = handler;
        this.callback = callback;

        this.ip = Helper.getIP(context);
        this.port = Helper.getPort(context);
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);

            String message = Messages.sGetPeople + "#" + ageMin + "#" + ageMax + "#" + sMale + "#" + sFemale + "#" + sOther;
            Helper.sendMessage(socket, message);

            //Thread.sleep(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
