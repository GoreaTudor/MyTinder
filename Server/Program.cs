using System;
using System.Threading;

namespace Server {
    class Program {
        private static bool _exitKeyPressed = false;
        private static int _port = 8000;

        static void Main(string[] args) {
            Console.Clear();

            // creeaza un server nou
            ServerCore server = new ServerCore();
            try {
                server.createServer(_port);
            } catch (Exception e) {
                Console.WriteLine(e.ToString());
            }

            // seteaza inchiderea serverului la apasarea Ctrl + C
            Console.CancelKeyPress += new ConsoleCancelEventHandler((object sender, ConsoleCancelEventArgs args) => {
                Console.WriteLine("Server was interrupted");
                args.Cancel = true;

                if (args.SpecialKey == ConsoleSpecialKey.ControlC) {
                    _exitKeyPressed = true;
                }
            });

            Console.WriteLine("Server is running...");
            Console.WriteLine("Press Ctrl+C to stop the server");

            // programul trebuie sa ramana activ cat timp inca exista servere
            while (!_exitKeyPressed) {
                Thread.Sleep(100);
            }

            Console.WriteLine("Stopping Server...");
            server.stopServer();

            Console.WriteLine("Cleaning up clients...");
            ClientDataStore.getInstance().stopClients();

            Console.WriteLine("Server is down");
            Thread.Sleep(1000);
        }
    }
}
