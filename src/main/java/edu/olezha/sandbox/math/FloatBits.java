package edu.olezha.sandbox.math;

import java.util.Objects;

public class FloatBits implements Cloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
        FloatBits a = new FloatBits(-0f);
        FloatBits b = new FloatBits(+0f);
        System.out.println((a.v == b.v) + " " + a.equals(b));
        System.out.println(Float.floatToIntBits(a.v) + " " + Float.floatToIntBits(b.v));

        FloatBits positiveInfinity = new FloatBits(Float.POSITIVE_INFINITY);
        FloatBits negativeInfinity = new FloatBits(Float.NEGATIVE_INFINITY);
        FloatBits nan = new FloatBits(Float.NaN);
        FloatBits nanClone = (FloatBits) nan.clone();

        System.out.println((nan.v == nanClone.v) + " " +
                nan.equals(nanClone) + " " + (Float.floatToIntBits(nan.v) == Float.floatToIntBits(nanClone.v)));

        System.out.println((positiveInfinity.v - negativeInfinity.v) + " " + (negativeInfinity.v + positiveInfinity.v));
        System.out.println(positiveInfinity.v + " in int bits: " + Float.floatToIntBits(positiveInfinity.v));
        System.out.println(negativeInfinity.v + " in int bits: " + Float.floatToIntBits(negativeInfinity.v));
    }

    private float v;

    private FloatBits(float v) {
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatBits floatBits = (FloatBits) o;
        return Float.compare(floatBits.v, v) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v);
    }
}
