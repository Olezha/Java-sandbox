package edu.olezha.sandbox.math;

import java.math.BigInteger;

public class PositiveLong128 {

    private long hi = 0, lo;

    public PositiveLong128(long n) {
        if (n < 0)
            throw new UnsupportedOperationException();
        lo = n;
    }

    public PositiveLong128(long n_hi_bits, long n_lo_bits) {
        hi = n_hi_bits;
        lo = n_lo_bits;
    }

    public void add(long n) {
        throw new UnsupportedOperationException();
    }

    public static PositiveLong128 add(long n1, long n2) {
        throw new UnsupportedOperationException();
    }

    public void subtract(long n) {
        throw new UnsupportedOperationException();
    }

    public void multiply(long n) {
        throw new UnsupportedOperationException();
    }

    public static PositiveLong128 multiply(long n1, long n2) {
        throw new UnsupportedOperationException();
    }

    public void divide(long n) {
        throw new UnsupportedOperationException();
    }

    public long remainder(long n) {
        throw new UnsupportedOperationException();
    }

    public BigInteger value() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }

    public long longValue() {
        if (hi != 0)
            throw new RuntimeException("Overflow");
        throw new UnsupportedOperationException();
    }
}
