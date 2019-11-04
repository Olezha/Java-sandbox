package edu.olezha.sandbox.gof.singleton;

public class BillPughSingleton {
    
    private BillPughSingleton() {}
    
    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }
    
    private static class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
}
