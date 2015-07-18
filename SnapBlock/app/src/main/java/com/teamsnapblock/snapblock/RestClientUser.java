package com.teamsnapblock.snapblock;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.teamsnapblock.snapblock.model.StoryList;

import org.apache.http.Header;
import org.json.JSONArray;

/**
 * Created by louyang on 7/18/15.
 */
public class RestClientUser {
    public static final String URL_GETSTORY = "returnAllStories.php";

    public static StoryList getStoryList(){
        JsonHttpResponseHandler myHandler = new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

        };
        RestClient.get(URL_GETSTORY,new RequestParams(),myHandler);
        return null;
    }
}
