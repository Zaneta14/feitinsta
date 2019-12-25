package com.example.feitinsta;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.example.feitinsta.Service.GService;
import com.example.feitinsta.Service.GithubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        GService.initalizeRetrofit();

        /*Post postRequest = new Post("Stefanija Post", 1200);

        Call<Post> getPost = GService.service.createPost(postRequest);
        getPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful())
                    return;
                Post postResponse = response.body();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                return;
            }
        });*/

        Call<List<Post>> posts = GService.service.getPosts();
        posts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful())
                    return;
                List<Post> posts = response.body();
                mRecyclerView.setAdapter(new PostsAdapter(getApplicationContext(), posts));
                Log.d("key", "Number of posts received: " + posts.size());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("failure", t.getMessage());
            }
        });
    }
}