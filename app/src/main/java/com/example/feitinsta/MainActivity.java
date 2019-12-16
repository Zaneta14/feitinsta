package com.example.feitinsta;

import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/***
 * Main Activity for the Material Me app, a mock sports news application
 */
public class MainActivity extends AppCompatActivity {

    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<Post> mPostsData;
    private PostsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPostsData = new ArrayList<>();

        mAdapter = new PostsAdapter(this, mPostsData);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();
    }

    private void initializeData() {
        String[] descs = getResources().getStringArray(R.array.descriptions);

        TypedArray postsImageResources = getResources().obtainTypedArray(R.array.posts_images);
        TypedArray profilePics = getResources().obtainTypedArray(R.array.icons);
        TypedArray commentProfilePics = getResources().obtainTypedArray(R.array.commentIcons);

        int[] likes=new int[] {212, 156, 310, 192};
        String[] dates=new String[] {"December 15", "December 3", "November 16", "October 18"};

        User[] mainUsers=new User[] {
                new User("shiba_inu", profilePics.getResourceId(0, 0)),
                new User("golden14", profilePics.getResourceId(1, 0)),
                new User("_germanshepherd_", profilePics.getResourceId(2, 0)),
                new User("rottweiler123", profilePics.getResourceId(3, 0))
        };

        User[] users=new User[] {
                new User("_poodle_22", commentProfilePics.getResourceId(0, 0)),
                new User("puggg195", commentProfilePics.getResourceId(1, 0)),
                new User("shiba_inu", commentProfilePics.getResourceId(2, 0)),
                new User("samoyed___", commentProfilePics.getResourceId(3, 0)),
        };
        Comment[] comments=new Comment[] {
                new Comment(users[0], "Where is this taken? XOXO"),
                new Comment(users[1], "Amazing picture!"),
                new Comment(users[2], "Can\'t wait to see you!"),
                new Comment(users[3], "Good one :)"),
        };
        mPostsData.clear();

        for(int i=0; i<mainUsers.length; i++){
            mPostsData.add(new Post(mainUsers[i], descs[i],
                    postsImageResources.getResourceId(i,0), likes[i], dates[i], comments[i]));
        }

        postsImageResources.recycle();
        profilePics.recycle();

        mAdapter.notifyDataSetChanged();
    }

    public void resetPosts(View view) {
        initializeData();
    }
}
