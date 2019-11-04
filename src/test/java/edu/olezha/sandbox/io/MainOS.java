package edu.olezha.sandbox.io;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created by oleh on 27.12.17.
 */
public class MainOS {

    @Test
    public void testOS() throws IOException {

        byte[] data = new byte[20];

        OutputStream outputStream = new OutputStream() {
            int index = 0;

            @Override
            public void write(int i) throws IOException {
                data[index++] = (byte) i;
            }
        };

        byte[] in = {1, 2, 3, 4, 5};

        outputStream.write(in);

        assertEquals(
                "[1, 2, 3, 4, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]",
                Arrays.toString(data));

        outputStream.write(12312345);
        assertNotEquals(
                "[1, 2, 3, 4, 5, 12312345, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]",
                Arrays.toString(data));
        assertEquals(
                "[1, 2, 3, 4, 5, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]",
                Arrays.toString(data));
        assertEquals(25, (byte) 12312345);

        outputStream.write(in, 2, 2);
        assertEquals(
                "[1, 2, 3, 4, 5, 25, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]",
                Arrays.toString(data));
    }

    // TODO ~1.2h
}
