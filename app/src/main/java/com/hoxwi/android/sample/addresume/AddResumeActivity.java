package com.hoxwi.android.sample.addresume;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hoxwi.android.sample.R;
import com.hoxwi.android.sample.util.Injection;

public class AddResumeActivity extends AppCompatActivity {

    private AddResumeContract.Presenter mAddResumePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resume);

        AddResumeFragment addResumeFragment =
                (AddResumeFragment) getSupportFragmentManager().findFragmentById(R.id.addResumeContentFrame);
        if (addResumeFragment == null) {
            // Create the fragment
            addResumeFragment = AddResumeFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.addResumeContentFrame, addResumeFragment).commit();
        }

        // Create the presenter
        mAddResumePresenter = new AddResumePresenter(Injection.provideResumesRepository(), addResumeFragment);
    }
}
