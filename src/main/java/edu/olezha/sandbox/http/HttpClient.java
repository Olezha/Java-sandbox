package edu.olezha.sandbox.http;

import com.google.common.util.concurrent.ListenableFuture;
import okhttp3.RequestBody;

public interface HttpClient {

    ListenableFuture<String> post(String url, RequestBody requestBody);
}
