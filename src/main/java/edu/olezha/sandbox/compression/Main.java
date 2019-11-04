package edu.olezha.sandbox.compression;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by oleh on 16.01.18.
 */
@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        String zipFileName = "/resources.zip";
        String tarGzFileName = "/resources.tar.gz";
        File zipFile = new File(Main.class.getResource(zipFileName).getFile());
        File tarGzFile = new File(Main.class.getResource(tarGzFileName).getFile());

        readZip(zipFile);
        readTarGz(tarGzFile);
    }

    private static void readTarGz(File file) throws IOException {
        TarArchiveInputStream tarInput =
                new TarArchiveInputStream(
                        new GzipCompressorInputStream(
                                new FileInputStream(file)));
        TarArchiveEntry tarEntry = tarInput.getNextTarEntry();
        while (tarEntry != null) {
            log.info("{} {}", tarEntry, tarEntry.getName());
            tarEntry = tarInput.getNextTarEntry();
        }
    }

    private static void readZip(File file) throws IOException {
        try (ZipFile zipFile = new ZipFile(file)) {
            for (Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
                 enumeration.hasMoreElements(); ) {
                ZipEntry zipEntry = enumeration.nextElement();
                log.info("Entry: {}", zipEntry);

                if (!zipEntry.isDirectory()) {
                    try (BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    zipFile.getInputStream(zipEntry)))) {
                        log.info("    {}", bufferedReader.readLine());
                    }
                }
            }

            zipFile.stream().forEach(s -> log.info("{}", s));
        }
    }
}
