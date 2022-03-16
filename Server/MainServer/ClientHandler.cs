using System;
using System.Linq;
using System.Collections.Generic;

using System.Text;
using System.Threading;
using System.Net.Sockets;

using Server.Data;

namespace Server_v1 {
    class ClientHandler {
        private Socket _socket = null;
        private int _idClient = -1;
        private Thread _thread = null;
        private bool _shouldRun = true;
        private bool _isRunning = true;

        public ClientHandler(Socket socket, int id) {
            this._socket = socket;
            this._idClient = id;
        }

        public void initClient() {
            if (_thread == null) {
                _thread = new Thread(new ThreadStart(run));
                _thread.Start();
            }
        }

        private void run() {
            while (_shouldRun) {
                byte[] rawMessage = new byte[100]; // the receive buffer

                try {
                    // preia mesajul
                    int nrOfBytes = _socket.Receive(rawMessage); // Receives data from a bound socket into a receive

                    // prelucreaza mesajul
                    String message = Encoding.UTF8.GetString(rawMessage);

                    if (nrOfBytes > 0) {
                        Console.WriteLine("Client " + _idClient + ": " + message);
                        handleMessage(message);
                    }

                } catch (Exception e) {
                    Console.WriteLine(e.ToString());
                    return;
                }

                Thread.Sleep(1);
            }

            _isRunning = false;
        }

        public void stopClient() {
            if (_thread != null) {
                _socket.Close();
                _shouldRun = false;
            }
        }

        public bool isAlive() {
            return this._isRunning;
        }

        public bool SocketConnected(Socket socket) {
            int microSeconds = 10000;

            return !(socket.Poll(microSeconds, SelectMode.SelectRead) && socket.Available == 0);
            // Poll - determines the status of the Socket
            // Available - returns the number of bytes of data received from the network and available to be read
        }


        private void handleMessage(String message) {
            String[] arrayMessage = message.Split("#");

            if (arrayMessage[0].StartsWith(Messages.sTestAdd)) {            // TEST ADD
                DbHandler.instance.TEST_Add();
                sendResponseExample("Response example ADD");


            } else if (arrayMessage[0].StartsWith(Messages.sTestGet)) {     // TEST GET
                DbHandler.instance.TEST_Get();
                sendResponseExample("Response example GET");


            } else if(arrayMessage[0].StartsWith(Messages.sRegNew)) {       // REGISTER
                if (arrayMessage.Length >= 4) {
                    String username = arrayMessage[1];
                    String password = arrayMessage[2];
                    String data = arrayMessage[3];

                    User user = new User(username, password, data);
                    User.users.Add(user);
                    Console.WriteLine("New user:" + user.ToString());
                    sendResponse("Account created");
                }

            } else if (arrayMessage[0].StartsWith(Messages.sLoginReq)) {    // LOGIN REQUEST
                if (arrayMessage.Length >= 3) {
                    String username = arrayMessage[1];
                    String password = arrayMessage[2];

                    User user = null;
                    try {
                        user = (from u in User.users
                                where u.username == username && u.password == password.GetHashCode()
                                select u
                                ).First();

                        Console.WriteLine("Data: " + user.data);
                        sendResponse("Data: " + user.data);

                    } catch (Exception e) {
                        String response = "No user found";
                        Console.WriteLine(response);
                        sendResponse(response);
                    }
                }
            }

        }

        private void sendResponse(String message) {
            _socket.Send(Encoding.ASCII.GetBytes(message));
        }

        private void sendResponseExample(String message) {
            // creez un raspuns
            StringBuilder response = new StringBuilder();
            response.Append(message);

            // il convertesc in byte[]
            byte[] responeMessage = Encoding.ASCII.GetBytes(response.ToString());

            // il trimit prin socket
            _socket.Send(responeMessage);
        }
    }
}
