package com.example.lukasz.tumblrbrowser.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lukasz.tumblrbrowser.R;
import com.example.lukasz.tumblrbrowser.adapters.PostAdapter;
import com.example.lukasz.tumblrbrowser.databinding.ActivityMainBinding;
import com.example.lukasz.tumblrbrowser.network.ApiClient;
import com.example.lukasz.tumblrbrowser.viewmodels.MainActivityViewModel;
import com.example.lukasz.tumblrbrowser.viewmodels.interfaces.IMainActivityAccess;

public class MainActivity extends AppCompatActivity implements IMainActivityAccess {

    private ActivityMainBinding mBinding;
    private MainActivityViewModel mViewModel;

    private PostAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        mViewModel = new MainActivityViewModel(this, new ApiClient());

        mLayoutManager = new LinearLayoutManager(this);
        mBinding.postsListRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PostAdapter(mViewModel, mViewModel, MainActivity.this);
        mBinding.postsListRecyclerView.setAdapter(mAdapter);

        mBinding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.searchUserPosts(mBinding.searchEditTxt.getText().toString());
            }
        });
    }

    @Override
    public void openPost(String id, String photoCaption, String photoUrl) {
        Intent intent = PostDetailsActivity.createOpenPostDetailsIntent(MainActivity.this, id, photoCaption, photoUrl);
        startActivity(intent);
    }

    @Override
    public void loadPosts() {
        mAdapter.loadPosts();
    }
}
