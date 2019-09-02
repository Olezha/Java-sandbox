package edu.olezha.jsandbox.multithreading.concurrent;

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
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++)
            new TransferThread().start();
    }

    private static void transferMoney(Account from, Account to, DollarAmount amount) throws InsufficientFundsException {
        class Helper {

            private void transfer() throws InsufficientFundsException {
                if (from.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException();
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

class Account {

    private DollarAmount dollarAmount;

    Account(DollarAmount dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    DollarAmount getBalance() {
        return dollarAmount;
    }

    void debit(DollarAmount dollarAmount) {
        System.out.println(this + " debit");
        dollarAmount.debit(dollarAmount);
    }

    void credit(DollarAmount dollarAmount) {
        System.out.println(this + " credit");
        dollarAmount.credit(dollarAmount);
    }
}

class DollarAmount implements Comparable<DollarAmount> {

    private int amount;

    DollarAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(DollarAmount o) {
        return amount - o.amount;
    }

    void debit(DollarAmount dollarAmount) {
        amount -= dollarAmount.amount;
    }

    void credit(DollarAmount dollarAmount) {
        amount += dollarAmount.amount;
    }
}

class InsufficientFundsException extends Exception {

    InsufficientFundsException() {
        super("Insufficient Funds");
    }
}
