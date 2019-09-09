package edu.olezha.jsandbox.multithreading.concurrent.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private DollarAmount dollarAmount;

    public final Lock lock = new ReentrantLock();

    public Account(DollarAmount dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    public DollarAmount getBalance() {
        return dollarAmount;
    }

    public void debit(DollarAmount dollarAmount) {
        System.out.println(this + " debit");
        dollarAmount.debit(dollarAmount);
    }

    public void credit(DollarAmount dollarAmount) {
        System.out.println(this + " credit");
        dollarAmount.credit(dollarAmount);
    }
}
