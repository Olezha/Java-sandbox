package edu.olezha.sandbox.core.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteFileLine {

    public static void main(String[] args) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                new File("file")), StandardCharsets.UTF_8))) {
            bufferedWriter.write("string");
        }
    }
}
