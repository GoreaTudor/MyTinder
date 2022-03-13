using System;
using System.Collections.Generic;

using System.Text;
using System.Threading;
using System.Net.Sockets;

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
                byte[] rawMessage = new byte[20]; // the receive buffer

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
            String[] arrayMessage = message.Split("-");

            if (arrayMessage[0].StartsWith(Messages.sLoginReq)) {
                Console.WriteLine(arrayMessage[1]); // probabil username sau ce vreau eu idk

                if (arrayMessage[1].StartsWith("Tudor")) {
                    Console.WriteLine("Heyy, it's Tudor!"); // login_req-Tudor
                }
            }

        }

        private void sendResponseLogin(String message) {
            // creez un raspuns
            StringBuilder response = new StringBuilder();
            response.Append("login");
            response.Append("ok");

            // il convertesc in byte[]
            byte[] responeMessage = Encoding.ASCII.GetBytes(response.ToString());

            // il trimit prin socket
            _socket.Send(responeMessage);
        }
    }
}
