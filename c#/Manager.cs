using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace LB1
{
    class Manager
    {
        public Semaphore fullStock;
        public Semaphore takeItem;
        public Semaphore emptyStock;

        public volatile int prodLeft;
        public volatile int consLeft;

        private readonly object lockObj = new object();

        private int finishedProducers = 0;
        private int totalProducers;

        public volatile bool isProducingWorkDone = false;

        private List<string> stock;

        public int StockSize => stock.Count();

        public string ItemStock
        {
            get
            {

                    consLeft--;
                    string item = stock[0];
                    stock.RemoveAt(0);
                    return item; 
            }
            set
            {
                    prodLeft--;
                    stock.Add(value); 
            }
        }

        public void ProducerFinished()
        {
            lock (lockObj)
            {
                finishedProducers++;
                if (finishedProducers == totalProducers)
                {
                    isProducingWorkDone = true;
                    fullStock.Release();
                }
            }
        }

        public Manager(int prodLeft, int consLeft, int totalProducers)
        {
            fullStock = new Semaphore(0, 2);
            emptyStock = new Semaphore(5, 5);
            takeItem = new Semaphore(1, 1);
            stock = new List<string>();

            this.prodLeft = prodLeft;
            this.consLeft = consLeft;
            this.totalProducers=totalProducers;
        }
    }
}
