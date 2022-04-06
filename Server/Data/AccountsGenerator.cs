using System;
using System.Collections.Generic;
using System.Text;

namespace Server.Data {
    /// <summary>
    /// A helper class that generates accounts to be added to the database. 
    /// The class does not add those accounts to the database by itself.
    /// The generated data is random.
    /// </summary>
    class AccountsGenerator {

        /// <summary>Generates a list of random users with random data</summary>
        /// <param name="nrOfUsers">the number of users to be created</param>
        /// <returns>a list of users</returns>
        public static List<User> generate(int nrOfUsers) {
            Random rnd = new Random();
            List<User> users = new List<User>();

            int gender_rnd, gender, age, temp;
            String fn, ln, mail, pwd;
            String[] fn_array;

            for (int i = 1; i <= nrOfUsers; i++) {

                gender_rnd = rnd.Next(1, 6);
                if (gender_rnd == 1 || gender_rnd == 2) {          // male 
                    fn_array = fn_male;
                    gender = 1;
                } else if (gender_rnd == 3 || gender_rnd == 4) {   // female
                    fn_array = fn_female;
                    gender = 2;
                } else {                                    // other
                    fn_array = fn_other;
                    gender = 3;
                }

                temp = rnd.Next(0, 5);  // hide gender (20%)
                if (temp == 0) {
                    gender = 0;
                }


                fn = fn_array[rnd.Next(0, fn_array.Length)];
                ln = ln_array[rnd.Next(0, ln_array.Length)];
                age = rnd.Next(18, 50);

                mail = fn + ln + "@gmail.com";
                pwd = ln + fn + age;

                User user = new User(mail, pwd.GetHashCode(), fn + " " + ln, age, gender);
                users.Add(user);
            }

            return users;
        }

        private static String[] fn_male = {"Tudor", "Ionut", "Andrei", "Dragos", "Ion", "Catalin", "Nelutu", "Nelu", "Alexandru", "Petre", "Alin",
            "David", "Liviu", "Mihai", "Marius", "Raul", "Paul", "Cristi", "Adrian"};

        private static String[] fn_female = {"Adina", "Alexia", "Andrada", "Madalina", "Iulia", "Alexandra", "Ioana", "Mihaela", "Alicia", "Teodora",
            "Mirela", "Ana", "Maria", "Cristina", "Alina", "Daria"};

        private static String[] fn_other = { "Alex", "Piper", "Robin", "Roma", "Vasilica", "Gabi", "Luca" };

        private static String[] ln_array = {"Gorea", "Noje", "Iagar", "Andrei", "Dragomir", "Ichim", "Suciu", "Bosa", "Harhoi", "Oltean", "Moldovan",
            "Barabas", "Campeanu", "Francu"};
    }
}
