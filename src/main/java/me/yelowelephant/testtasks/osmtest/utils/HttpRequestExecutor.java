package me.yelowelephant.testtasks.osmtest.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;


public class HttpRequestExecutor {
    private HttpClientBuilder httpClientBuilder;

    public HttpResponse execute(HttpUriRequest request) throws IOException {
        return httpClientBuilder.create()
                .build()
                .execute(request);
    }

    public HttpResponse executeNoRedirect(HttpUriRequest request) throws IOException {
        return httpClientBuilder.create()
                .disableRedirectHandling()
                .build()
                .execute(request);
    }
}