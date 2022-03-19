using System;
using System.Collections.Generic;
using System.Text;

namespace Server.MainServer {
    class Messages {
        public static String sTestAdd = "test_add"; // expect test_add#
        public static String sTestGet = "test_get"; // expect test_get#

        public static String sLoginReq = "login_req"; // expect login_req#username#password#

        public static String sRegNew = "reg_new"; // expect reg_new#username#password#data#
    }
}
