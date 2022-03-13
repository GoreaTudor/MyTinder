using System;
using System.Collections.Generic;
using System.Text;

namespace Server.Data {
    class User {
        public static List<User> users = new List<User>();

        private String _username;
        private int _password;
        private String _data;

        public String username { get { return _username; } }
        public int password { get { return _password; } }
        public String data { get { return _data; } }

        public User(String uname, String password, String data) {
            this._username = uname;
            this._password = password.GetHashCode();
            this._data = data;
        }

        public override String ToString() {
            return "User{username='" + _username + "', password=" + _password + ", data='" + _data + "'}";
        }
    }
}
