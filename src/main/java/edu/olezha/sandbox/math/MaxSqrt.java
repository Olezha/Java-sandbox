package edu.olezha.sandbox.math;

/**
 * https://discuss.codechef.com/t/maximum-no-of-repeated-square-root-operations-that-can-be-performed-on-a-no-in-the-range/22118
 */
public class MaxSqrt {

    private static final int MAX_REPEATED_SQRT = 4;
    private static final int[][] SQRT_MAGIC = {
            {65536, 43046721},
            {256, 6561, 65536, 390625, 1679616, 5764801, 16777216, 43046721, 100000000, 214358881, 429981696, 815730721, 1475789056},
            {16, 81, 256, 625, 1296, 2401, 4096, 6561, 10000, 14641, 20736, 28561, 38416, 50625, 65536, 83521, 104976, 130321, 160000, 194481, 234256, 279841, 331776, 390625, 456976, 531441, 614656, 707281, 810000, 923521, 1048576, 1185921, 1336336, 1500625, 1679616, 1874161, 2085136, 2313441, 2560000, 2825761, 3111696, 3418801, 3748096, 4100625, 4477456, 4879681, 5308416, 5764801, 6250000, 6765201, 7311616, 7890481, 8503056, 9150625, 9834496, 10556001, 11316496, 12117361, 12960000, 13845841, 14776336, 15752961, 16777216, 17850625, 18974736, 20151121, 21381376, 22667121, 24010000, 25411681, 26873856, 28398241, 29986576, 31640625, 33362176, 35153041, 37015056, 38950081, 40960000, 43046721, 45212176, 47458321, 49787136, 52200625, 54700816, 57289761, 59969536, 62742241, 65610000, 68574961, 71639296, 74805201, 78074896, 81450625, 84934656, 88529281, 92236816, 96059601, 100000000, 104060401, 108243216, 112550881, 116985856, 121550625, 126247696, 131079601, 136048896, 141158161, 146410000, 151807041, 157351936, 163047361, 168896016, 174900625, 181063936, 187388721, 193877776, 200533921, 207360000, 214358881, 221533456, 228886641, 236421376, 244140625, 252047376, 260144641, 268435456, 276922881, 285610000, 294499921, 303595776, 312900721, 322417936, 332150625, 342102016, 352275361, 362673936, 373301041, 384160000, 395254161, 406586896, 418161601, 429981696, 442050625, 454371856, 466948881, 479785216, 492884401, 506250000, 519885601, 533794816, 547981281, 562448656, 577200625, 592240896, 607573201, 623201296, 639128961, 655360000, 671898241, 688747536, 705911761, 723394816, 741200625, 759333136, 777796321, 796594176, 815730721, 835210000, 855036081, 875213056, 895745041, 916636176, 937890625, 959512576, 981506241, 1003875856, 1026625681, 1049760000, 1073283121, 1097199376, 1121513121, 1146228736, 1171350625, 1196883216, 1222830961, 1249198336, 1275989841, 1303210000, 1330863361, 1358954496, 1387488001, 1416468496, 1445900625, 1475789056, 1506138481, 1536953616, 1568239201, 1600000000, 1632240801, 1664966416, 1698181681, 1731891456, 1766100625, 1800814096, 1836036801, 1871773696, 1908029761, 1944810000, 1982119441, 2019963136, 2058346161, 2097273616, 2136750625}
    };

    private static int repeatedSquareRoot(int a, int b) {
        for (int i = 0; i < SQRT_MAGIC.length; i++) {
            if (arrEntryInRangeCheck(SQRT_MAGIC[i], a, b)) {
                return MAX_REPEATED_SQRT - i;
            }
        }

        return Math.pow((int) Math.sqrt(b), 2) >= a ? 1 : 0;
    }

    private static boolean arrEntryInRangeCheck(int[] a, int min, int max) {
        if (a[a.length - 1] < min || a[0] > max) {
            return false;
        }

        return arrEntryInRangeCheck(a, min, max, 0, a.length - 1);
    }

    private static boolean arrEntryInRangeCheck(int[] a, int min, int max, int left, int right) {
        if (right < left) {
            return false;
        }

        if (a[left] >= min && a[right] <= max) {
            return true;
        }

        int center = left + (right - left) / 2;

        if (a[center] >= min && a[center] <= max) {
            return true;
        }

        if (a[center] < min || a[center] < max) {
            left = center + 1;
        } else {
            right = center - 1;
        }

        return arrEntryInRangeCheck(a, min, max, left, right);
    }

    private static int glenngouldSolution(int a, int b) {
        int nops = 0;
        a = (int) Math.ceil(Math.sqrt(a));
        b = (int) Math.floor(Math.sqrt(b));
        while (b >= a) {
            nops++;
            a = (int) Math.ceil(Math.sqrt(a));
            b = (int) Math.floor(Math.sqrt(b));
        }
        return nops;
    }

    public static void main(String[] args) {
//        for (int x = 2; x < 216; x++) {
//            int i = 0;
//            System.out.print(x + ": ");
//            long x1 = (long) x * x;
//            while (x1 <= Integer.MAX_VALUE) {
//                System.out.print(x1 + " ");
////                if (i == 1) {
////                    System.out.print(x1 + ", ");
////                }
//                x1 = x1 * x1;
//                i++;
//            }
//            System.out.println();
//        }

        System.out.println(repeatedSquareRoot(10, 20)); // 2
        System.out.println(repeatedSquareRoot(6_000, 7_000)); // 3
        System.out.println(repeatedSquareRoot(2, 1_000_000_000)); // 4
        System.out.println(repeatedSquareRoot(999_000_000, 1_000_000_000)); // 1
        System.out.println(repeatedSquareRoot(11, 12)); // 0
        System.out.println(repeatedSquareRoot(2_000, 20_000)); // 3

        System.out.println(System.lineSeparator() + "glenngould's solution");
        System.out.println(glenngouldSolution(10, 20));
        System.out.println(glenngouldSolution(6_000, 7_000));

//        System.out.println(System.lineSeparator() + "time");
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10_000_000; i++) {
//            repeatedSquareRoot(10, 20);
//            repeatedSquareRoot(6_000, 7_000);
//            repeatedSquareRoot(2, 1_000_000_000);
//            repeatedSquareRoot(999_000_000, 1_000_000_000);
//            repeatedSquareRoot(11, 12);
//            repeatedSquareRoot(2_000, 20_000);
//        }
//        System.out.println("my " + (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 10_000_000; i++) {
//            glenngouldSolution(10, 20);
//            glenngouldSolution(6_000, 7_000);
//            glenngouldSolution(2, 1_000_000_000);
//            glenngouldSolution(999_000_000, 1_000_000_000);
//            glenngouldSolution(11, 12);
//            glenngouldSolution(2_000, 20_000);
//        }
//        System.out.println("glenngould's " + (System.currentTimeMillis() - start));
    }
}
