using System;
using System.Collections.Generic;
using System.Text;

using Server.Data;
using Server.MainServer;

namespace Server.MainServer {
    class PackageHandler {
        /// SINGLETON
        private static PackageHandler instance = new PackageHandler();
        private PackageHandler() { }
        public static PackageHandler Instance { get { return instance; } }

        /// <summary>
        /// Splits the package and analizes the arguments
        /// </summary>
        /// <param name="package"></param>
        /// <returns>a response message</returns>
        public String handlePackage(String package) {
            String[] arrayPackage = package.Split("#");

            if (arrayPackage[0].StartsWith(Messages.sTestAdd)) {
                return TEST_Add();

            } else if (arrayPackage[0].StartsWith(Messages.sTestGet)) {
                return TEST_Get();

            } else if (arrayPackage[0].StartsWith(Messages.sLoginReq) && arrayPackage.Length >= 3) {
                return LoginReq(arrayPackage[1], arrayPackage[2]);

            }

            return "";
        }


        /// LOGIN ///
        private String LoginReq(String username, String password) {
            return null;
        }


        /// REGISTER ///

        

        /// TESTS ///
        private String TEST_Add () {
            DbHandler.instance.TEST_Add();
            return "Response example ADD";
        }

        private String TEST_Get() {
            DbHandler.instance.TEST_Get();
            return "Response example GET";
        }
    }
}
