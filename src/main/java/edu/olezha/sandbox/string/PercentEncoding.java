package edu.olezha.sandbox.string;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class PercentEncoding {

    public static void main(String[] args) throws UnsupportedEncodingException {
        log.info(URLEncoder.encode("+ABc8D_E", StandardCharsets.UTF_8.name()));
    }
}
