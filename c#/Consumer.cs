using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace LB1
{
    class Consumer
    {
        private Manager manager;
        private int idConsumer;
        private int countProduct;

        public Consumer(Manager manager, int countProduct, int idConsumer)
        {
            this.manager = manager;
            this.idConsumer = idConsumer;
            this.countProduct = countProduct;
        }
        public void Run()
        {
            for (int i = 0; i < countProduct; i++)
            {
                Thread.Sleep(1000);

                manager.fullStock.WaitOne();
                manager.takeItem.WaitOne();

                if (manager.StockSize > 0)
                {
                    string product = manager.ItemStock;
                    Console.WriteLine($"Consumer {idConsumer} taken from stock {product}");

                    manager.takeItem.Release();
                    manager.emptyStock.Release();
                }
                else
                {
                    manager.takeItem.Release();
                    break;
                }
            }
            Console.WriteLine("Consumer " + idConsumer + " has finished work");
        }
    }
}
