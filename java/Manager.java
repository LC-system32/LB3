import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Manager
{
    public Semaphore fullStock;
    public Semaphore takeItem;
    public Semaphore emptyStock;

    private int finishedProducers = 0;
    private final int totalProducers;

    public volatile int generalCountProductCreate;
    public volatile int generalCountProductUsing;

    public volatile boolean isProducingWorkDone = false;

    public volatile int createdProduct = 0;

    private ArrayList<String> stock;

    public int getStockSize()
    {
        return stock.size();
    }

    public synchronized String getItemStock() {
        generalCountProductUsing--;
        String item =  stock.get(0);
        stock.remove(0);
        return item;
    }

    public synchronized void addItemStock(String item) {
        generalCountProductCreate--;
        stock.add(item);
    }

    public Manager(int prodLeft, int consLeft, int threadNum, int totalProducers) {
        fullStock = new Semaphore(2);
        takeItem = new Semaphore(1);
        emptyStock = new Semaphore(0);
        stock = new ArrayList<String>();

        this.generalCountProductCreate = prodLeft;
        this.generalCountProductUsing = consLeft;

        this.totalProducers = totalProducers;
    }

    public synchronized void producerFinished() {
        finishedProducers++;
        if (finishedProducers == totalProducers) {
            isProducingWorkDone = true;
            emptyStock.release();
        }
    }
}
