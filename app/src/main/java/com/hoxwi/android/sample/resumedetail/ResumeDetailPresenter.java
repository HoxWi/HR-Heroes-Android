package com.hoxwi.android.sample.resumedetail;

import android.support.annotation.NonNull;

import com.hoxwi.android.sample.data.IResumesDataSource;
import com.hoxwi.android.sample.data.Resume;
import com.hoxwi.android.sample.data.ResumesRepository;

/**
 * Created by Cezar on 6/12/2017.
 */

public class ResumeDetailPresenter implements ResumeDetailContract.Presenter{

    private ResumesRepository mRepository;
    private ResumeDetailContract.View mView;
    private String mResumeId;

    public ResumeDetailPresenter(@NonNull ResumesRepository mRepository, @NonNull ResumeDetailContract.View mView, @NonNull String resumeId) {
        this.mRepository = mRepository;
        this.mView = mView;
        this.mResumeId = resumeId;

        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        mRepository.getResume(mResumeId, new IResumesDataSource.GetResumeCallback() {

            @Override
            public void onResumeLoaded(Resume resume) {
                mView.showResume(resume.getName(), resume.getJobTitle(), resume.getBio());
            }

            @Override
            public void onDataNotAvailable() {
                mView.showMessage("Error while fetching the resume.");
            }
        });
    }

    @Override
    public void deleteResume() {
        mRepository.deleteResume(mResumeId, new IResumesDataSource.DeleteResumeCallback() {
            @Override
            public void onResumeDeleted() {
                mView.showResumesList();
            }

            @Override
            public void onError() {
                mView.showMessage("Error while deleting the resume.");
            }
        });
    }
}
