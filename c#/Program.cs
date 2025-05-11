using System;
using System.Threading;

namespace LB1
{
    class Program
    {
        static void Main(string[] args)
        {
            int prodLeft = 15;
            int consLeft = 15;

            int consumerCount = 5;
            int producerCount = 3;
            
            Manager manager = new Manager(prodLeft, consLeft, producerCount);

            for (int i = 1; i <= producerCount; i++)
            {
                new Thread(new Producer(manager, prodLeft / producerCount, i).Run).Start();
            }
            for (int i = 1; i <= consumerCount; i++)
            {
                new Thread(new Consumer(manager, consLeft / consumerCount, i).Run).Start();
            }

        }
    }
}
