package edu.olezha.jsandbox.bc;

import edu.olezha.jsandbox.multithreading.concurrent.RNG;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Data
public class NiceHash {

    private static int STARTS_WITH = 11111;

    private final String message;
    private final int nonce;
    private final int hash;

    public static void main(String[] args) {
        NiceHash lastRecord = niceHash("me30:" + STARTS_WITH);
        System.out.println(lastRecord);
        NiceHash prevInterestedLine = lastRecord = niceHash("meToMax20:" + lastRecord.hash);
        System.out.println(lastRecord);
        NiceHash interestedLine = lastRecord = niceHash("MaxToMe10:" + lastRecord.hash);
        System.out.println(lastRecord);
        lastRecord = niceHash("meToMax20:" + lastRecord.hash);
        System.out.println(lastRecord);
        lastRecord = niceHash("MaxToMe5:" + lastRecord.hash);
        System.out.println(lastRecord);

        System.out.println(System.lineSeparator() + "Try to replace the 3rd line:");
        long start = System.currentTimeMillis();
        lastRecord = niceHash("MaxToMe20:" + prevInterestedLine.hash, interestedLine.hash);
        System.out.println(lastRecord);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    private static NiceHash niceHash(String message) {
        for (; ; ) {
            int nonce = RNG.random();
            int hash = (message + nonce).hashCode();
            if (Integer.toString(hash).startsWith(Integer.toString(STARTS_WITH)))
                return new NiceHash(message, nonce, hash);
        }
    }

    private static NiceHash niceHash(String message, int neededHash) {
        int nThreads = Runtime.getRuntime().availableProcessors() - 1;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        CompletionService<NiceHash> completionService = new ExecutorCompletionService<>(executorService);
        List<Future<NiceHash>> futures = new ArrayList<>(nThreads);
        for (int i = 0; i < nThreads; i++) {
            futures.add(completionService.submit(() -> {
                for (int nonce = RNG.random(); ; nonce++) {
                    if (Thread.currentThread().isInterrupted())
                        throw new InterruptedException();

                    int hash = (message + nonce).hashCode();
                    if (Integer.toString(hash).equals(Integer.toString(neededHash)))
                        return new NiceHash(message, nonce, hash);
                }
            }));
        }
        executorService.shutdown();

        try {
            return completionService.take().get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            for (Future f : futures)
                f.cancel(true);
            executorService.shutdownNow();
        }
    }

    public String toString() {
        return "record: " + message + ":" + nonce + "; hash: " + hash;
    }
}

//    record: me30:11111:1884891578; hash: 1111183061
//    record: meToMax20:1111183061:1863808974; hash: 1111138596
//    record: MaxToMe10:1111138596:-55552722; hash: 1111157426
//    record: meToMax20:1111157426:-1281572876; hash: 111113758
//    record: MaxToMe5:111113758:-117295141; hash: 1111155302
//
//    Try to replace the 3rd line:
//    record: MaxToMe15:1111138596:-1652923107; hash: 1111157426
//    39035ms

//    record: me30:11111:-727501897; hash: 111111675
//    record: meToMax20:111111675:981494823; hash: 1111158707
//    record: MaxToMe10:1111158707:1511497213; hash: 1111170932
//    record: meToMax20:1111170932:386343837; hash: 1111140738
//    record: MaxToMe5:1111140738:2033898895; hash: 1111171014
//
//    Try to replace the 3rd line:
//    record: MaxToMe20:1111158707:1365680596; hash: 1111170932
//    3138ms
