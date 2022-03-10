package com.goreatudor.mytinder_1.data;

import android.util.Log;

public class UserDataChecker {
    public static boolean checkFullname (String fname) {
        if (fname == null || fname.isEmpty()) {
            Log.i("FullName", "null or empty");
            return false;
        }

        return true;
    }


    public static boolean checkMail (String mail) {
        if (mail == null || mail.isEmpty()) {
            Log.i("Mail", "null or empty");
            return false;
        }

        if (!mail.matches("[a-zA-Z]\\w*@gmail.com")) {
            Log.i("Mail", "invalid format");
            return false;
        }

        return true;
    }


    public static boolean checkAge (String age_s) {
        if (age_s == null || age_s.isEmpty()) {
            Log.i("Age", "null or empty");
            return false;
        }

        int age;
        try {
            age = Integer.parseInt(age_s);

            if (age < 18) {
                Log.i("Age", "too young");
                return false;
            }

            if (age > 99) {
                Log.i("Age", "too old");
                return false;
            }

            return true;

        } catch (Exception e) {
            Log.i("Age", "invalid format");
            return false;
        }
    }


    public static boolean checkUsername (String uname) {
        if (uname == null || uname.isEmpty()) {
            Log.i("Username", "null or empty");
            return false;
        }

        return true;
    }


    public static boolean checkPassword (String pwd_s) {
        if (pwd_s == null || pwd_s.isEmpty()) {
            Log.i("Password", "null or empty");
            return false;
        }

        return true;
    }
}
