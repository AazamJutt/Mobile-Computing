using System;

namespace EAD_SumVariations
{
    class Program
    {
        static void Main(string[] args)
        {
            //sum_variation1();
            //sum_variation2();
            sum_variation3();
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
            //Calculates Square Sum
            Console.WriteLine("***************Variation 3 - Summation Σ*************");
            Console.WriteLine("Enter \'Single digit Numbers\' you want to sum in a Row i.e; 1 2 3: ");
            string numbers = Console.ReadLine();
            int sum = 0;
            for (int i = 0; i < numbers.Length; i++)
            {
                if (Char.IsDigit(numbers[i]))
                    sum += (int)(numbers[i] - '0');
            }
            Console.WriteLine("Summation(Σ) = " + sum);
        }
    }
}
