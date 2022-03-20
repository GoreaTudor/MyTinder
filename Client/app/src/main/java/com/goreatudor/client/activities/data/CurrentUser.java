package com.goreatudor.client.activities.data;

public class CurrentUser extends User {
    /// SINGLETON ///
    private static CurrentUser instance = new CurrentUser();
    private CurrentUser() {super("", 0, "", 0, "");}
    public static CurrentUser getInstance() { return instance; }

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
}
