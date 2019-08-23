package edu.olezha.jsandbox.multithreading.concurrent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class WebServer {

    private static final int N_THREADS = 100;
    private static final ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);

    public static void main(String[] args) throws IOException {
        final ServerSocket socket = new ServerSocket(8081);

        new Thread(() -> {
            try (Scanner in = new Scanner(System.in)) {
                for (; ; ) {
                    switch (in.nextLine()) {
                        case "exit":
                            try {
                                socket.close();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                            return;
                        case "help":
                            System.out.println("Available commands: exit");
                            break;
                    }
                }
            } finally {
                System.out.println("Scanner done");
            }
        }).start();

        try {
            while (!socket.isClosed()) {
                Socket connection = socket.accept();
                executor.submit(() -> handleRequest(connection));
            }
        } catch (IOException | RejectedExecutionException e) {
            System.out.println(e.getMessage());
        } finally {
            executor.shutdownNow();
            System.out.println("ServerSocket done");
        }
    }

    private static void handleRequest(Socket connection) {
        System.out.println(connection);
    }
}
