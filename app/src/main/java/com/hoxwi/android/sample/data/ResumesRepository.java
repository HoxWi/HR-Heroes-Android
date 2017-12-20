package com.hoxwi.android.sample.data;

import android.support.annotation.NonNull;

import com.hoxwi.android.sample.data.local.ResumesLocalDataSource;
import com.hoxwi.android.sample.data.remote.ResumesRemoteDataSource;

import java.util.List;

/**
 * Created by Cezar on 24/11/2017.
 */

public class ResumesRepository implements IResumesDataSource {

    private static ResumesRepository INSTANCE = null;

    private ResumesLocalDataSource mLocalDataSource;
    private ResumesRemoteDataSource mRemoteDataSource;

    private boolean mCacheIsDirty = true; // Always true since we don't have a local data source implemented.

    private ResumesRepository(@NonNull ResumesLocalDataSource localDataSource, @NonNull ResumesRemoteDataSource remoteDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource = remoteDataSource;
    }

    public static ResumesRepository getInstance(@NonNull ResumesLocalDataSource localDataSource, @NonNull ResumesRemoteDataSource remoteDataSource){
        if(INSTANCE == null) {
            INSTANCE = new ResumesRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void loadResumes(@NonNull final LoadResumesCallback loadResumesCallback) {
        if(mCacheIsDirty){
            mRemoteDataSource.loadResumes(loadResumesCallback);
        } else {
            mLocalDataSource.loadResumes(new LoadResumesCallback() {
                @Override
                public void onResumesLoaded(List<Resume> resumes) {
                    // should refresh the cache right here, e.g. refreshCache(resumes);
                    loadResumesCallback.onResumesLoaded(null /* pass the cached data */);
                }

                @Override
                public void onDataNotAvailable() {
                    mRemoteDataSource.loadResumes(loadResumesCallback);
                }
            });
        }
    }

    @Override
    public void getResume(@NonNull String id, @NonNull GetResumeCallback getResumeCallback) {
        // TODO: do the local/remote thing

        mRemoteDataSource.getResume(id, getResumeCallback);
    }

    @Override
    public void addResume(@NonNull Resume resume, @NonNull AddResumeCallback addResumeCallback) {
        // TODO: do the local/remote thing

        mRemoteDataSource.addResume(resume, addResumeCallback);
    }

    @Override
    public void deleteResume(@NonNull String id, @NonNull DeleteResumeCallback deleteResumeCallback) {
        // TODO: do the local/remote thing

        mRemoteDataSource.deleteResume(id, deleteResumeCallback);
    }
}
