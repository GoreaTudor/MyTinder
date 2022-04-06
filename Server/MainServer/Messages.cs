using System;
using System.Collections.Generic;
using System.Text;

namespace Server.MainServer {
    /// <summary>
    /// A class that contains all the Operation Messages
    /// </summary>
    class Messages {
        public static readonly String sTestAdd = "test_add";
        public static readonly String sTestGet = "test_get";

        public static readonly String sTablePop = "table_populate";

        public static readonly String sLoginReq = "login_req";
        public static readonly String sLoginOk = "login_ok";
        public static readonly String sLoginErr = "login_err";

        public static readonly String sRegReq = "reg_req";
        public static readonly String sRegOk = "reg_ok";
        public static readonly String sRegErr = "reg_err";

        public static readonly String sGetPReq = "getP_req";
        public static readonly String sGetPOk = "getP_ok";
        public static readonly String sGetPErr = "getP_err";
        public static readonly String sGetPInv = "getP_inv";
    }

    class InvalidDataException : Exception { }
}
