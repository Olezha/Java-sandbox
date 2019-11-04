package edu.olezha.sandbox.core.langref;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private List<Obj> strongList = new ArrayList<>();
    ;
    private List<Reference<Obj>> softList = new ArrayList<Reference<Obj>>();
    private List<Reference<Obj>> weakList = new ArrayList<Reference<Obj>>();
    private List<Reference<Obj>> phantomList = new ArrayList<Reference<Obj>>();
    private ReferenceQueue<Obj> referenceQueue = new ReferenceQueue<Obj>();
    private List<String> loadMemoryList = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        System.gc();
        System.out.println("-- GC");
        main.printLists();
        System.out.println("-- load memory ..");
        main.loadMemory();
        main.printLists();
    }

    private void printLists() {
        System.out.println("Strong references: " + strongList);
        System.out.println("SoftReferences: " + listReferencesToString(softList));
        System.out.println("WeakReferences: " + listReferencesToString(weakList));
        System.out.println("PhantomReferences: " + listReferencesToString(phantomList));
    }

    private String listReferencesToString(List<Reference<Obj>> list) {
        List<String> collection = new ArrayList<>();
        for (Reference<Obj> ref : list)
            collection.add(String.valueOf(ref.get()));
        return String.valueOf(collection);
    }

    private Main() {
        for (int i = 0; i < 3; i++) {
            strongList.add(new Obj("St" + i));
            softList.add(new SoftReference<Obj>(new Obj("SR" + i)));
            weakList.add(new WeakReference<Obj>(new Obj("WR" + i)));
            phantomList.add(new PhantomReference<>(new Obj("PR" + i), referenceQueue));
        }
        printLists();
    }

    private void loadMemory() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1200000; j++) {
                loadMemoryList.add(i + "-" + j);
            }
            System.gc();
        }
    }
}
