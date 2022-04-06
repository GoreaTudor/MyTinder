using System;
using System.Collections.Generic;
using System.Text;

using Server.Data;

namespace Server.MainServer {
    /// <summary>
    /// A singleton class that does operations based on string messages
    /// </summary>
    class PackageHandler {
        /// SINGLETON
        private static PackageHandler instance = new PackageHandler();
        private PackageHandler() { }
        public static PackageHandler Instance { get { return instance; } }


        /// <summary>Splits the package into arguments and does operation based on those arguments</summary>
        /// <param name="package">the package that will be split into argumets</param>
        /// <returns>a response message to the operation</returns>
        public String handlePackage(String package) {
            String[] arrayPackage = package.Split("#");

            if (arrayPackage[0].StartsWith(Messages.sTestAdd)) {                                        // c!test_add#
                return TEST_Add();

            } else if (arrayPackage[0].StartsWith(Messages.sTestGet)) {                                 // c!test_get#
                return TEST_Get();

            } else if (arrayPackage[0].StartsWith(Messages.sTablePop)) {                                // c!table_populate#
                return Table_Populate();

            } else if (arrayPackage[0].StartsWith(Messages.sLoginReq) && arrayPackage.Length >= 3) {    // c!login_req#mail#pwd#
                return LoginReq(arrayPackage[1], arrayPackage[2]);

            } else if (arrayPackage[0].StartsWith(Messages.sRegReq) && arrayPackage.Length >= 2) {      // c!reg_req#mail,pwd,fullname,age,gender#
                return RegReq(arrayPackage[1]);

            } else if (arrayPackage[0].StartsWith(Messages.sGetPReq) && arrayPackage.Length >= 6) {      // c!getP_req#ageMin#ageMax#sMale#sFemale#sOther#
                return GetPeople(arrayPackage[1], arrayPackage[2], arrayPackage[3], arrayPackage[4], arrayPackage[5]);
            }

            return "";
        }


        /// LOGIN ///
        /// <summary>Checks if the credidentials are valid and returns user data if valid</summary>
        /// <param name="mail">first credidential, primary key</param>
        /// <param name="password">second credidential, is always hashed</param>
        /// <returns>'login_ok' and user data if account exists, 'login_err' if the account doesn't exist</returns>
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
        /// <summary>Creates a new user if the account doesn't already exist</summary>
        /// <param name="package">contains all the user data, splitted by ','</param>
        /// <returns>'reg_ok' if account was created, 'reg_err' if the account already exists</returns>
        private String RegReq(String package) {
            String[] userData = package.Split(",");

            if (userData.Length < 5) {
                return Messages.sRegErr;
            }

            User user = new User(
                userData[0],                // mail
                int.Parse(userData[1]),     // password
                userData[2],                // full name
                int.Parse(userData[3]),     // age
                userData[4]                 // gender
            );

            try {
                DbHandler.instance.insertUser(user);
                return Messages.sRegOk;

            } catch (Exception e) {
                return Messages.sRegErr;
            }
        }


        /// SELECT PEOPLE ///
        /// <summary>Gets people from the database using some age and gender filters</summary>
        /// <param name="ageMin_s">ageMin is less than 18 or ageMin is greater than ageMax then the data is invalid</param>
        /// <param name="ageMax_s">ageMax is less than 18 or ageMax is less than ageMin then the data is invalid</param>
        /// <param name="sMale_s">sMale, sFemale, sOther are all false then the data is invalid</param>
        /// <param name="sFemale_s">sMale, sFemale, sOther are all false then the data is invalid</param>
        /// <param name="sOther_s">sMale, sFemale, sOther are all false then the data is invalid</param>
        /// <returns>
        /// 'getP_inv' if the data is invalid,
        /// 'getP_err' if there was an error,
        /// 'getP_ok' -- still on work
        /// </returns>
        private String GetPeople (String ageMin_s, String ageMax_s, String sMale_s, String sFemale_s, String sOther_s) {
            try {
                int ageMin = int.Parse(ageMin_s);
                int ageMax = int.Parse(ageMax_s);
                bool sMale = bool.Parse(sMale_s);
                bool sFemale = bool.Parse(sFemale_s);
                bool sOther = bool.Parse(sOther_s);

                if ((!sMale && !sFemale && !sOther) || (ageMax < ageMin) || (ageMin < 18) || (ageMax < 18)) {
                    throw new InvalidDataException();
                }

                List<User> users = DbHandler.instance.GetUsers(ageMin, ageMax, sMale, sFemale, sOther);

                StringBuilder message = new StringBuilder();
                foreach(User user in users) {
                    message.Append(user.GetSomeUserData());
                    message.Append("$");
                }

                return Messages.sGetPOk + "#" + message;

            } catch (InvalidDataException e) {
                return Messages.sGetPInv;

            } catch (Exception e) {
                return Messages.sGetPErr;
            }
        }


        /// POPULATE TABLE ///
        /// <summary>Populates the table with random data</summary>
        /// <returns>a string to be send as a response, containing the number of newly added rows</returns>
        private String Table_Populate () {
            int count = DbHandler.instance.insertMultipleUsers(AccountsGenerator.generate(50));
            return "Added " + count + " rows";
        }
        

        /// TESTS ///
        private String TEST_Add () {
            DbHandler.instance.TEST_Add();
            return "Response example ADD";
        }

        private String TEST_Get () {
            DbHandler.instance.TEST_Get();
            return "Response example GET";
        }
    }
}
