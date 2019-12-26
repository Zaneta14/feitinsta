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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.feitinsta.Post.POST_KEY;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<Post> mPostsData;
    private Context mContext;

    PostsAdapter(Context context, List<Post> postsData) {
        this.mPostsData = postsData;
        this.mContext = context;
    }

    @Override
    public PostsAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostsAdapter.PostsViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PostsAdapter.PostsViewHolder holder, int position) {
        holder.mUsernameText.setText(mPostsData.get(position).getUserName());
        holder.mCreatedAt.setText(mPostsData.get(position).getCreatedAt());
        holder.mNumOfLikes.setText(String.valueOf(mPostsData.get(position).getLikes()));
        String image_url = mPostsData.get(position).getPhoto();
        String avatar_url = mPostsData.get(position).getUserAvatar();

        Picasso.with(mContext)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.mPostsImage);

        Picasso.with(mContext)
                .load(avatar_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.mAvatarImage);
        Post currentPost = mPostsData.get(position);
        holder.mCurrentPost = currentPost;
    }

    @Override
    public int getItemCount() {
        return mPostsData.size();
    }

    static class PostsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mUsernameText;
        private ImageView mPostsImage;
        private ImageView mAvatarImage;
        private TextView mNumOfLikes;
        private TextView mCreatedAt;
        private Context mContext;
        private Post mCurrentPost;

        PostsViewHolder(Context context, View itemView) {
            super(itemView);

            mUsernameText = (TextView)itemView.findViewById(R.id.username);
            mPostsImage = (ImageView)itemView.findViewById(R.id.postsImage);
            mAvatarImage = (ImageView)itemView.findViewById(R.id.avatar);
            mNumOfLikes = (TextView)itemView.findViewById(R.id.numoflikes);
            mCreatedAt = (TextView)itemView.findViewById(R.id.created);
            mContext = context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent detailIntent = new Intent(mContext, DetailsActivity.class);
            detailIntent.putExtra(POST_KEY, mCurrentPost.getId());
            detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(detailIntent);
        }
    }
}