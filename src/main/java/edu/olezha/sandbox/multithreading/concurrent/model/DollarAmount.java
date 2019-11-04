package edu.olezha.sandbox.multithreading.concurrent.model;

public class DollarAmount implements Comparable<DollarAmount> {

    private int amount;

    public DollarAmount(int amount) {
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

    public static class InsufficientFundsException extends Exception {

        public InsufficientFundsException() {
            super("Insufficient Funds");
        }
    }
}
