package edu.olezha.sandbox.core.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;

public class OutOfSpace {

    public static void main(String[] args) throws IOException {
        NumberFormat nf = NumberFormat.getNumberInstance();
        FileStore store = Files.getFileStore(Paths.get(""));
        System.out.println("available=" + nf.format(store.getUsableSpace()));
        System.out.println("total=" + nf.format(store.getTotalSpace()));

        String str = "Hello";
        try (OutputStream outputStream = new FileOutputStream("test")) {
            byte[] strToBytes = str.getBytes();
            try {
                outputStream.write(strToBytes);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
