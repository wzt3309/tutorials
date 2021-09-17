package io.zeddw.gist.okhttp;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class GetStartTest {
    private static final String BASE_URL = "https://httpbin.org/";
    private final OkHttpClient client = new OkHttpClient();

    @Test
    public void test_get_request() {
        Request request = new Request.Builder()
        .url(BASE_URL + "get")
        .build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                log.info("\n{}", response.headers());
            }
        } catch(IOException e) {
            // ignore
            e.printStackTrace();
        }

    }
}
