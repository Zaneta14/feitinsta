package com.example.feitinsta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>{

    private List<Comment> nCommentsData;
    private Context nContext;

    CommentsAdapter(Context context, List<Comment> commentsData) {
        this.nCommentsData = commentsData;
        this.nContext = context;
    }

    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsAdapter.CommentsViewHolder(nContext, LayoutInflater.from(nContext).
                inflate(R.layout.list_comments, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.CommentsViewHolder holder, int position) {
        holder.nUsernameText.setText(nCommentsData.get(position).getUserName());
        holder.nCreatedAt.setText(nCommentsData.get(position).getCreatedAt());
        String Avatar_url = nCommentsData.get(position).getUserAvatar();
        holder.nComment.setText(nCommentsData.get(position).getComment());

        Picasso.with(nContext)
                .load(Avatar_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.nAvatarImage);
    }

    @Override
    public int getItemCount() {
        return nCommentsData.size();
    }

    static class CommentsViewHolder extends RecyclerView.ViewHolder {

        private TextView nUsernameText;
        private ImageView nAvatarImage;
        private TextView nCreatedAt;
        protected TextView nComment;
        protected Context nContext;
        //private Comment nCurrentComment;
        protected GradientDrawable mGradientDrawable;

        CommentsViewHolder(Context context, View itemView) {
            super(itemView);
            nUsernameText = (TextView)itemView.findViewById(R.id.usernamee);
            nAvatarImage = (ImageView)itemView.findViewById(R.id.avatarr);
            nComment = (TextView) itemView.findViewById(R.id.comm);
            nCreatedAt = (TextView)itemView.findViewById(R.id.createdat);
            nContext = context;
        }
    }
}
