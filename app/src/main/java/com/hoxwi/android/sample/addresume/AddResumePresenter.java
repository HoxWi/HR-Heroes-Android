package com.hoxwi.android.sample.addresume;

import android.support.annotation.NonNull;

import com.hoxwi.android.sample.constants.Messages;
import com.hoxwi.android.sample.data.IResumesDataSource;
import com.hoxwi.android.sample.data.Resume;
import com.hoxwi.android.sample.data.ResumesRepository;

/**
 * Created by Cezar on 5/12/2017.
 */

public class AddResumePresenter implements AddResumeContract.Presenter {

    private ResumesRepository mRepository;

    private AddResumeContract.View mView;

    public AddResumePresenter(@NonNull ResumesRepository mRepository, @NonNull AddResumeContract.View mView) {
        this.mRepository = mRepository;
        this.mView = mView;

        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveResume(String name, String jobTitle, String bio) {
        String msg = "";
        if("".equals(name)){
            msg += "Name cannot be empty.\n";
        }
        if("".equals(jobTitle)){
            msg += "Job Title cannot be empty.";
        }

        if(!"".equals(msg)){
            mView.showSaveError(msg);
            return;
        }

        mRepository.addResume(new Resume(null, name, jobTitle, bio), new IResumesDataSource.AddResumeCallback() {
            @Override
            public void onResumeAdded(Resume resume) {
                mView.showMessage(Messages.RESUME_SUCCESSFULLY_SAVED);
                mView.showResumesList();
            }

            @Override
            public void onError() {
                mView.showSaveError("An error ocurred.");
            }
        });

    }

}
