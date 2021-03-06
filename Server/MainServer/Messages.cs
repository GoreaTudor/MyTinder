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
        public static readonly String sTestLikesAdd = "test_likes_add";
        public static readonly String sTestLikesGet = "test_likes_get";

        public static readonly String sTablePop = "table_populate";

        public static readonly String sLoginReq = "login_req";
        public static readonly String sLoginOk  = "login_ok";
        public static readonly String sLoginErr = "login_err";

        public static readonly String sRegReq = "reg_req";
        public static readonly String sRegOk  = "reg_ok";
        public static readonly String sRegErr = "reg_err";

        public static readonly String sGetPReq = "getP_req";
        public static readonly String sGetPOk  = "getP_ok";
        public static readonly String sGetPErr = "getP_err";
        public static readonly String sGetPInv = "getP_inv";

        public static readonly String sLikeSet   = "like_set";
        public static readonly String sLikeReset = "like_reset";
        public static readonly String sLikeOk    = "like_ok";
        public static readonly String sLikeErr   = "like_err";
    }

    class InvalidDataException : Exception { }
}
