package com.goreatudor.mytinder_1.data;

public class UserModel_ {
    public enum Gender { nope, male, female, other }

    String username;
    String fullName;
    String mail;
    int age;
    int password;
    Gender gender;

    public UserModel_ (String uname, String fname, String mail, int age, int pwd, Gender g) {
        this.username = uname;
        this.fullName = fname;
        this.mail = mail;
        this.age = age;
        this.password = pwd;
        this.gender = g;
    }

}
