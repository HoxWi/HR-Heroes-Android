package com.hoxwi.android.sample.resumedetail;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoxwi.android.sample.R;

public class ResumeDetailFragment extends Fragment implements ResumeDetailContract.View {

    private ResumeDetailContract.Presenter mPresenter;

    public ResumeDetailFragment() {
        // Required empty public constructor
    }

    public static ResumeDetailFragment newInstance() {
        ResumeDetailFragment fragment = new ResumeDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_resume_detail, container, false);
        ImageView resumeDetailDeleteImageView = root.findViewById(R.id.resumeDetailDeleteImageView);
        resumeDetailDeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.deleteResume();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(ResumeDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showResumesList() {
        getActivity().finish();
    }

    @Override
    public void showResume(String name, String jobTitle, String bio) {
        TextView resumeDetailNameTextView = getView().findViewById(R.id.resumeDetailNameTextView);
        TextView resumeDetailJobTitleTextView = getView().findViewById(R.id.resumeDetailJobTitleTextView);
        TextView resumeDetailBioTextView = getView().findViewById(R.id.resumeDetailBioTextView);

        resumeDetailNameTextView.setText(name);
        resumeDetailJobTitleTextView.setText(jobTitle);
        resumeDetailBioTextView.setText(bio);
    }
}
