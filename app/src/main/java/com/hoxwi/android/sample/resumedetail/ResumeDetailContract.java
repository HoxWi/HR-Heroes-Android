package com.hoxwi.android.sample.resumedetail;

import com.hoxwi.android.sample.BasePresenter;
import com.hoxwi.android.sample.BaseView;

/**
 * Created by Cezar on 6/12/2017.
 */

public interface ResumeDetailContract {

    interface View extends BaseView <Presenter> {
        void showResumesList();
        void showResume(String name, String jobTitle, String bio);
    }

    interface Presenter extends BasePresenter {
        void deleteResume();
    }
}
