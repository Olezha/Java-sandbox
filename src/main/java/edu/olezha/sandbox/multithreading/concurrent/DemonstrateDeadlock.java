package edu.olezha.sandbox.multithreading.concurrent;

import edu.olezha.sandbox.multithreading.concurrent.model.Account;
import edu.olezha.sandbox.multithreading.concurrent.model.DollarAmount;

import java.util.Random;

public class DemonstrateDeadlock {

    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1_000_000;

//    private static final Object tieLock = new Object();

    // try dump threads
    public static void main(String[] args) {
        final Random rnd = new Random();

        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++)
            accounts[i] = new Account(new DollarAmount(rnd.nextInt(1_000)));

        class TransferThread extends Thread {

            public void run() {
                for (int i=0; i<NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    DollarAmount amount = new DollarAmount(rnd.nextInt(1_000));

                    try {
                        transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                    } catch (DollarAmount.InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++)
            new TransferThread().start();
    }

    private static void transferMoney(Account from, Account to, DollarAmount amount) throws DollarAmount.InsufficientFundsException {
        class Helper {

            private void transfer() throws DollarAmount.InsufficientFundsException {
                if (from.getBalance().compareTo(amount) < 0)
                    throw new DollarAmount.InsufficientFundsException();
                else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }
        }

//        int fromHash = System.identityHashCode(from);
//        int toHash = System.identityHashCode(to);
//
//        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
//        } else if (fromHash > toHash) {
//            synchronized (to) {
//                synchronized (from) {
//                    new Helper().transfer();
//                }
//            }
//        } else {
//            synchronized (tieLock) {
//                synchronized (from) {
//                    synchronized (to) {
//                        new Helper().transfer();
//                    }
//                }
//            }
//        }
    }
}
