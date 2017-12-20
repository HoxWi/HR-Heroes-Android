package com.hoxwi.android.sample.addresume;

import com.hoxwi.android.sample.BasePresenter;
import com.hoxwi.android.sample.BaseView;

/**
 * Created by Cezar on 6/12/2017.
 */

public class AddResumeContract {

    interface View extends BaseView<Presenter> {
        void showSaveError(String msg);
        void showResumesList();
    }

    interface Presenter extends BasePresenter {
        void saveResume(String name, String jobTitle, String bio);
    }
}
