package edu.olezha.sandbox.refactoring;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
class A {

    private String some;

    A(String some) {
        this.some = some;
    }
}

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
class B extends A {

    private String other;

    B(String some, String other) {
        super(some);
        this.other = other;
    }
}

@Slf4j
public class DuplicatedCodeWithInheritance {

    public static void main(String[] args) {
        DuplicatedCodeWithInheritance dup = new DuplicatedCodeWithInheritance();
        log.info("{} {}", dup.processA(), dup.processB());
    }

    private List<A> processA() {
        return process(A.class);
    }

    private List<B> processB() {
        return process(B.class);
    }

    private List process(Class<? extends A> clazz) {
        List list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            String some = get(i);

            if (clazz == A.class) {
                list.add(new A(some));
            }
            else if (clazz == B.class) {
                String other = get(i);
                list.add(new B(some, other));
            }
        }
        return list;
    }

    private String get(int i) {
        return Integer.toString(i);
    }
}
