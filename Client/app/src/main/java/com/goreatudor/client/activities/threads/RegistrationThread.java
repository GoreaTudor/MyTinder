package com.goreatudor.client.activities.threads;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.goreatudor.client.activities.data.User;
import com.goreatudor.client.activities.helper.Action;
import com.goreatudor.client.activities.helper.Helper;
import com.goreatudor.client.activities.helper.Messages;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class RegistrationThread extends Thread {

    private Context context;
    private Action callback;

    private String ip;
    private int port;

    private User user;

    public RegistrationThread(User user, Context context, Action callback) {
        this.user = user;
        this.context = context;
        this.callback = callback;

        this.ip = Helper.getIP(context);
        this.port = Helper.getPort(context);
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);

            String message = Messages.sRegReq + "#" + user.toString();
            Helper.sendMessage(socket, message);

            Thread.sleep(1000);

            String response = Helper.getMessage(socket);
            Log.d("RESPONSE", response);

            String[] args = response.split("#");
            if (args[0].equals(Messages.sRegOk)) {
                Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show();

            }if (args[0].equals(Messages.sRegErr)) {
                Toast.makeText(context, "Account already exists", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, "Account creation error", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
