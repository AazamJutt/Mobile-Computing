using System;

namespace EAD_SumVariations
{
    class Program
    {
        static void Main(string[] args)
        {
            //sum_variation1();
            //sum_variation2();
            //sum_variation3();
            //sum_variation4();
            sum_variation6();
        }
        static void menu()
        {
            Console.WriteLine("*******************Menu*********************");
            Console.WriteLine("1 - Sum.");
            Console.WriteLine("2 - Square Sum.");
            Console.WriteLine("3 - Summation of N numbers.");
            Console.WriteLine("4 - Average of N Numbers.");
            Console.WriteLine("5 - N numbers of Fibonacci Sequence.");
            Console.WriteLine("6 - Sums of numbers Between a range.");
            Console.WriteLine("7 - Exit.");
            Console.Write("Enter Your Choice: ");
            int choice = 0;
            do
            {
                choice = Int32.Parse(Console.ReadLine());
                switch (choice)
                {
                    case 1:
                        sum_variation1();
                        break;
                    case 2:
                        sum_variation2();
                        break;
                    case 3:
                        sum_variation3();
                        break;
                    case 4:
                        sum_variation4();
                        break;
                    case 5:
                        sum_variation5();
                        break;
                    case 6:
                        sum_variation6();
                        break;
                    default:
                        break;
                }
            } while (choice > 0 && choice < 7);
        }

        static void sum_variation1()
        {
            Console.WriteLine("***************Variation 1 - Sum*************");
            Console.Write("Enter Number A: ");
            int a = Int32.Parse(Console.ReadLine());
            Console.Write("Enter Number B: ");
            int b = Int32.Parse(Console.ReadLine());
            Console.WriteLine("Sum = " + (a + b));
        }

        static void sum_variation2()
        {
            //Calculates Square Sum
            Console.WriteLine("***************Variation 2 - Square Sum*************");
            Console.Write("Enter Number A: ");
            int a = Int32.Parse(Console.ReadLine());
            Console.Write("Enter Number B: ");
            int b = Int32.Parse(Console.ReadLine());
            Console.WriteLine("Square Sum = " + (a * a + b * b));
        }
        static void sum_variation3()
        {
            //Calculates Summation
            Console.WriteLine("***************Variation 3 - Summation*************");
            Console.WriteLine("Enter \'Single digit Numbers\' you want to sum in a Row i.e; 1 2 3: ");
            string numbers = Console.ReadLine();
            int sum = 0;
            for (int i = 0; i < numbers.Length; i++)
            {
                if (Char.IsDigit(numbers[i]))
                    sum += (int)(numbers[i] - '0');
            }
            Console.WriteLine("Summation = " + sum);
        }
        static void sum_variation4()
        {
            //Calculates average
            Console.WriteLine("***************Variation 4 - Average*************");
            Console.WriteLine("Enter \'Single digit Numbers\' you want to find average of, in a Row i.e; 1 2 3: ");
            string numbers = Console.ReadLine();
            int sum = 0;
            int n = 0;
            for (int i = 0; i < numbers.Length; i++)
            {
                if (Char.IsDigit(numbers[i]))
                {
                    sum += (int)(numbers[i] - '0');
                    n++;
                }
            }
            Console.WriteLine("Summation = " + sum/n);
        }
        static void sum_variation5()
        {
            //Finds Fibonacci sequeence
            Console.WriteLine("***************Variation 5 - Fibonacci*************");
            Console.Write("Enter number of Fibonacci Sequence you want to see: ");
            int len = int.Parse(Console.ReadLine());
            int a = 0, b = 1, c = 0;
            Console.Write("Fibonacci Sequence: {0} {1}", a, b);
            for (int i = 2; i < len; i++)
            {
                c = a + b;
                Console.Write(" {0}", c);
                a = b;
                b = c;
            }
        }
        static void sum_variation6()
        {
            //Calculates average
            Console.WriteLine("***************Variation 6 - Find sum of numbers between a given Range**************");
            Console.Write("Enter Lower Limit: ");
            int ll = int.Parse(Console.ReadLine());
            Console.Write("Enter Upper Limit: ");
            int ul = int.Parse(Console.ReadLine());
            int sum = 0;
            for (int i =ll; i <= ul; i++)
            {
                sum += i;
            }
            Console.WriteLine("Sum = " + sum);
        }

    }
}
