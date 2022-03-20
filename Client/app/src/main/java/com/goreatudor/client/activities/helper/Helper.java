package com.goreatudor.client.activities.helper;

import android.content.Context;
import android.util.Log;

import com.goreatudor.client.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Helper {
    public static String getIP(Context context) {
        return context.getResources().getString(R.string.ip_home);
        //return context.getResources().getString(R.string.ip_univ_l11);
    }

    public static int getPort(Context context) {
        return context.getResources().getInteger(R.integer.port);
    }

    public static PrintWriter getWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }

    public static BufferedReader getReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static void sendMessage (Socket socket, String message) throws IOException {
        PrintWriter writer = getWriter(socket);
        String pack = "c!" + message + "#";
        writer.println(pack);
    }
}
