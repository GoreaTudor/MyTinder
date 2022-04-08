package com.goreatudor.client.activities.threads;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.goreatudor.client.activities.data.User;
import com.goreatudor.client.activities.helper.Action;
import com.goreatudor.client.activities.helper.Helper;
import com.goreatudor.client.activities.helper.Messages;

import java.io.IOException;
import java.net.Socket;

public class RegistrationThread extends Thread {

    private Context context;
    private Handler handler;

    private String ip;
    private int port;

    private User user;

    public RegistrationThread(User user, Handler handler, Context context) {
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

            String message = Messages.sRegReq + "#" + user.getUserData();
            Helper.sendMessage(socket, message);

            Thread.sleep(1000);

            String response = Helper.getMessage(socket);

            String[] args = response.split("#");
            if (args[0].equals(Messages.sRegOk)) {
                handler.post(() -> {
                    Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show();
                });

            }if (args[0].equals(Messages.sRegErr)) {
                handler.post(() -> {
                    Toast.makeText(context, "Account already exists", Toast.LENGTH_SHORT).show();
                });

            } else {
                handler.post(() -> {
                    Toast.makeText(context, "Account creation error", Toast.LENGTH_SHORT).show();
                });
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
