package edu.olezha.sandbox.http;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ClientTest {

    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Test
    public void skipRequest() {
        HttpClient httpClient = spy(OkHttpClient.class);

        RequestBody requestBody = RequestBody.create(JSON, "");
        RequestBody requestBody2 = RequestBody.create(JSON, "");
        httpClient.post("https://init", requestBody);
        httpClient.post("https://event", requestBody);

        verify(httpClient, times(1)).post("https://event", requestBody);
    }
}
