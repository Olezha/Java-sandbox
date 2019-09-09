package edu.olezha.jsandbox.multithreading.concurrent;

import edu.olezha.jsandbox.multithreading.concurrent.model.Account;
import edu.olezha.jsandbox.multithreading.concurrent.model.DollarAmount;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CanonicalFormOfUsingLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println("synchronized");
        } finally {
            lock.unlock();
        }
    }

    public boolean transferMoney(Account fromAcct, Account toAcct, DollarAmount amount,
                                 long timeout, TimeUnit unit)
            throws DollarAmount.InsufficientFundsException, InterruptedException {
//        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
//        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);

        for (; ; ) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.getBalance().compareTo(amount) < 0) {
                                throw new DollarAmount.InsufficientFundsException();
                            } else {
                                fromAcct.debit(amount);
                                toAcct.credit(amount);
                                return true;
                            }
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }

            if (System.nanoTime() > stopTime)
                return false;

//            TimeUnit.NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
        }
    }

//    public boolean trySendOnSharedLine(String message,
//                                       long timeout, TimeUnit unit)
//            throws InterruptedException {
//        long nanosToLock = unit.toNanos(timeout) - estimatedNanosToSend(message);
//        if (!lock.tryLock(nanosToLock, TimeUnit.NANOSECONDS))
//            return false;
//        try {
//            return sendOnSharedLine(message);
//        } finally {
//            lock.unlock();
//        }
//    }
}
