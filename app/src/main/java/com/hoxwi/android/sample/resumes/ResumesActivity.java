package com.hoxwi.android.sample.resumes;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.hoxwi.android.sample.R;
import com.hoxwi.android.sample.util.Injection;

public class ResumesActivity extends AppCompatActivity {

    private ResumesPresenter mResumesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ResumesFragment resumesFragment =
                (ResumesFragment) getSupportFragmentManager().findFragmentById(R.id.resumesContentFrame);
        if (resumesFragment == null) {
            // Create the fragment
            resumesFragment = ResumesFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.resumesContentFrame, resumesFragment).commit();
        }

        // Create the presenter
        mResumesPresenter = new ResumesPresenter(Injection.provideResumesRepository(), resumesFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
