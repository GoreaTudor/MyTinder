using System;
using System.Collections.Generic;
using System.Text;

using System.Net;
using System.Net.Sockets;

namespace Server.MainServer {
    class ServerSocket {
        private Socket _socket;

        protected void createSocket(int port) {
            if (_socket != null)
                throw new Exception("Socket already exists");

            _socket = new Socket(
                AddressFamily.InterNetwork, // address for ip v4
                SocketType.Stream, // stream of data, no structures
                ProtocolType.Tcp // transmision control protocol
            );

            try {
                // associates a socket with a local endpoint
                _socket.Bind(new IPEndPoint(IPAddress.Any, port));

                // places the socket in a listening state
                _socket.Listen(10);

            } catch (Exception e) {
                throw e;
            }
        }

        protected Socket acceptConnection() {
            try {
                return _socket.Accept();
            } catch (Exception e) {
                throw e;
            }
        }

        protected void closeSocket() {
            if (_socket != null) {
                _socket.Close();
                _socket = null;
            }
        }
    }
}
