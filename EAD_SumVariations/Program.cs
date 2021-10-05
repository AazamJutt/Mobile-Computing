using System;

namespace EAD_SumVariations
{
    class Program
    {
        static void Main(string[] args)
        {
            sum_variation1();
            sum_variation2();
        }

        static void sum_variation1()
        {
            Console.WriteLine("***************Sum Variation 1*************");
            Console.Write("Enter Number A: ");
            int a = Int32.Parse(Console.ReadLine());
            Console.Write("Enter Number B: ");
            int b = Int32.Parse(Console.ReadLine());
            Console.WriteLine("Sum = " + (a + b));
        }

        static void sum_variation2()
        {
            //Calculates Square Sum
            Console.WriteLine("***************Sum Variation 2*************");
            Console.Write("Enter Number A: ");
            int a = Int32.Parse(Console.ReadLine());
            Console.Write("Enter Number B: ");
            int b = Int32.Parse(Console.ReadLine());
            Console.WriteLine("Square Sum = " + (a*a + b*b));
        }
    }
}
