package com.hoxwi.android.sample.addresume;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.hoxwi.android.sample.R;

public class AddResumeFragment extends Fragment implements AddResumeContract.View {

    private AddResumeContract.Presenter mPresenter;

    public AddResumeFragment() {
        // Required empty public constructor
    }

    public static AddResumeFragment newInstance() {
        AddResumeFragment fragment = new AddResumeFragment();
        return fragment;
    }

    @Override
    public void setPresenter(AddResumeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_add_resume, container, false);

        Button okButton = root.findViewById(R.id.addResumeOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEditText = root.findViewById(R.id.addResumeNameEditText);
                EditText jobTitleEditText = root.findViewById(R.id.addResumeJobTitleEditText);
                EditText bioEditText = root.findViewById(R.id.addResumeBioEditText);

                mPresenter.saveResume(nameEditText.getText().toString(),
                        jobTitleEditText.getText().toString(),
                        bioEditText.getText().toString());
            }
        });

        return root;
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSaveError(String msg) {
        Snackbar.make(getView(), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showResumesList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

}
