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
                    Comment comment = new Comment("109", postId, "17T02:52:06.003Z",
                            "Zaneta", "https://s3.amazonaws.com/uifaces/faces/twitter/anjhero/128.jpg",
                            com);
                    List<Comment> comments11 = new ArrayList<>();
                    comments11.add(comment);
                    Post post = new Post(null, null, comments11);

                    Call<Post> postCall = GService.service.putPost(postId, post);
                    postCall.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (!response.isSuccessful())
                                return;
                            Post post = response.body();
                            String p;
                            String full="";
                            for(int i=0; i<post.getComments().size(); i++) {
                                p = post.getComments().get(i).getComment();
                                full+=" ";
                                full+=p;
                            }
                            String str=post.getCreatedAt()+" "+post.getUserName()+" "+full;
                            Log.d("mission", str);
                        }
                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                            Log.d("failure", t.getMessage());
                        }
                    });
                }
            }
        });
    }
}