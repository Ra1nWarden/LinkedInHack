package com.teamsnapblock.snapblock;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zihao on 7/17/15.
 */
public class StoryCommentsAdapter extends RecyclerView.Adapter<StoryCommentsAdapter.CommentViewHolder> {

    List<String> questions;
    List<Integer> votes;

    public StoryCommentsAdapter() {
        questions = new ArrayList<>();
        votes = new ArrayList<>();
    }

    public void addItem(String question) {
        questions.add(question);
        votes.add(0);
        notifyDataSetChanged();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.story_comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.counterView.setText(Integer.toString(votes.get(position)));
        holder.questionView.setText(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "CommentViewHolder";
        ImageView upArrow;
        ImageView downArrow;
        TextView counterView;
        TextView questionView;

        public CommentViewHolder(RelativeLayout itemView) {
            super(itemView);
            upArrow = (ImageView) itemView.findViewById(R.id.up_arrow);
            upArrow.setClickable(true);
            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getPosition();
                    votes.set(pos, votes.get(pos) + 1);
                    Log.d(TAG, "pos is " + pos);
                    notifyDataSetChanged();
                }
            });
            downArrow = (ImageView) itemView.findViewById(R.id.down_arrow);
            downArrow.setClickable(true);
            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getPosition();
                    votes.set(pos, votes.get(pos) - 1);
                    Log.d(TAG, "pos is " + pos);
                    notifyDataSetChanged();
                }
            });
            counterView = (TextView) itemView.findViewById(R.id.counter);
            questionView = (TextView) itemView.findViewById(R.id.question);
        }
    }
}
