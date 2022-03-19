using System;
using System.Collections.Generic;
using System.Data.SQLite; // Install-Package System.Data.Sqlite
using System.Text;

namespace Server.Data {
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
        public User getUser(String username, int password) {
            return null;
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
