package com.example.feitinsta;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView username = (TextView)findViewById(R.id.usernamee);
        ImageView avatar = (ImageView)findViewById(R.id.avatarr);
        TextView comment=(TextView) findViewById(R.id.comm);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.GRAY);

        String kom=getIntent().getStringExtra(Post.COMMENT_KEY);

        String kor=getIntent().getStringExtra(Post.USER_KEY);

        Drawable drawable = ContextCompat.getDrawable
                (this,getIntent().getIntExtra(Post.THUMBNAIL_KEY, 0));

        //String[] userNames=getResources().getStringArray(R.array.usernames);

        TypedArray profilePics = getResources().obtainTypedArray(R.array.icons);

        comment.setText(kom);
        username.setText(kor);
        Glide.with(this).load(getIntent().getIntExtra(Post.THUMBNAIL_KEY,0))
                .placeholder(gradientDrawable).into(avatar);
    }
}
