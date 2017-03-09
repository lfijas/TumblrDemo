package com.example.lukasz.tumblrbrowser.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lukasz.tumblrbrowser.BR;
import com.example.lukasz.tumblrbrowser.R;
import com.example.lukasz.tumblrbrowser.network.jackson.PostJson;

import java.util.ArrayList;

/**
 * Created by lukasz on 05.02.17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<PostJson> mDataset;
    private IPostClickListener mPostClickListener;
    private IPostProvider mPostProvider;

    public PostAdapter(IPostClickListener postClickListener, IPostProvider postProvider) {
        mDataset = new ArrayList<>();
        mPostClickListener = postClickListener;
        mPostProvider = postProvider;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PostJson post = mDataset.get(position);
        holder.getBinding().setVariable(BR.post, post);
        holder.getBinding().executePendingBindings();
        holder.getBinding().getRoot().setOnClickListener(new OnPostClickedListener(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
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
