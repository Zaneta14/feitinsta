package com.example.feitinsta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private GradientDrawable mGradientDrawable;
    private ArrayList<Post> mPostsData;
    private Context mContext;

    PostsAdapter(Context context, ArrayList<Post> postsData) {
        this.mPostsData = postsData;
        this.mContext = context;

        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);
    }
    @Override
    public PostsAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostsAdapter.PostsViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false), mGradientDrawable);
    }

    @Override
    public void onBindViewHolder(PostsAdapter.PostsViewHolder holder, int position) {

        Post currentPost = mPostsData.get(position);

        holder.bindTo(currentPost);
    }

    @Override
    public int getItemCount() {
        return mPostsData.size();
    }

    static class PostsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mUsernameText;
        private TextView mDescriptionText;
        private ImageView mPostsImage;
        private ImageView mAvatarImage;
        private TextView mNumOfLikes;
        private TextView mDate;
        private Context mContext;
        private Post mCurrentPost;
        private GradientDrawable mGradientDrawable;

        PostsViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);

            //Initialize the views
            mUsernameText = (TextView)itemView.findViewById(R.id.username);
            mDescriptionText = (TextView)itemView.findViewById(R.id.description);
            mPostsImage = (ImageView)itemView.findViewById(R.id.postsImage);
            mAvatarImage = (ImageView)itemView.findViewById(R.id.avatar);
            mNumOfLikes = (TextView)itemView.findViewById(R.id.numoflikes);
            mDate = (TextView)itemView.findViewById(R.id.date);
            mContext = context;
            mGradientDrawable = gradientDrawable;

            //Set the OnClickListener to the whole view
            itemView.setOnClickListener(this);
        }

        void bindTo(Post currentPost){
            //Populate the textviews with data
            mUsernameText.setText(currentPost.getUser().getUsername());
            mDescriptionText.setText(currentPost.getDescription());
            mNumOfLikes.setText(String.valueOf(currentPost.getNumberOfLikes()));
            mDate.setText(currentPost.getDate());
            //Get the current sport
            mCurrentPost = currentPost;

            //Load the images into the ImageView using the Glide library
            Glide.with(mContext).load(currentPost.
                    getImageResource()).placeholder(mGradientDrawable).into(mPostsImage);

            Glide.with(mContext).load(currentPost.getUser().
                    getImageRes()).placeholder(mGradientDrawable).into(mAvatarImage);

        }

        @Override
        public void onClick(View view) {

            //Set up the detail intent
            Intent detailIntent = Post.starter(mContext, mCurrentPost.getComment());

            //Start the detail activity
            mContext.startActivity(detailIntent);
        }
    }
}
