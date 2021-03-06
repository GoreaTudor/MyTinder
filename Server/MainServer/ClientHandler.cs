using System;
using System.Linq;
using System.Collections.Generic;

using System.Text;
using System.Threading;
using System.Net.Sockets;

namespace Server.MainServer {
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


        /// <summary>Splits the message by '!' and passes it's data to PackageHandler</summary>
        /// <param name="message">the whole package that is received from the socket</param>
        private void handleMessage(String message) {
            String[] arguments = message.Split("!");

            if (arguments[0] == "c" && arguments.Length >= 2) {
                String response = PackageHandler.Instance.handlePackage(arguments[1]);
                sendResponse(response);
            }

        }


        /// <summary>Sends a response to the client through the socket</summary>
        /// <param name="message">the response message</param>
        private void sendResponse(String message) {
            String response = "s!" + message + "#";
            Console.WriteLine(response + "\n");
            _socket.Send(Encoding.ASCII.GetBytes(response));
        }
    }
}
