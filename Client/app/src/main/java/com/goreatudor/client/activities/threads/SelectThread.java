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
import java.util.ArrayList;

public class SelectThread extends Thread {
    private Context context;
    private Action callback;
    private Handler handler;
    private ArrayList <User> userList;

    private String ip;
    private int port;

    private int ageMin;
    private int ageMax;
    private boolean sMale;
    private boolean sFemale;
    private boolean sOther;

    public SelectThread (int ageMin, int ageMax, boolean sMale, boolean sFemale, boolean sOther,
                         ArrayList<User> usersList, Context context, Handler handler, Action callback) {
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.sMale = sMale;
        this.sFemale = sFemale;
        this.sOther = sOther;

        this.context = context;
        this.handler = handler;
        this.callback = callback;
        this.userList = usersList;

        this.ip = Helper.getIP(context);
        this.port = Helper.getPort(context);
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);

            String message = Messages.sGetPReq + "#" + ageMin + "#" + ageMax + "#" + sMale + "#" + sFemale + "#" + sOther;
            Helper.sendMessage(socket, message);

            Thread.sleep(1000);

            String response = Helper.getMessage(socket);

            String[] args = response.split("#");

            if (args[0].equals(Messages.sGetPOk) && args.length >= 2) {
                String[] people_s = args[1].split("\\$");

                for(String person_s : people_s) {
                    String[] userData = person_s.split(",");

                    userList.add(new User(
                            userData[0],                    // mail
                            0,                         // pwd
                            userData[1],                    // full name
                            Integer.parseInt(userData[2]),  // age
                            userData[3]                     // gender
                    ));
                }

                callback.exec();

            } else {
                handler.post(() -> {
                    Toast.makeText(context, "Selection error", Toast.LENGTH_SHORT).show();
                });
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
