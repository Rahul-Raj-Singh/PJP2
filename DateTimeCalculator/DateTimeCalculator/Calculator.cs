using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace DateTimeCalculator {

    public class Calculator {

        private readonly DateTime Date;

        public Calculator(String date) {
            Date = DateTime.Parse(date);
        }

        public string DetermineDayOfWeek() {

            return Date.DayOfWeek.ToString();

        }

        public string DetermineWeekNumber() {

            int day = Date.Day, weekNo = 0;

            if (day <= 7)
                weekNo = 1;
            else if (day <= 14)
                weekNo = 2;
            else if (day <= 21)
                weekNo = 3;
            else if (day <= 28)
                weekNo = 4;
            else
                weekNo = 5;

            return $"Month Week No: {weekNo}\nYear Week No: {Date.DayOfYear / 7}";
        }


        public string Add(string s) {

            int[] array = DetermineDayWeekMonthYear(s);

            int day = array[0], week = array[1], month = array[2], year = array[3];

            DateTime newDate = Date;

            newDate = newDate.AddDays(day);
            newDate = newDate.AddDays(week*7);
            newDate = newDate.AddMonths(month);
            newDate = newDate.AddYears(year);

            return newDate.ToLongDateString();

        }

        public string Subtract(string s) {
            // Since user might enter a date
            bool isDate = Regex.IsMatch(s, @"^\d{2}([-/])\d{2}\1\d{4}$");

            if (isDate) {

                TimeSpan timeSpan = Date - DateTime.Parse(s);

                return $"{Math.Abs(timeSpan.Days)} days";
            }

            int[] array = DetermineDayWeekMonthYear(s);

            int day = array[0], week = array[1], month = array[2], year = array[3];

            DateTime newDate = Date;

            newDate = newDate.AddDays(-day);
            newDate = newDate.AddDays(-week * 7);
            newDate = newDate.AddMonths(-month);
            newDate = newDate.AddYears(-year);

            return newDate.ToLongDateString();

        }


        /* Helper Methods */

        /*
         * If input string is "5 days 3 months"
         * This method will return an array {5, 0, 3, 0}, here {day, week, month, year}
         */
        public int[] DetermineDayWeekMonthYear(String s) {

            int[] ans = new int[4];     // {Day, Week, Month, Year}

            string regex = @"(\d+)\s+days?|(\d+)\s+weeks?|(\d+)\s+months?|(\d+)\s+years?";
            MatchCollection matches = new Regex(regex, RegexOptions.IgnoreCase).Matches(s);

            foreach(Match match in matches) {

                if (match.Groups[1].Success)                        // Day
                    ans[0] = Convert.ToInt32(match.Groups[1].Value);
                if (match.Groups[2].Success)                        // Week                    
                    ans[1] = Convert.ToInt32(match.Groups[2].Value);
                if (match.Groups[3].Success)                        // Month
                    ans[2] = Convert.ToInt32(match.Groups[3].Value);
                if (match.Groups[4].Success)                        // Year
                    ans[3] = Convert.ToInt32(match.Groups[4].Value);

            }

            return ans;

        }
    }
}
