package com.goreatudor.client.activities.data;

public class User {

    protected String mail;
    protected int password;
    protected String fullName;
    protected int age;
    protected Gender gender;

    public User(String mail, int pwd, String fullName, int age, String gender) {
        this.mail = mail;
        this.password = pwd;
        this.fullName = fullName;
        this.age = age;

        if (gender.equals("male")) {
            this.gender = Gender.male;
        } else if (gender.equals("female")) {
            this.gender = Gender.female;
        } else if (gender.equals("other")) {
            this.gender = Gender.other;
        } else {
            this.gender = Gender.nope;
        }
    }

    public String getUserData() {
        return mail + "," + password + "," + fullName + "," + age + "," + gender;
    }


    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
