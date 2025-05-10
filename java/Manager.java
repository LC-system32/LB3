import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Manager
{
    public Semaphore fullStock;
    public Semaphore takeItem;
    public Semaphore emptyStock;

    public volatile int generalCountProductCreate = 10;
    public volatile int generalCountProductUsing = 10;

    public volatile boolean isProducingWorkDone = false;

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

    public Manager(int prodLeft, int consLeft) {
        fullStock = new Semaphore(5);
        takeItem = new Semaphore(1);
        emptyStock = new Semaphore(0);
        stock = new ArrayList<String>();

        this.generalCountProductCreate = prodLeft;
        this.generalCountProductUsing = consLeft;
    }
}
