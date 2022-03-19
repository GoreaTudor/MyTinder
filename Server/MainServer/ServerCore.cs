using System;
using System.Collections.Generic;
using System.Text;

using System.Threading;
using System.Net.Sockets;

namespace Server.MainServer {
    class ServerCore : ServerSocket {
        private Thread _thread = null;
        private bool _shouldRun = true;

        public void createServer(int port) {
            createSocket(port);

            _thread = new Thread(new ThreadStart(run));
            _thread.Start();
        }

        public void stopServer() {
            closeSocket();
        }

        private void run() {
            while (_shouldRun) {
                try {
                    Socket socket = acceptConnection();

                    if (socket == null)
                        return;

                    Console.WriteLine("Accepted connection from: " + socket.RemoteEndPoint);

                    // creeaza un client nou, ii se da un socket si un idClient
                    ClientHandler clientHandler = new ClientHandler(socket, ClientDataStore.getInstance().clientCount());
                    clientHandler.initClient();

                    // adauga la lista de clienti
                    ClientDataStore.getInstance().addClient(clientHandler);

                } catch (Exception e) {
                    return;
                }

                Thread.Yield();
            }
        }

    }
}
