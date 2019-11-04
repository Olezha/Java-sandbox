package edu.olezha.sandbox.gof.singleton;

import org.junit.Test;

import static org.junit.Assert.assertSame;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingletonsTest {

    @Test
    public void testSimpleSingleton() {
        SomeManager oneManager = SomeManager.getInstance();
        SomeManager otherManager = SomeManager.getInstance();
        assertSame(oneManager, otherManager);
    }

    @Test
    public void testBillPughSingleton() {
        BillPughSingleton oneSingleton = BillPughSingleton.getInstance();
        BillPughSingleton otherSingleton = BillPughSingleton.getInstance();
        assertSame(oneSingleton, otherSingleton);
    }

    @Test
    public void testConcurrentSimpleSingleton() throws InterruptedException, ExecutionException,
            NoSuchFieldException, IllegalAccessException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10_000; i++) {
            Future<SomeManager> f1 = executorService.submit(SomeManager::getInstance);
            Future<SomeManager> f2 = executorService.submit(SomeManager::getInstance);
            assertSame(f1.get(), f2.get());

            Field instanceField = SomeManager.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(SomeManager.class, null);
        }
        executorService.shutdown();
    }

    @Test
    public void testConcurrentBillPughSingleton() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<BillPughSingleton>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            futures.add(executorService.submit(BillPughSingleton::getInstance));
        executorService.shutdown();

        for (int i = 1; i < futures.size(); i++)
            assertSame(futures.get(i - 1).get(), futures.get(i).get());
    }
}
