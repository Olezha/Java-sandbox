package edu.olezha.sandbox.core.io;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TextFile {

    public static void main(String[] args) throws URISyntaxException, IOException {
        String fileName = "/file.txt";
        log.info("{}", readFile(fileName));
        log.info("{}", Files.readAllLines(Paths.get(TextFile.class.getResource(fileName).toURI())));
    }

    static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader =
                     new BufferedReader(
                             new FileReader(
                                     TextFile.class.getResource(fileName).getFile()))) {
            String line;
            while ( (line = bufferedReader.readLine()) != null)
                lines.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
