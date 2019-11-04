package edu.olezha.sandbox.io;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by oleh on 26.12.17.
 */
public class MainIS {

    private byte[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    private InputStream newInputStream() {
        return new InputStream() {
            int index = 0;

            @Override
            public int read() throws IOException {
                if (index == data.length)
                    return -1;
                return data[index++];
            }

            @Override
            public int available() throws IOException {
                return data.length - index;
            }
        };
    }

    private InputStream newMarkedInputStream() {
        return new InputStream() {
            int index = 0;
            int mark = 0,
                    readingLimitAfterMarking = 0,
                    readingCounterAfterMarking = 0;
            boolean marked = false;

            @Override
            public boolean markSupported() {
                return true;
            }

            @Override
            public synchronized void mark(int limit) {
                readingLimitAfterMarking = limit;
                readingCounterAfterMarking = 0;
                mark = index;
                marked = true;
            }

            @Override
            public synchronized void reset() throws IOException {
                if (!marked)
                    throw new IOException("InputStream not marked");
                if (readingLimitAfterMarking != 0 && readingCounterAfterMarking > readingLimitAfterMarking)
                    throw new IOException("Reached reading limit");
                index = mark;
            }

            @Override
            public int read() throws IOException {
                if (index == data.length)
                    return -1;
                readingCounterAfterMarking++;
                return data[index++];
            }

            @Override
            public int available() throws IOException {
                return data.length - index;
            }
        };
    }

    @Test
    public void testIS() throws IOException {
        InputStream inputStream = newInputStream();

        byte[] out = new byte[3];
        int count = inputStream.read(out);
        assertEquals("[1, 2, 3]", Arrays.toString(out));
        assertEquals(3, count);

        long skipCount = inputStream.skip(5);
        assertEquals(5, skipCount);

        out = new byte[5];
        count = inputStream.read(out);
        assertEquals("[9, 10, 0, 0, 0]", Arrays.toString(out));
        assertEquals(2, count);
    }

    @Test
    public void testISOffset() throws IOException {
        InputStream inputStream = newInputStream();

        byte[] out = new byte[10];
        inputStream.read(out, 3, 3);
        assertEquals("[0, 0, 0, 1, 2, 3, 0, 0, 0, 0]", Arrays.toString(out));
        Arrays.fill(out, (byte) 123);
        inputStream.read(out, 3, 3);
        assertEquals("[123, 123, 123, 4, 5, 6, 123, 123, 123, 123]", Arrays.toString(out));

        assertEquals(4, inputStream.available());
    }

    @Test
    public void testMarkedIS() throws IOException {
        InputStream inputStream = newMarkedInputStream();

        byte[] out = new byte[3];
        byte[] out2 = new byte[3];
        inputStream.read(out);
        inputStream.mark(0);
        inputStream.read(out);
        inputStream.read(out2);
        assertNotEquals(Arrays.toString(out), Arrays.toString(out2));
        inputStream.reset();
        inputStream.read(out2);
        assertEquals(Arrays.toString(out), Arrays.toString(out2));
    }

    @Test
    public void testMarkedISAvailable() throws IOException {
        InputStream inputStream = newMarkedInputStream();

        inputStream.read(new byte[5]);
        inputStream.mark(0);
        inputStream.read(new byte[4]);

        assertEquals(1, inputStream.available());
        inputStream.reset();
        assertEquals(5, inputStream.available());
    }

    @Test(expected = IOException.class)
    public void testMarkedISLimit() throws IOException {
        InputStream inputStream = newMarkedInputStream();

        inputStream.mark(1);
        inputStream.read(new byte[5]);
        assertEquals(5, inputStream.available());
        inputStream.reset();
    }
}
