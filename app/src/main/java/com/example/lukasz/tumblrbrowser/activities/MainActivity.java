package com.example.lukasz.tumblrbrowser.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

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
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        mViewModel = new MainActivityViewModel(this, new ApiClient());

        mLayoutManager = new LinearLayoutManager(this);
        mBinding.postsListRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mBinding.postsListRecyclerView.getContext(), mLayoutManager.getOrientation());
        mBinding.postsListRecyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter = new PostAdapter(mViewModel, mViewModel, MainActivity.this);
        mBinding.postsListRecyclerView.setAdapter(mAdapter);

        mBinding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.searchUserPosts(mBinding.searchEditTxt.getText().toString());
            }
        });

        mBinding.searchEditTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    mViewModel.searchUserPosts(mBinding.searchEditTxt.getText().toString());
                }
                return false;
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

    @Override
    public void setSearchingInProgress(boolean isInProgress) {
        if (isInProgress) {
            mBinding.searchingProgressLayout.setVisibility(View.VISIBLE);
            mBinding.postsListRecyclerView.setVisibility(View.GONE);
        } else {
            mBinding.searchingProgressLayout.setVisibility(View.GONE);
            mBinding.postsListRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNetworkErrorToast() {
        Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
    }


}
