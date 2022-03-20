package com.goreatudor.client.activities.threads;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.goreatudor.client.activities.data.CurrentUser;
import com.goreatudor.client.activities.data.Gender;
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

    private String mail;
    private int pwd;

    public LogInThread (String mail, int pwd, Context context, Action callback) {
        this.mail = mail;
        this.pwd = pwd;
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

            String message = Messages.sLoginReq + "#" + mail + "#" + pwd;
            //String message = Messages.sLoginReq + "#" + "dummy1@gmail.com" + "#" + "1111";
            Helper.sendMessage(socket, message);

            Thread.sleep(1000);

            String response = Helper.getMessage(socket);
            Log.d("RESPONSE", response);

            String[] args = response.split("#");
            if (args[0].equals(Messages.sLoginOk) && args.length >= 2) {
                String[] userData = args[1].split(",");

                CurrentUser.getInstance().isNow(
                        userData[0],                    // mail
                        Integer.parseInt(userData[1]),  // pwd
                        userData[2],                    // full name
                        Integer.parseInt(userData[3]),  // age
                        userData[4]                     // gender
                );

                /// CALLBACK ///
                callback.exec();

            } else if (args[1].equals(Messages.sLoginErr)) {
                Toast.makeText(context, "Account not found", Toast.LENGTH_SHORT).show();

            } else{
                Toast.makeText(context, "Login error", Toast.LENGTH_SHORT).show();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}