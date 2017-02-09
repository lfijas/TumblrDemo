package com.example.lukasz.tumblrbrowser.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lukasz.tumblrbrowser.R;
import com.example.lukasz.tumblrbrowser.network.jackson.PostJson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lukasz on 05.02.17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<PostJson> mDataset;
    private IPostClickListener mPostClickListener;
    private IPostProvider mPostProvider;
    private Context mContext;

    public PostAdapter(IPostClickListener postClickListener, IPostProvider postProvider, Context context) {
        mDataset = new ArrayList<>();
        mPostClickListener = postClickListener;
        mPostProvider = postProvider;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextPostTitle.setText(Html.fromHtml(mDataset.get(position).getPhotoCaption()));
        Picasso.with(mContext).load(mDataset.get(position).getPhotoUrl75()).into(holder.mPostPhoto);
        holder.mPostRow.setOnClickListener(new OnPostClickedListener(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextPostTitle;
        public ImageView mPostPhoto;
        public View mPostRow;
        public ViewHolder(View v) {
            super(v);
            mTextPostTitle = (TextView) v.findViewById(R.id.post_row_title_txt_view);
            mPostPhoto = (ImageView) v.findViewById(R.id.post_row_photo_img_view);
            mPostRow = v;
        }
    }

    public void loadPosts() {
        mDataset = mPostProvider.getPosts();
        notifyDataSetChanged();
    }

    private class OnPostClickedListener implements View.OnClickListener {

        private int mPosition;

        public OnPostClickedListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View view) {
            String id = mDataset.get(mPosition).getId();
            String photoCaption = mDataset.get(mPosition).getPhotoCaption();
            String photoUrl = mDataset.get(mPosition).getPhotoUrl1280();
            mPostClickListener.postClicked(id, photoCaption, photoUrl);
        }
    }
}
