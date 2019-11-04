package edu.olezha.sandbox.gof.singleton;

import org.junit.Test;

import static org.junit.Assert.assertSame;

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
    
//    @Ignore
    @Test
    public void testConcurrentSimpleSingleton() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<SomeManager>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            futures.add(executorService.submit(new GetSimpleSingletonProcess()));
        executorService.shutdown();
        
        for (int i = 1; i < futures.size(); i++)
            assertSame(futures.get(i - 1).get(), futures.get(i).get());
    }
    
    @Test
    public void testConcurrentBillPughSingleton() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<BillPughSingleton>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            futures.add(executorService.submit(new GetBillPughSingletonProcess()));
        executorService.shutdown();
        
        for (int i = 1; i < futures.size(); i++)
            assertSame(futures.get(i - 1).get(), futures.get(i).get());
    }
    
    private class GetSimpleSingletonProcess implements Callable<SomeManager> {
        @Override
        public SomeManager call() throws Exception {
            return SomeManager.getInstance();
        }
    }
    
    private class GetBillPughSingletonProcess implements Callable<BillPughSingleton> {
        @Override
        public BillPughSingleton call() throws Exception {
            return BillPughSingleton.getInstance();
        }
    }
    
}
