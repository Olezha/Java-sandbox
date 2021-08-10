package edu.olezha.sandbox.http;

import okhttp3.MediaType;
import okio.BufferedSink;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class RequestBody extends okhttp3.RequestBody {

    public RequestBody(okhttp3.RequestBody requestBody) {}

    @Override
    public boolean equals(Object o) {
        return o instanceof RequestBody;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
    }
}
