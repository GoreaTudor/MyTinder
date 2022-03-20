package com.goreatudor.client.activities.threads;

import android.content.Context;
import android.util.Log;

import com.goreatudor.client.activities.helper.Action;
import com.goreatudor.client.activities.helper.Helper;
import com.goreatudor.client.activities.helper.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class LogInThread extends Thread {

    private Context context;
    private Action callback;

    private String ip;
    private int port;

    public LogInThread (Context context, Action callback) {
        this.context = context;
        this.callback = callback;
        this.ip = Helper.getIP(context);
        this.port = Helper.getPort(context);
        Log.d("MyThread", "Server:{ip=" + ip + ", port=" + port + "}");
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);

            PrintWriter writer = Helper.getWriter(socket);
            BufferedReader reader = Helper.getReader(socket);

            String message = Messages.sLoginReq + "#" + "dummy1@gmail.com" + "#" + "1111";
            Helper.sendMessage(socket, message);

        } catch (IOException e) {
            e.printStackTrace();
        }

        /// CALLBACK ///
        callback.exec();
    }
}