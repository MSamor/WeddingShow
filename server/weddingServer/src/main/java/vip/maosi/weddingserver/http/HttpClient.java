package vip.maosi.weddingserver.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.http.HttpLogging;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import vip.maosi.weddingserver.util.JsonUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;

@Slf4j
public class HttpClient {
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    public static final MediaType FORM = MediaType.get("application/x-www-form-urlencoded");

    private static final HttpClient INSTANCE = new HttpClient();

    public final OkHttpClient client = new OkHttpClient.Builder()
            .callTimeout(Duration.ofSeconds(60))
            .build();

    public static HttpClient get() {
        return INSTANCE;
    }

    @Nullable
    public String jsonPost(String url, @NonNull Map<String, ? extends Object> params) throws IOException {
        RequestBody requestBody = RequestBody.create(JsonUtils.toJson(params), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.code() == HttpURLConnection.HTTP_OK) {
                ResponseBody responseBody = response.body();
                return responseBody != null ? responseBody.string() : null;
            }
        }
        return null;
    }

    @Nullable
    public String get(String url) throws IOException {
        return get(url, Collections.emptyMap());
    }

    @Nullable
    public String get(String url, @NonNull Map<String, ? extends Object> params) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.get(url).newBuilder();
        params.forEach((k, v) -> urlBuilder.setQueryParameter(k, v.toString()));
        HttpUrl httpUrl = urlBuilder.build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.code() == HttpURLConnection.HTTP_OK) {
                ResponseBody responseBody = response.body();
                return responseBody != null ? responseBody.string() : null;
            }
        }
        return null;
    }
}
