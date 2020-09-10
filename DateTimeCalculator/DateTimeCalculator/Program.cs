using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DateTimeCalculator {

    class Program {
        static void Main(string[] args) {

            // To track history in current session
            var session = new List<string>();

            while (true) {

                Menu1();

                string option1 = Console.ReadLine().Trim();

                // Quit the app
                if (option1.Equals("Q", StringComparison.InvariantCultureIgnoreCase))
                    break;

                // NLP
                if (option1.Equals("2")) {

                    Console.WriteLine("Enter expression");
                    Console.WriteLine("  e.g. today, tomorrow, next-week, 3 days earlier etc");

                    string expression = Console.ReadLine().Trim();
                    string ans = new NLP().Translate(expression);
                    Console.WriteLine(ans);

                    session.Add($"NLP: {expression} => {ans}");
                    continue;
                }

                Console.WriteLine("Enter Date");
                string date = Console.ReadLine().Trim();

                // Performs all date calculations
                Calculator calculator = new Calculator(date);

                Menu2();

                string option2 = Console.ReadLine().Trim();

                switch (option2) {

                    case "1": {

                            string ans = calculator.DetermineDayOfWeek();
                            Console.WriteLine(ans);

                            session.Add($"{date}: DayOfWeek => {ans}");
                            break;
                    }

                    case "2": {

                            string ans = calculator.DetermineWeekNumber();
                            Console.WriteLine(ans);

                            session.Add($"{date}: Week Number => {ans}");
                            break;
                    }
                        

                    case "3": {

                            Console.WriteLine("Enter data to Add");
                            Console.WriteLine("   eg: 3 days, 4 months, 2 days 1 year etc");

                            string input = Console.ReadLine().Trim();
                            string ans = calculator.Add(input);
                            Console.WriteLine(ans);

                            session.Add($"{date}: Add {input} => {ans}");
                            break;
                        }


                    case "4": {

                            Console.WriteLine("Enter data to Subtract");
                            Console.WriteLine("   eg: 31/12/2018, 3 days, 4 months, 2 days 1 year etc");

                            string input = Console.ReadLine().Trim();
                            string ans = calculator.Subtract(input);
                            Console.WriteLine(ans);

                            session.Add($"{date}: Subtract {input} => {ans}");
                            break;
                    }


                    default:
                        Console.WriteLine("Invalid Choice !!!");
                        break;

                }   // End of switch


            }

            Console.WriteLine("-----Session Closed-----");
            SaveSession(session);
            Console.WriteLine("-----Session Saved------");

        }

        private static void Menu2() {

            Console.WriteLine("Choose Option");
            Console.WriteLine("1. Week Day");
            Console.WriteLine("2. Week Number");
            Console.WriteLine("3. Add");
            Console.WriteLine("4. Subtract");
            
        }

         private static void Menu1() {

            Console.WriteLine("Choose Option");
            Console.WriteLine("1. Enter Date");
            Console.WriteLine("2. Enter Natural Language");
            Console.WriteLine("**Press Q to Quit**");
        }

        // Persist history of current session in text file
        private static void SaveSession(List<string> session) {

            string location = Directory.GetCurrentDirectory().Replace(@"bin\Debug", "") + "history.txt";

            using (StreamWriter sw = File.AppendText(location)) {

                foreach(var line in session) 
                    sw.WriteLine(line);
                sw.WriteLine("----------");
            }
        }
    }
}
