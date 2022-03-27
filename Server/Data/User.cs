using System;
using System.Collections.Generic;
using System.Text;

namespace Server.Data {
    enum Gender {
        nope, male, female, other
    }

    /// <summary>
    /// A model class that represents the User model in the database
    /// </summary>
    class User {
        public String Mail { get; set; }
        public int Password { get; set; }
        public String FullName { get; set; }
        public int Age { get; set; }
        public Gender Gender { get; set; }

        public User() { }

        public User(String mail, int pwd, String fname, int age, String g) {
            this.Mail = mail;
            this.Password = pwd;
            this.FullName = fname;
            this.Age = age;
            
            if (g == "male") {
                this.Gender = Gender.male;
            } else if (g == "female") {
                this.Gender = Gender.female;
            } else if (g == "other") {
                this.Gender = Gender.other;
            } else {
                this.Gender = Gender.nope;
            }
        }

        public User(String mail, int pwd, String fname, int age, int g) {
            this.Mail = mail;
            this.Password = pwd;
            this.FullName = fname;
            this.Age = age;

            if (g == 1) {
                this.Gender = Gender.male;
            } else if (g == 2) {
                this.Gender = Gender.female;
            } else if (g == 3) {
                this.Gender = Gender.other;
            } else {
                this.Gender = Gender.nope;
            }
        }

        /// <summary>Method that returns all user data</summary>
        /// <returns>a string with format: 'mail,pwd,fullName,age,gender'</returns>
        public String GetUserData() {
            return Mail + "," + Password + "," + FullName + "," + Age + "," + Gender; 
        }
    }
}
