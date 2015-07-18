package com.teamsnapblock.snapblock;

import android.graphics.Bitmap;
import android.location.Geocoder;
import android.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamsnapblock.snapblock.model.AnswerPic;
import com.teamsnapblock.snapblock.model.AnswerStr;
import com.teamsnapblock.snapblock.model.StoryList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by louyang on 7/18/15.
 */
public class RestClientUser {
    //URL Locations
    public static final String URL_GETSTORY = "returnAllStories.php";
    public static final String URL_UPVOTESTORY = "upvoteStory.php";
    public static final String URL_DOWNVOTESTORY = "downvoteStory.php";
    public static final String URL_UPVOTECOMMENT = "upvoteComment.php";
    public static final String URL_DOWNVOTECOMMENT = "downvoteComment.php";
    public static final String URL_UPLOADPHOTO = "uploadPhoto.php";
    public static final String URL_CLICKCOMMENT = "clickComment.php";
    public static final String URL_LOGIN = "login.php";
    public static final String URL_CREATESTORY = "createStory.php";

    //JSON Fields
    public static final String COMMENTID="commentid";
    public static final String STORYID="storyid";
    public static final String IMAGE="image";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String NAME = "name";

    public static StoryList getStoryList(){
        try {
            String out = RestClient.GET(URL_GETSTORY);
            ObjectMapper objectMapper = new ObjectMapper();
            StoryList storyList = objectMapper.readValue(out,StoryList.class);
            return objectMapper.readValue(out,StoryList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean login(String userName, String password){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",userName);
            jsonObject.put("password",password);
            String output = RestClient.POST(URL_LOGIN, jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void upvoteStory(int storyID){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(STORYID,storyID);
            RestClient.POST(URL_UPVOTESTORY, jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void downvoteStory(int storyID){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(STORYID,storyID);
            RestClient.POST(URL_DOWNVOTESTORY, jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void downvoteComment(int commentID, int storyID){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(STORYID,storyID);
            jsonObject.put(COMMENTID,commentID);
            RestClient.POST(URL_DOWNVOTECOMMENT, jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void upvoteComment(int commentID, int storyID){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(STORYID,storyID);
            jsonObject.put(COMMENTID,commentID);
            RestClient.POST(URL_UPVOTECOMMENT, jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void uploadPhoto(Bitmap image, int commentID, int storyID){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(IMAGE, bitMapToString(image));
            jsonObject.put(STORYID,storyID);
            jsonObject.put(COMMENTID,commentID);
            String tony = RestClient.POST(URL_UPLOADPHOTO, jsonObject);
            System.out.println(tony);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static void createStory(Bitmap image, double longitude, double latitude, String name){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(IMAGE, bitMapToString(image));
            jsonObject.put(LONGITUDE,longitude);
            jsonObject.put(LATITUDE,latitude);
            jsonObject.put(NAME,name);
            String tony = RestClient.POST(URL_CREATESTORY, jsonObject);
            System.out.println(tony);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String clickComment(int commentID, int storyID){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(STORYID,storyID);
            jsonObject.put(COMMENTID,commentID);
            String output = RestClient.POST(URL_CLICKCOMMENT, jsonObject);
            ObjectMapper objectMapper = new ObjectMapper();

            AnswerStr answerStr = objectMapper.readValue(output,AnswerStr.class);
            AnswerPic answerPic = objectMapper.readValue(output,AnswerPic.class);
            //returns AnswerPic url if not null
            if(answerPic!=null)
                return answerPic.getAnswerPic();
            if(answerStr!=null)
                return answerStr.getAnswerStr();
            else return null;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
}