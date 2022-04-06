using System;
using System.Collections.Generic;
using System.Data.SQLite; // Install-Package System.Data.Sqlite
using System.Text;

namespace Server.Data {
    /// <summary>
    /// A singleton class that connects, gets and modifies the data of a database
    /// </summary>
    class DbHandler {
        /// SINGLETON ///
        public static DbHandler instance { get { return _instance; } }
        private static DbHandler _instance = new DbHandler();
        private DbHandler() {}

        SQLiteConnection connection = null;
        
        /// Database connection ///
        private void ConnectToDB() {
            if (!isConnectionOpened()) {
                connection = new SQLiteConnection("Data Source=UsersDB.sqlite; Version=3;");

                try {
                    connection.Open();
                } catch (Exception e) {
                    Console.WriteLine("DB: " + e.Message);
                }
            }
        }

        private void DisconnectFromDB() {
            if (isConnectionOpened()) {
                connection.Close();
                connection = null;
            }
        }

        private bool isConnectionOpened() {
            return connection != null && connection.State == System.Data.ConnectionState.Open;
        }


        /// Database Operations ///
        public User getUser(String mail, int password) {
            ConnectToDB();
            User user = null;

            if (isConnectionOpened()) {
                String commandText = "SELECT * FROM users_table WHERE mail=@mail and pwd=@pwd;";
                SQLiteCommand command = new SQLiteCommand(commandText, connection);

                command.Parameters.Add("@mail", System.Data.DbType.String);
                command.Parameters["@mail"].Value = mail;

                command.Parameters.Add("@pwd", System.Data.DbType.Int32);
                command.Parameters["@pwd"].Value = password;

                SQLiteDataReader reader = command.ExecuteReader();
                if (reader.Read()) {
                    user = new User(
                        reader.GetString(0),    // mail
                        reader.GetInt32(1),     // password
                        reader.GetString(2),    // full name
                        reader.GetInt32(3),     // age
                        reader.GetInt32(4)      // gender
                    );
                }
            }
            DisconnectFromDB();
            return user;
        }

        public void insertUser(User user) {
            ConnectToDB();
            if (isConnectionOpened()) {
                String commandText = "INSERT INTO users_table VALUES(@mail, @pwd, @fname, @age, @gender);";
                SQLiteCommand command = new SQLiteCommand(commandText, connection);

                command.Parameters.Add("@mail", System.Data.DbType.String);
                command.Parameters["@mail"].Value = user.Mail;

                command.Parameters.Add("@pwd", System.Data.DbType.Int32);
                command.Parameters["@pwd"].Value = user.Password;

                command.Parameters.Add("@fname", System.Data.DbType.String);
                command.Parameters["@fname"].Value = user.FullName;

                command.Parameters.Add("@age", System.Data.DbType.Int32);
                command.Parameters["@age"].Value = user.Age;

                command.Parameters.Add("@gender", System.Data.DbType.Int32);
                command.Parameters["@gender"].Value = user.Gender;

                command.ExecuteNonQuery();
            }
            DisconnectFromDB();
        }

        public int insertMultipleUsers(List<User> users) {
            int count = 0;
            foreach (User user in users) {
                try {
                    insertUser(user);
                    count++;

                } catch (Exception e) {
                    Console.WriteLine(e.Message);
                }
            }

            return count;
        }

        public List<User> GetUsers(int ageMin, int ageMax, bool sMale, bool sFemale, bool sOther) {
            ConnectToDB();
            List<User> users = null;

            if (isConnectionOpened()) {
                users = new List<User>();
                StringBuilder commandText = new StringBuilder();
                commandText.Append("SELECT * FROM users_table WHERE age >= @ageMin AND age <= @ageMax AND ( gender = 0");

                if (sMale) { commandText.Append(" OR gender = 1"); }
                if (sFemale) { commandText.Append(" OR gender = 2"); }
                if (sOther) { commandText.Append(" OR gender = 3"); }

                commandText.Append(" );");

                SQLiteCommand command = new SQLiteCommand(commandText.ToString(), connection);

                command.Parameters.Add("@ageMin", System.Data.DbType.Int32);
                command.Parameters["@ageMin"].Value = ageMin;

                command.Parameters.Add("@ageMax", System.Data.DbType.Int32);
                command.Parameters["@ageMax"].Value = ageMax;

                SQLiteDataReader reader = command.ExecuteReader();
                while (reader.Read()) {
                    users.Add(new User(
                        reader.GetString(0),    // mail
                        0,                      // pwd
                        reader.GetString(2),    // full name
                        reader.GetInt32(3),     // age
                        reader.GetInt32(4)      // gender
                    ));
                }
            }
            DisconnectFromDB();
            return users;
        }


        /// TESTS ///
        public void TEST_Add() {
            ConnectToDB();
            if (isConnectionOpened()) {
                SQLiteCommand command = connection.CreateCommand();
                command.CommandText = "INSERT INTO test_table(text) VALUES('testadd');";
                command.ExecuteNonQuery();
                Console.WriteLine("TEST_Add: OK");
            }
            DisconnectFromDB();
        }

        public void TEST_Get() {
            ConnectToDB();
            if (isConnectionOpened()) {
                SQLiteCommand command = connection.CreateCommand();
                command.CommandText = "SELECT * FROM test_table;";

                SQLiteDataReader reader = command.ExecuteReader();

                Console.WriteLine("TEST_Get: ");
                while (reader.Read()) {
                    Console.WriteLine(reader.GetInt32(0) + " | " + reader.GetString(1));
                }
                Console.WriteLine("OK");
            }
            DisconnectFromDB();
        }
    }
}
