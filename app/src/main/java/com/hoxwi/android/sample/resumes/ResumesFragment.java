package com.hoxwi.android.sample.resumes;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoxwi.android.sample.R;
import com.hoxwi.android.sample.addresume.AddResumeActivity;
import com.hoxwi.android.sample.components.RecyclerViewEmptySupport;
import com.hoxwi.android.sample.data.Resume;
import com.hoxwi.android.sample.resumedetail.ResumeDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ResumesFragment extends Fragment implements ResumesContract.View, ResumesAdapter.ResumeItemListener {

    private ResumesContract.Presenter mPresenter;
    private ResumesAdapter mAdapter;

    private SwipeRefreshLayout mResumesSwipeRefreshLayout;
    private RecyclerViewEmptySupport mResumesRecyclerView;

    public ResumesFragment() {
    }

    public static ResumesFragment newInstance() {
        return new ResumesFragment();
    }

    @Override
    public void setPresenter(ResumesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_resumes, null);
        mResumesSwipeRefreshLayout = root.findViewById(R.id.resumesSwipeRefreshLayout);

        mResumesRecyclerView = root.findViewById(R.id.resumesRecyclerView);
        mResumesRecyclerView.setHasFixedSize(false);
        mResumesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TextView mNoResultsTextView = root.findViewById(R.id.noResultsTextView);
        mResumesRecyclerView.setEmptyView(mNoResultsTextView);
        mAdapter = new ResumesAdapter(null, this);
        mResumesRecyclerView.setAdapter(mAdapter);

        mResumesSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadResumes();
            }
        });

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_resume);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewResume();
            }
        });

        return root;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl = getView().findViewById(R.id.resumesSwipeRefreshLayout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showResumes(List<Resume> resumes) {
        mAdapter.replaceData(resumes);
    }

    @Override
    public void showAddResume() {
        Intent intent = new Intent(getContext(), AddResumeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showResumeDetails(String resumeId) {
        Intent intent = new Intent(getContext(), ResumeDetailActivity.class);
        intent.putExtra(ResumeDetailActivity.RESUME_ID_INTENT_KEY, resumeId);
        startActivity(intent);
    }

    @Override
    public void remove(int position) {
        mAdapter.removeItem(position);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onResumeClick(Resume clickedResume) {
        mPresenter.openResumeDetails(clickedResume);
    }

    @Override
    public void onDeleteResumeClick(int position, Resume resume) {
        mPresenter.deleteResume(position, resume);
    }
}
