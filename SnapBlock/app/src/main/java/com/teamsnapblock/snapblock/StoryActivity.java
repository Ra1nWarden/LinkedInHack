package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zihao on 7/17/15.
 */
public class StoryActivity extends Activity {

    private RecyclerView storyPhotosList;
    private RecyclerView storyCommentsList;
    private RecyclerView.LayoutManager storyPhotosLayoutManager;
    private RecyclerView.LayoutManager storyCommentsLayoutManager;
    private StoryPhotosAdapter photosAdapter;
    private StoryCommentsAdapter commentsAdaapter;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.story_view);
        storyPhotosList = (RecyclerView) findViewById(R.id.story_photos_list);
        storyPhotosLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        storyPhotosList.setLayoutManager(storyPhotosLayoutManager);
        photosAdapter = new StoryPhotosAdapter();
        storyPhotosList.setAdapter(photosAdapter);
        storyCommentsList = (RecyclerView) findViewById(R.id.story_comments_list);
        commentsAdaapter = new StoryCommentsAdapter();
        storyCommentsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        storyCommentsList.setLayoutManager(storyCommentsLayoutManager);
        storyCommentsList.setAdapter(commentsAdaapter);
        setUpExamples();
    }

    private void setUpExamples() {
        for(int i = 0; i < 5; i++) {
            photosAdapter.addPhoto(getResources().getDrawable(R.drawable.green_mushroom, null));
        }
        for(int i = 0; i < 10; i++) {
            commentsAdaapter.addItem("Comment number " + (i+1));
        }
    }


}
