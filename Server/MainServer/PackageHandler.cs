using System;
using System.Collections.Generic;
using System.Text;

using Server.Data;

namespace Server.MainServer {
    class PackageHandler {
        /// SINGLETON
        private static PackageHandler instance = new PackageHandler();
        private PackageHandler() { }
        public static PackageHandler Instance { get { return instance; } }


        /// <summary>
        /// Splits the package into arguments and does operation based on those arguments
        /// </summary>
        /// <param name="package">the package that will be split into argumets</param>
        /// <returns>a response message</returns>
        public String handlePackage(String package) {
            String[] arrayPackage = package.Split("#");

            if (arrayPackage[0].StartsWith(Messages.sTestAdd)) {                                        // c!test_add#
                return TEST_Add();

            } else if (arrayPackage[0].StartsWith(Messages.sTestGet)) {                                 // c!test_get#
                return TEST_Get();

            } else if (arrayPackage[0].StartsWith(Messages.sLoginReq) && arrayPackage.Length >= 3) {    // c!login_req#mail#pwd#
                return LoginReq(arrayPackage[1], arrayPackage[2]);

            }

            return "";
        }


        /// LOGIN ///
        private String LoginReq(String mail, String password) {
            try {
                User user = DbHandler.instance.getUser(mail, int.Parse(password));

                if (user == null) {
                    return Messages.sLoginErr;
                }

                return Messages.sLoginOk + "#" + user.GetUserData();

            } catch (Exception e) {
                return Messages.sLoginErr;
            }
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
