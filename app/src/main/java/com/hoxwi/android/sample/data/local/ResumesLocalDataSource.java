package com.hoxwi.android.sample.data.local;

import android.support.annotation.NonNull;

import com.hoxwi.android.sample.data.Resume;
import com.hoxwi.android.sample.data.IResumesDataSource;

/**
 * Created by Cezar on 24/11/2017.
 */

public class ResumesLocalDataSource implements IResumesDataSource {

    private static ResumesLocalDataSource INSTANCE = null;

    private ResumesLocalDataSource(){
    }

    public static ResumesLocalDataSource getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new ResumesLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void loadResumes(@NonNull LoadResumesCallback loadResumesCallback) {
        // In this sample we don't really have a local data source but you can implement one.
    }

    @Override
    public void getResume(@NonNull String id, @NonNull GetResumeCallback getResumeCallback) {
        // In this sample we don't really have a local data source but you can implement one.
    }

    @Override
    public void addResume(@NonNull Resume resume, @NonNull AddResumeCallback addResumeCallback) {
        // In this sample we don't really have a local data source but you can implement one.
    }

    @Override
    public void deleteResume(@NonNull String id, @NonNull DeleteResumeCallback deleteResumeCallback) {
        // In this sample we don't really have a local data source but you can implement one.
    }
}
