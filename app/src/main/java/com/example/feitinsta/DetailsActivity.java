package com.example.feitinsta;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.feitinsta.Service.GService;
import com.example.feitinsta.Service.GithubService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String postid=getIntent().getStringExtra(Post.POST_KEY);

        final RecyclerView nRecyclerView;
        nRecyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        nRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        GService.initalizeRetrofit();

        Call<List<Comment>> comments= GService.service.commentsList(postid);
        comments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    Log.d("fail", "unsuccessful");
                    return;
                }
                List<Comment> comments1 = response.body();
                nRecyclerView.setAdapter(new CommentsAdapter(getApplicationContext(), comments1));
                Log.d("key", "Number of comments received: " + comments1.size());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("failure1", t.getMessage());
            }
        });

        Button button=(Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postId = getIntent().getStringExtra(Post.POST_KEY);
                EditText editText = (EditText) findViewById(R.id.edittext);
                String com;
                if (editText.getText().toString().length() != 0) {
                    com = editText.getText().toString();
                    Comment comment = new Comment(postId, "17T02:52:06.003Z",
                            "Zaneta", "https://s3.amazonaws.com/uifaces/faces/twitter/anjhero/128.jpg",
                            com);
                    Call<Comment> commentCall = GService.service.createComment(postId, comment);
                    commentCall.enqueue(new Callback<Comment>() {
                        @Override
                        public void onResponse(Call<Comment> call, Response<Comment> response) {
                            if (!response.isSuccessful())
                                return;
                            Comment k = response.body();
                        }
                        @Override
                        public void onFailure(Call<Comment> call, Throwable t) {
                            Log.d("failure", t.getMessage());
                        }
                    });
                }
            }
        });
    }
}