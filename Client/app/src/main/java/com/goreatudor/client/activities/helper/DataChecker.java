package com.goreatudor.client.activities.helper;

public class DataChecker {
    public static boolean nullCheck(String s) {
        return (s != null && !s.isEmpty());
    }

    public static boolean checkMail(String mail) {
        return (nullCheck(mail) && mail.matches("[a-zA-Z][a-zA-Z0-9_]*@gmail\\.com"));
    }

    public static boolean checkPwd(String pwd) {
        return nullCheck(pwd) && pwd.length() >= 4 && pwd.length() <= 20;
    }

    public static boolean checkPasswords(String pwd, String cpwd) {
        return pwd.equals(cpwd);
    }

    public static boolean checkFullName(String fname) {
        return nullCheck(fname) && fname.length() >= 5 && fname.length() <= 30;
    }

    public static boolean checkAge(String age) {
        if (!nullCheck(age))
            return false;

        try {
            int age_i = Integer.parseInt(age);

            if (age_i >= 18 && age_i <= 100) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
