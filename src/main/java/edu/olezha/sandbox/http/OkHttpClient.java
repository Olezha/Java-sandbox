package edu.olezha.sandbox.http;

import com.google.common.util.concurrent.ListenableFuture;
import okhttp3.RequestBody;

public class OkHttpClient implements HttpClient {

    @Override
    public ListenableFuture<String> post(String url, RequestBody requestBody) {
        return null;
    }
}
