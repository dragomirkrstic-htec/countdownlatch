package rs.htec.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private final CountDownLatch startLatch;
    private final CountDownLatch finishedLatch;
    private final Random random;

    public Worker(CountDownLatch startLatch, CountDownLatch finishedLatch, Random random) {
        this.startLatch = startLatch;
        this.finishedLatch = finishedLatch;
        this.random = random;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
            System.out.println(Thread.currentThread().getName() + " | doing some work here.");
            sleep(random);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            finishedLatch.countDown();
        }
    }

    private void sleep(Random random) {
        long millis = (long) random.nextDouble() * 500;
        try {
            Thread.sleep(3000L + millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
