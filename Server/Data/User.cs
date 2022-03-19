using System;
using System.Collections.Generic;
using System.Text;

namespace Server.Data {
    enum Gender {
        nope, male, female, other
    }

    class User {
        public String Mail { get; set; }
        public int Password { get; set; }
        public String FullName { get; set; }
        public int Age { get; set; }
        public Gender Gender { get; set; }

        public User() { }

        public User(String mail, int pwd, String fname, int age, Gender g) {
            this.Mail = mail;
            this.Password = pwd;
            this.FullName = fname;
            this.Age = age;
            this.Gender = g;
        }

        public String GetUserData() {
            return Mail + "," + Password + "," + FullName + "," + Age + "," + Gender; 
        }
    }
}
