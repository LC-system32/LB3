import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int prodLeft = 10;
        int consLeft = 10;

        Manager manager = new Manager(prodLeft, consLeft);

        int consumerCount = 2;
        int producerCount = 1;

        for (int i = 1; i <= consumerCount; i++) {
            new Consumer(manager, consLeft / consumerCount , i).start();
        }
        for (int i = 1; i <= producerCount; i++) {
            new Producer(manager, prodLeft / producerCount , i).start();
        }
    }
}
