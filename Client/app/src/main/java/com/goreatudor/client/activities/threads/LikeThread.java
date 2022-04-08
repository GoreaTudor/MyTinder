package com.goreatudor.client.activities.threads;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.goreatudor.client.activities.data.CurrentUser;
import com.goreatudor.client.activities.data.User;
import com.goreatudor.client.activities.helper.Helper;
import com.goreatudor.client.activities.helper.Messages;

import java.io.IOException;
import java.net.Socket;


public class LikeThread extends Thread {

    private Context context;
    private Handler handler;

    private String mail;
    private User user;

    private String ip;
    private int port;

    public LikeThread (String mail, User user, Context context, Handler handler) {
        this.mail = mail;
        this.user = user;

        this.context = context;
        this.handler = handler;

        this.ip = Helper.getIP(context);
        this.port = Helper.getPort(context);
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);

            String message = Messages.sLikeSet + "#" + mail + "#" + user.getMail();

            Helper.sendMessage(socket, message);

            handler.post(() -> {
                Toast.makeText(context, "Liked " + user.getFullName(), Toast.LENGTH_SHORT).show();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
