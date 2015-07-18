package com.teamsnapblock.snapblock;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zihao on 7/17/15.
 */
public class NewStoryActivity extends Activity {

    private RecyclerView storyPhotosList;
    private RecyclerView.LayoutManager storyPhotosLayoutManager;
    private StoryPhotosAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.story_view);
        storyPhotosList = (RecyclerView) findViewById(R.id.story_photos_list);
        storyPhotosLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        storyPhotosList.setLayoutManager(storyPhotosLayoutManager);
        adapter = new StoryPhotosAdapter();
        storyPhotosList.setAdapter(adapter);
        setUpExamples();
    }

    private void setUpExamples() {
        for(int i = 0; i < 5; i++) {
            adapter.addPhoto(getResources().getDrawable(R.drawable.green_mushroom, null));
        }
    }


}
