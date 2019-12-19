package com.example.feitinsta;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.DrawableRes;

public class Post {

    private final User user;
    private final String description;
    private final int imageResource;
    private final int numberOfLikes;
    private final String date;
    private final Comment comment;

    static final String COMMENT_KEY = "Comment";
    static final String USER_KEY = "User";
    static final String THUMBNAIL_KEY = "Thumbnail";

    Post(User user, String description, int imageResource, int numberOfLikes, String date, Comment comment) {
        this.user = user;
        this.description = description;
        this.imageResource = imageResource;
        this.numberOfLikes=numberOfLikes;
        this.date=date;
        this.comment=comment;
    }

    User getUser() {
        return user;
    }

    String getDescription() {
        return description;
    }

    int getImageResource() {
        return imageResource;
    }

    int getNumberOfLikes() {return numberOfLikes; }

    String getDate() { return date; }

    Comment getComment() { return comment; }
    /**
     * Method for creating  the Detail Intent
     * @param context Application context, used for launching the Intent
     * @param title The title of the current Sport
     * @param imageResId The image resource associated with the current sport
     * @return The Intent containing the extras about the sport, launches Detail activity
     */

    static Intent starter(Context context, Comment com) {
        Intent detailIntent = new Intent(context, DetailsActivity.class);
        detailIntent.putExtra(COMMENT_KEY, com.getCommentt());
        detailIntent.putExtra(USER_KEY, com.getUser().getUsername());
        detailIntent.putExtra(THUMBNAIL_KEY, com.getUser().getImageRes());
        return detailIntent;
    }
}