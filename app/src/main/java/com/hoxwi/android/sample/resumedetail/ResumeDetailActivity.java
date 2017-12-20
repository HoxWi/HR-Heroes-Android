package com.hoxwi.android.sample.resumedetail;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hoxwi.android.sample.R;
import com.hoxwi.android.sample.addresume.AddResumeFragment;
import com.hoxwi.android.sample.addresume.AddResumePresenter;
import com.hoxwi.android.sample.util.Injection;

public class ResumeDetailActivity extends AppCompatActivity {

    private ResumeDetailContract.Presenter mPresenter;

    static public final String RESUME_ID_INTENT_KEY = "RESUME_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_detail);

        ResumeDetailFragment resumeDetailFragment =
                (ResumeDetailFragment) getSupportFragmentManager().findFragmentById(R.id.resumeDetailContentFrame);
        if (resumeDetailFragment == null) {
            // Create the fragment
            resumeDetailFragment = ResumeDetailFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.resumeDetailContentFrame, resumeDetailFragment).commit();
        }

        String resumeId = getIntent().getStringExtra(RESUME_ID_INTENT_KEY);
        // Create the presenter
        mPresenter = new ResumeDetailPresenter(Injection.provideResumesRepository(), resumeDetailFragment, resumeId);
    }
}
