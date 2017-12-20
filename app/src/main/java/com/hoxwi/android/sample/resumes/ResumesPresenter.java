package com.hoxwi.android.sample.resumes;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.hoxwi.android.sample.constants.Messages;
import com.hoxwi.android.sample.data.Resume;
import com.hoxwi.android.sample.data.IResumesDataSource;
import com.hoxwi.android.sample.data.ResumesRepository;

import java.util.List;

/**
 * Created by Cezar on 22/11/2017.
 */

public class ResumesPresenter implements ResumesContract.Presenter {

    private final ResumesRepository mRepository;

    private final ResumesContract.View mView;

    public ResumesPresenter(@NonNull ResumesRepository mResumesRepository, @NonNull ResumesContract.View mResumesView) {
        this.mRepository = mResumesRepository;
        this.mView = mResumesView;

        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadResumes();
    }

    @Override
    public void loadResumes() {
        mView.setLoadingIndicator(true);

        mRepository.loadResumes(new IResumesDataSource.LoadResumesCallback() {
            @Override
            public void onResumesLoaded(List<Resume> resumes) {
                if(mView.isActive()){
                    mView.showResumes(resumes);
                    mView.setLoadingIndicator(false);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if(mView.isActive()){
                    mView.showMessage(Messages.ERROR_LOADING_RESUMES);
                    mView.setLoadingIndicator(false);
                }
            }
        });

    }

    @Override
    public void addNewResume() {
        mView.showAddResume();
    }

    @Override
    public void deleteResume(final int position, Resume resume) {
        mRepository.deleteResume(resume.getId(), new IResumesDataSource.DeleteResumeCallback() {
            @Override
            public void onResumeDeleted() {
                mView.remove(position);
            }

            @Override
            public void onError() {
                mView.showMessage("An error occurred.");
            }
        });

    }

    @Override
    public void openResumeDetails(@NonNull Resume requestedResume) {
        mView.showResumeDetails(requestedResume.getId());
    }
}
