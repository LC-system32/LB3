public class Consumer extends Thread {
    private final int idConsumer;
    private Manager manager;
    private int countProduct;

    public Consumer(Manager manager,int countProduct, int idConsumer) {
        this.idConsumer = idConsumer;
        this.manager = manager;
        this.countProduct = countProduct;
    }

    @Override
    public void run() {
        for (int i = 0; i < countProduct; i++)
        {
            try {
                Thread.sleep(2000);
                manager.emptyStock.acquire();
                manager.takeItem.acquire();

                if (manager.isProducingWorkDone && manager.getStockSize() == 0) {
                    manager.takeItem.release();
                    break;
                }

                if (manager.getStockSize() > 0 && manager.generalCountProductUsing > 0) {
                    String item = manager.getItemStock();
                    System.out.println("Consumer " + idConsumer + " taken from stock " + item);
                }

                manager.takeItem.release();
                manager.fullStock.release();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Consumer " + idConsumer + " has finished work");
    }
}
