package com.teamsnapblock.snapblock.model;

/**
 * Created by louyang on 7/18/15.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "storyid",
        "timestamp",
        "upvote",
        "downvote",
        "latitude",
        "longitude",
        "urls",
        "comments"
})
public class Story {

    @JsonProperty("name")
    private String name;
    @JsonProperty("storyid")
    private String storyid;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("upvote")
    private String upvote;
    @JsonProperty("downvote")
    private String downvote;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("urls")
    private List<Object> urls = new ArrayList<Object>();
    @JsonProperty("comments")
    private List<Object> comments = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The storyid
     */
    @JsonProperty("storyid")
    public String getStoryid() {
        return storyid;
    }

    /**
     *
     * @param storyid
     * The storyid
     */
    @JsonProperty("storyid")
    public void setStoryid(String storyid) {
        this.storyid = storyid;
    }

    /**
     *
     * @return
     * The timestamp
     */
    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     * The timestamp
     */
    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     * The upvote
     */
    @JsonProperty("upvote")
    public String getUpvote() {
        return upvote;
    }

    /**
     *
     * @param upvote
     * The upvote
     */
    @JsonProperty("upvote")
    public void setUpvote(String upvote) {
        this.upvote = upvote;
    }

    /**
     *
     * @return
     * The downvote
     */
    @JsonProperty("downvote")
    public String getDownvote() {
        return downvote;
    }

    /**
     *
     * @param downvote
     * The downvote
     */
    @JsonProperty("downvote")
    public void setDownvote(String downvote) {
        this.downvote = downvote;
    }

    /**
     *
     * @return
     * The latitude
     */
    @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The urls
     */
    @JsonProperty("urls")
    public List<Bitmap> getUrls() {
        List<Bitmap> list = new ArrayList<Bitmap>();
        for(Object url:urls){
            list.add(getBitmapFromURL("http://10.16.21.173/hackthon/"+(String)url));
        }
        return list;
    }

    /**
     *
     * @param urls
     * The urls
     */
    @JsonProperty("urls")
    public void setUrls(List<Object> urls) {
        this.urls = urls;
    }

    /**
     *
     * @return
     * The comments
     */
    @JsonProperty("comments")
    public List<Object> getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    @JsonProperty("comments")
    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            return null;
        }
        return null;
    }

}