package edu.olezha.sandbox.gof.singleton;

/**
 * Created by oleh on 13.11.16.
 */
public class SomeManager {

    private static SomeManager instance;

    private SomeManager() {}

    public static SomeManager getInstance() {
        if (instance == null)
            instance = new SomeManager();
        return instance;
    }

}
