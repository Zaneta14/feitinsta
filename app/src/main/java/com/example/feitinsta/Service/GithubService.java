package com.example.feitinsta.Service;

import com.example.feitinsta.Comment;
import com.example.feitinsta.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GithubService {

    @POST("posts")
    Call<Post> createPost(@Body Post requestModel);

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    Call<List<Comment>> commentsList(@Path("id") String postId);

    @POST("posts/{id}/comments")
    Call<Comment> createComment(@Path("id") String id, @Body Comment comment);

    //@PATCH("posts/{id}")
    //Call<List<Post> patchPost(@Path("id") String id, @Body Post post);
}