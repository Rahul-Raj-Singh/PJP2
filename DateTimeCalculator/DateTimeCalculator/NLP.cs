using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace DateTimeCalculator {

    public class NLP {

        private Dictionary<string, string> nlp;

        public NLP() {
            Setup();
        }

        public void Setup() {

            nlp = new Dictionary<string, string>()
            {
                {"today",  GetInstance().Add("0 day")},
                {"tomorrow",  GetInstance().Add("1 day")},
                {"day-after-tomorrow",  GetInstance().Add("2 day")},
                {"yesterday",  GetInstance().Subtract("1 day")},
                {"day-before-yesterday",  GetInstance().Subtract("2 day")},
                {"previous-week",  GetInstance().Subtract("1 week")},
                {"next-week",  GetInstance().Add("1 week")},
                {"next-month",  GetInstance().Add("1 month")},
                {"next-year",  GetInstance().Add("1 year")},
                {"last-month",  GetInstance().Subtract("1 month")},
                {"last-year",  GetInstance().Subtract("1 year")},

            };

            // Some synonymous names already calculated
            nlp["last-week"] = nlp["previous-week"];
            nlp["month-after"] = nlp["next-month"];
            nlp["month-before"] = nlp["last-month"];

            
        }

        public String Translate(string s) {

            s = s.ToLower();
            // Retrieved from already calculated Dictionary
            if (nlp.ContainsKey(s))
                return nlp[s];

            // Complex NLPs e.g 3 months from, 2 years earlier etc
            string number = s.Substring(0, s.IndexOf(" "));
            string followup = s.Contains("day") ? "day" :
                s.Contains("week") ? "week" :
                s.Contains("month") ? "month" :
                s.Contains("year") ? "year" : null;

            if (s.Contains("from") && followup != null)
                return GetInstance().Add(number + " " + followup);

            else if (s.Contains("earlier") && followup != null)
                return GetInstance().Subtract(number + " " + followup);

            return "Cannot understand language !!!";
 
        }

        public Calculator GetInstance() {

            return new Calculator(DateTime.Now.ToShortDateString());
        }

    }
}
