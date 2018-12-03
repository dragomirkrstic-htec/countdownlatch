package rs.htec.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchDemo {

    private static final Integer NUMBER_OF_WORKERS = 1000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(NUMBER_OF_WORKERS);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishedLatch = new CountDownLatch(NUMBER_OF_WORKERS);
        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_WORKERS; i++) {
            es.execute(new Worker(startLatch, finishedLatch, random));
        }

        System.out.println("Threads initialized. Starting threads...");
        startLatch.countDown();
        System.out.println("Waiting for threads to finish.");
        finishedLatch.await();
        System.out.println("All threads are done.");
        es.shutdown();
    }
}
