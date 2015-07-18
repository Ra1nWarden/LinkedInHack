package com.teamsnapblock.snapblock;

import com.teamsnapblock.snapblock.com.teamsnapblock.snapblock.models.StoryList;
import com.loopj.android.http.*;

/**
 * Created by louyang on 7/18/15.
 */
public class RestClient {
    private static final String BASE_URL = "https://api.twitter.com/1/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}