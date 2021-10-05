using System;

namespace EAD_SumVariations
{
    class Program
    {
        static void Main(string[] args)
        {
            sum_variation1();
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
    }
}
