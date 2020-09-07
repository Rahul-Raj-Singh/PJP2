using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DateTimeCalculator {

    class Program {
        static void Main(string[] args) {

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

                    Console.WriteLine(new NLP().Translate(expression));
                    continue;
                }

                Console.WriteLine("Enter Date");
                string date = Console.ReadLine().Trim();

                // Performs all date calculations
                Calculator calculator = new Calculator(date);

                Menu2();

                string option2 = Console.ReadLine().Trim();

                switch (option2) {

                    case "1":
                        Console.WriteLine(calculator.DetermineDayOfWeek());
                        break;

                    case "2":
                        Console.WriteLine(calculator.DetermineWeekNumber());
                        break;

                    case "3":
                        Console.WriteLine("Enter data to Add");
                        Console.WriteLine("   eg: 3 days, 4 months, 2 days 1 year etc");

                        string input1 = Console.ReadLine().Trim();

                        Console.WriteLine(calculator.Add(input1));
                        break;

                    case "4":
                        Console.WriteLine("Enter data to Subtract");
                        Console.WriteLine("   eg: 31/12/2018, 3 days, 4 months, 2 days 1 year etc");

                        string input2 = Console.ReadLine().Trim();

                        Console.WriteLine(calculator.Subtract(input2));
                        break;

                    default:
                        Console.WriteLine("Invalid Choice !!!");
                        break;

                }   // End of switch


            }

            Console.WriteLine("-----Session Closed-----");

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
    }
}
