package com.hoxwi.android.sample.resumes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoxwi.android.sample.R;
import com.hoxwi.android.sample.data.Resume;

import java.util.List;

/**
 * Created by Cezar on 29/11/2017.
 */

public class ResumesAdapter extends RecyclerView.Adapter<ResumesAdapter.ViewHolder> {

    private List<Resume> mResumes;
    private ResumeItemListener mItemListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView resumeNameItemTextView;
        public TextView resumeJobTitleItemTextView;
        public ImageView resumeDeleteImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.resumeNameItemTextView = itemView.findViewById(R.id.resumeNameItemTextView);
            this.resumeJobTitleItemTextView = itemView.findViewById(R.id.resumeJobTitleItemTextView);
            this.resumeDeleteImageView = itemView.findViewById(R.id.resumeDeleteImageView);
        }
    }

    public ResumesAdapter(@NonNull List<Resume> resumes, @NonNull ResumeItemListener itemListener) {
        this.mResumes = resumes;
        this.mItemListener = itemListener;
    }

    public void replaceData(List<Resume> resumes) {
        this.mResumes = resumes;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        if(mResumes == null) {
            return;
        }
        mResumes.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mResumes == null ? 0 : mResumes.size();
    }

    @Override
    public ResumesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.resume_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ResumesAdapter.ViewHolder holder, final int position) {
        if (mResumes == null || mResumes.size() == 0) {
            return;
        }

        final Resume r = mResumes.get(position);
        holder.resumeNameItemTextView.setText(r.getName());
        holder.resumeJobTitleItemTextView.setText(r.getJobTitle());

        holder.resumeDeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.onDeleteResumeClick(position, r);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.onResumeClick(r);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface ResumeItemListener {

        void onResumeClick(Resume clickedResume);

        void onDeleteResumeClick(int position, Resume resume);

    }

}
