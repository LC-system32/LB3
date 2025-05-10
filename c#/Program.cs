using System;
using System.Threading;

namespace LB1
{
    class Program
    {
        static void Main(string[] args)
        {
            int prodLeft = 10;
            int consLeft = 10;

            Manager manager = new Manager(prodLeft, consLeft);

            int consumerCount = 2;
            int producerCount = 1;

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
