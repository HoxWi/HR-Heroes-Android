package com.hoxwi.android.sample.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Cezar on 24/11/2017.
 */

public interface IResumesDataSource {

    interface LoadResumesCallback {

        void onResumesLoaded(List<Resume> resumes);

        void onDataNotAvailable();
    }

    interface GetResumeCallback {

        void onResumeLoaded(Resume resume);

        void onDataNotAvailable();
    }

    interface AddResumeCallback {

        void onResumeAdded(Resume resume);

        void onError();
    }

    interface DeleteResumeCallback {

        void onResumeDeleted();

        void onError();
    }

    void loadResumes(@NonNull LoadResumesCallback loadResumesCallback);

    void getResume(@NonNull String id, @NonNull GetResumeCallback getResumeCallback);

    void addResume(@NonNull Resume resume, @NonNull AddResumeCallback addResumeCallback);

    void deleteResume(@NonNull String id, @NonNull DeleteResumeCallback deleteResumeCallback);

}
