package com.goreatudor.client.activities.data;

public class CurrentUser {
    /// SINGLETON ///
    private static CurrentUser instance = new CurrentUser();
    private CurrentUser() {}
    public static CurrentUser getInstance() { return instance; }

    /// DATA ///
    private String mail;
    private int password;
    private String fullName;
    private int age;
    private Gender gender;

    /// ACTIONS ///
    public void isNow(String mail, int pwd, String fullName, int age, String gender) {
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

    @Override
    public String toString() {
        return "CurrentUser{" +
                "mail='" + mail + '\'' +
                ", password=" + password +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public String getMail() {
        return mail;
    }

    public int getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
