package edu.olezha.sandbox.core;

import java.util.ArrayList;
import java.util.List;

public class Variance {

    static class A {}

    static class B extends A{}

    static class C extends B {}

    public static void main(String[] args) {
        A a;
        B b;

        List<B> invariantBList = new ArrayList<>();
        invariantBList.add(new B());
        invariantBList.add(new C());
        a = invariantBList.get(0);
        b = invariantBList.get(0);

        List<? extends B> covarianceBList = new ArrayList<>();
        a = covarianceBList.get(0);
        b = covarianceBList.get(0);
//        covarianceBList.add(new B());
//        covarianceBList.add(new C());

        List<? super B> contravarianceBList = new ArrayList<>();
//        a = contravarianceBList.get(0);
//        b = contravarianceBList.get(0);
        contravarianceBList.add(new B());
        contravarianceBList.add(new C());
    }

    A firstA(List<? extends B> covarianceBList) {
        return covarianceBList.get(0);
    }

    void addC(List<? super B> contravarianceBList) {
        contravarianceBList.add(new C());
    }

}
