using System;
using System.Collections.Generic;
using System.Text;

namespace Server_v1 {
    class ClientDataStore {
        /// SINGLETON ///
        private static ClientDataStore _instance = new ClientDataStore();
        private ClientDataStore() { }
        public static ClientDataStore getInstance() { return _instance; }

        private List<ClientHandler> _clients = new List<ClientHandler>();

        public void addClient(ClientHandler clientHandler) {
            lock (_clients) {
                _clients.Add(clientHandler);
            }
        }

        public void stopClients() {
            lock (_clients) {
                foreach (ClientHandler clientHandler in _clients) {
                    clientHandler.stopClient();
                }
            }
        }

        public int clientCount() {
            return _clients.Count;
        }
    }
}
