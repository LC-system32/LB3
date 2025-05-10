import java.util.concurrent.Semaphore;

public class Producer extends Thread {

    private final int idProducer;
    private Manager manager;
    private int countProduct;

    public Producer(Manager manager, int countProduct, int idProducer) {
        this.idProducer = idProducer;
        this.manager = manager;
        this.countProduct = countProduct;
    }

    @Override
    public void run() {
        for (int i = 0; i < countProduct; i++)
        {
            try {
                if (manager.generalCountProductCreate <= 0) {
                    manager.isProducingWorkDone = true;
                    break;
                }

                Thread.sleep(1000);
                manager.fullStock.acquire();
                manager.takeItem.release();

                manager.addItemStock("Product " + i);
                System.out.println("Producer " + idProducer + " added to stock product " + i);

                manager.emptyStock.release();
                manager.takeItem.release();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Producer " + idProducer + " has finished work");
    }
}
