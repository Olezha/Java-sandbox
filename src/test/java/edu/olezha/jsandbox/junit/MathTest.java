package edu.olezha.jsandbox.junit;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by oleh on 12.11.16.
 */
public class MathTest {

<<<<<<< HEAD
    private static final double delta = .003;
=======
    private static final double delta = .002;
>>>>>>> 429d1e78be643f0579631365258bedc4c064ba5f

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After class");
    }

    @Before
    public void before() {
        System.out.println("    Before");
    }

    @After
    public void after() {
        System.out.println("    After");
    }

    @Test
    public void testAdd() {
        System.out.println("        testAdd");

        assertEquals(4, Math.add(2, 2), delta);
        assertNotEquals(3, Math.add(0, 1));
    }

    @Test
    public void testDivide() {
        System.out.println("        testDivide");

        assertEquals(1, Math.divide(10, 10), delta);
    }

    @Test(expected=DivByZeroException.class)
    public void testDivideDivByZero() {
        System.out.println("        testDivideDivByZero");

        Math.divide(1, 0);
    }

    @Ignore
    @Test
    public void testSin() {
        System.out.println("        testSin");

        assertEquals(0, Math.sin(0), delta);
        assertEquals(1, Math.sin(java.lang.Math.PI / 2), delta);
        assertEquals(-1, Math.sin(3 * java.lang.Math.PI / 2), delta);
    }

}
