package com.example.lukasz.tumblrbrowser.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;

import com.example.lukasz.tumblrbrowser.R;
import com.example.lukasz.tumblrbrowser.databinding.ActivityPostDetailsBinding;
import com.example.lukasz.tumblrbrowser.viewmodels.PostDetailsActivityViewModel;
import com.example.lukasz.tumblrbrowser.viewmodels.interfaces.IPostDetailsActivityAccess;
import com.squareup.picasso.Picasso;

public class PostDetailsActivity extends AppCompatActivity implements IPostDetailsActivityAccess{

    public static final String POST_ID = "post_id";
    public static final String POST_PHOTO_CAPTION = "post_photo_caption";
    public static final String POST_PHOTO_URL = "post_photo_url";

    private PostDetailsActivityViewModel mViewModel;

    private ActivityPostDetailsBinding mBinding;

    public static Intent createOpenPostDetailsIntent(Context context, String postId, String photoCaption, String photoUrl) {
        Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(POST_ID, postId);
        intent.putExtra(POST_PHOTO_CAPTION, photoCaption);
        intent.putExtra(POST_PHOTO_URL, photoUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(PostDetailsActivity.this, R.layout.activity_post_details);

        Intent intent = getIntent();
        String photoCaption = intent.getStringExtra(POST_PHOTO_CAPTION);
        String photoUrl = intent.getStringExtra(POST_PHOTO_URL);

        mBinding.captionTxtView.setText(Html.fromHtml(photoCaption));
        Picasso.with(PostDetailsActivity.this).load(photoUrl).into(mBinding.photoImgView);

        mViewModel = new PostDetailsActivityViewModel(this);
    }

}
