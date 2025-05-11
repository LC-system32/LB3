import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int prodLeft = 15;
        int consLeft = 15;

        int consumerCount = 5;
        int producerCount = 3;

        Manager manager = new Manager(prodLeft, consLeft ,consumerCount + producerCount, producerCount);

        for (int i = 1; i <= consumerCount; i++) {
            new Consumer(manager, consLeft / consumerCount , i).start();
        }
        for (int i = 1; i <= producerCount; i++) {
            new Producer(manager, prodLeft / producerCount , i).start();
        }
    }
}
