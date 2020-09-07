using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace TestStuff {

    class Program {
        static void Main(string[] args) {

            DateTime date = DateTime.Parse("03-09-2020");
            string s = "5 yea";

            string followup = s.Contains("day") ? "day" :
                s.Contains("week") ? "week" :
                s.Contains("month") ? "month" :
                s.Contains("year") ? "year" : null;

            Console.WriteLine(followup);





        }
    }
}
