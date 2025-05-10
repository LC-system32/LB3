using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace LB1
{
    class Producer
    {
        private Manager manager;
        private int idProducer;
        private int countProduct;

        public Producer(Manager manager, int countProduct, int idProducer)
        {
            this.manager = manager;
            this.idProducer = idProducer;
            this.countProduct = countProduct;
        }

        public void Run()
        {
            for (int i = 0; i < countProduct; i++)
            {
                if (manager.prodLeft <= 0)
                {
                    manager.isProducingWorkDone = true;
                    break;
                }
                Thread.Sleep(1000);

                manager.emptyStock.WaitOne();
                manager.takeItem.WaitOne();

                manager.ItemStock = "Product " + i;
                Console.WriteLine("Producer " + idProducer + " added to stock product " + i);

                manager.takeItem.Release();
                manager.fullStock.Release();


            }
            Console.WriteLine("Producer " + idProducer + " has finished work");
        }
    }
}
