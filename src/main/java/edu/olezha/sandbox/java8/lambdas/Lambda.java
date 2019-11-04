package edu.olezha.sandbox.java8.lambdas;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by oleh on 20.12.17.
 */
@Slf4j
public class Lambda {

    public static void main(String[] args) {
        File path = new File("/etc/");

        File[] files1 = path.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile();
            }
        });

        File[] files2 = path.listFiles( (file) -> file.isFile());

        for (int i = 0; i < files1.length; i++) {
            File file1 = files1[i];

            File file2 = null;
            if (i < files2.length)
                file2 = files2[i];

            log.info("{} {}", file1.equals(file2), file1);
        }


        List<String> list = new ArrayList<>();
        for (int i = 10; i > 0; i--)
            list.add("str" + String.format("%02d", i));
        log.info("{}", list);

        Collections.sort(list);
        log.info("{}", list);

        Random random = new Random();
        Collections.sort(list, (o1, o2) -> {
            int plusMinus = random.nextBoolean() ? 1 : -1;
            return random.nextBoolean() ? plusMinus : 0;
        });
        log.info("{}", list);
    }
}
