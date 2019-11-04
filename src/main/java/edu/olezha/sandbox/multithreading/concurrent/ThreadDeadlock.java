package edu.olezha.sandbox.multithreading.concurrent;

import java.util.concurrent.*;

public class ThreadDeadlock {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> page = executorService.submit(new ThreadDeadlock.RenderPageTask());
        System.out.println(page.get());
    }

    public static class RenderPageTask implements Callable<String> {

        public String call() throws Exception {
            Future<String> header, footer;
            header = executorService.submit(new LoadFileTask("header.html"));
            footer = executorService.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // Will deadlock -- task waiting for result of subtask
            return header.get() + page + footer.get();
        }
    }

    private static String renderBody() {
        return " body ";
    }
}

class LoadFileTask implements Callable<String> {

    private String file;

    LoadFileTask(String file) {
        this.file = file;
    }

    @Override
    public String call() throws Exception {
        wait(1_000);
        return file;
    }
}
