package com.hoxwi.android.sample.data.remote;

import android.support.annotation.NonNull;

import com.hoxwi.android.sample.BuildConfig;
import com.hoxwi.android.sample.data.Resume;
import com.hoxwi.android.sample.data.IResumesDataSource;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cezar on 24/11/2017.
 */

public class ResumesRemoteDataSource implements IResumesDataSource {

    private static ResumesRemoteDataSource INSTANCE = null;

    private HoxWiService mHoxWiService;

    private ResumesRemoteDataSource(){
        this.mHoxWiService = new Retrofit.Builder()
                .baseUrl(HoxWiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HoxWiService.class);


    }

    public static ResumesRemoteDataSource getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new ResumesRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void loadResumes(@NonNull final LoadResumesCallback loadResumesCallback) {
        HoxWiRequestBody body = buildRequestBody(new HashMap<>());
        Call<HoxWiResponseBody> call = mHoxWiService.searchResumes(body);
        call.enqueue(new Callback<HoxWiResponseBody>() {
            @Override
            public void onResponse(Call<HoxWiResponseBody> call, Response<HoxWiResponseBody> response) {
                loadResumesCallback.onResumesLoaded(response.body().getResults());
            }

            @Override
            public void onFailure(Call<HoxWiResponseBody> call, Throwable t) {
                loadResumesCallback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getResume(@NonNull String id, @NonNull final GetResumeCallback getResumeCallback) {
        Resume document = new Resume();
        document.setId(id);

        HoxWiRequestBody body = buildRequestBody(document);
        Call<HoxWiResponseBody> call = mHoxWiService.searchResumes(body);
        call.enqueue(new Callback<HoxWiResponseBody>() {
            @Override
            public void onResponse(Call<HoxWiResponseBody> call, Response<HoxWiResponseBody> response) {
                List<Resume> results = response.body().getResults();
                if(results == null || results.size() == 0){
                    getResumeCallback.onDataNotAvailable();
                    return;
                }

                getResumeCallback.onResumeLoaded(results.get(0));
            }

            @Override
            public void onFailure(Call<HoxWiResponseBody> call, Throwable t) {
                getResumeCallback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void addResume(@NonNull final Resume resume, @NonNull final AddResumeCallback addResumeCallback) {
        HoxWiRequestBody body = buildRequestBody(resume);
        Call<HoxWiResponseBody> call = mHoxWiService.addResume(body);
        call.enqueue(new Callback<HoxWiResponseBody>() {
            @Override
            public void onResponse(Call<HoxWiResponseBody> call, Response<HoxWiResponseBody> response) {
                HoxWiResponseBody hoxWiResponse = response.body();
                if(! hoxWiResponse.getSuccess()){
                    addResumeCallback.onError();
                    return;
                }
                resume.setId(hoxWiResponse.getResult());
                addResumeCallback.onResumeAdded(resume);
            }

            @Override
            public void onFailure(Call<HoxWiResponseBody> call, Throwable t) {
                addResumeCallback.onError();
            }
        });

    }

    @Override
    public void deleteResume(@NonNull String id, @NonNull final DeleteResumeCallback deleteResumeCallback) {
        Resume document = new Resume();
        document.setId(id);
        HoxWiRequestBody body = buildRequestBody(document);
        Call<HoxWiResponseBody> call = mHoxWiService.deleteResume(body);
        call.enqueue(new Callback<HoxWiResponseBody>() {
            @Override
            public void onResponse(Call<HoxWiResponseBody> call, Response<HoxWiResponseBody> response) {
                HoxWiResponseBody hoxWiResponse = response.body();
                if(! hoxWiResponse.getSuccess()){
                    deleteResumeCallback.onError();
                    return;
                }
                deleteResumeCallback.onResumeDeleted();
            }

            @Override
            public void onFailure(Call<HoxWiResponseBody> call, Throwable t) {
                deleteResumeCallback.onError();
            }
        });

    }

    private HoxWiRequestBody buildRequestBody(Object document){
        HoxWiRequestBody body = new HoxWiRequestBody.Builder()
                .secretKey(BuildConfig.HOX_WI_SECRET_KEY)
                .container(HoxWiService.CONTAINER)
                .document(document)
                .build();

        return body;
    }
}
