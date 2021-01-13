package edu.olezha.sandbox.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Raf {

    public static void main(String[] args) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile("input.txt", "r")) {
            raf.seek(100);
        }
    }
}
