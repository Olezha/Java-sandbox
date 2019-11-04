package edu.olezha.sandbox.core.langref;

import lombok.Data;

@Data
public class Obj {

    private String value;

    Obj(String value) {
        this.value = value;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(value + " finalize");
    }
}
