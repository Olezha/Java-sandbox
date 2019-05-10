package edu.olezha.jsandbox.math;

public class CalcBytes {

    static final int KiB = 1024;
    static final int MiB = KiB * KiB;
    static final int GiB = MiB * KiB;

    public static void main(String[] args) {
        long maxDataLengthToInsert = ((long) Integer.MAX_VALUE) * CHKBlock.DATA_LENGTH;
        System.out.println(maxDataLengthToInsert + " B = " + maxDataLengthToInsert / GiB + " GiB");
        // 70368744144896 B = 65535 GiB
    }
}

class CHKBlock {
    static final int DATA_LENGTH = 32768;
}
