package com.hoxwi.android.sample.resumes;

import android.support.annotation.NonNull;

import com.hoxwi.android.sample.BasePresenter;
import com.hoxwi.android.sample.BaseView;
import com.hoxwi.android.sample.data.Resume;

import java.util.List;

/**
 * Created by Cezar on 22/11/2017.
 */

public class ResumesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showResumes(List<Resume> resumes);

        void showAddResume();

        void remove(int position);

        void showResumeDetails(String resumeId);

    }

    interface Presenter extends BasePresenter {
        void loadResumes();

        void addNewResume();

        void deleteResume(int position, @NonNull Resume resume); //we need 'position' to remove from view and 'resume' to delete in the data source

        void openResumeDetails(@NonNull Resume requestedResume);
    }
}
