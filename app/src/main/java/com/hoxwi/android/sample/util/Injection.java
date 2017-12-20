package com.hoxwi.android.sample.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hoxwi.android.sample.data.ResumesRepository;
import com.hoxwi.android.sample.data.local.ResumesLocalDataSource;
import com.hoxwi.android.sample.data.remote.ResumesRemoteDataSource;

/**
 * Created by Cezar on 24/11/2017.
 */

public class Injection {

    public static ResumesRepository provideResumesRepository(){
        return ResumesRepository.getInstance(ResumesLocalDataSource.getInstance(), ResumesRemoteDataSource.getInstance());
    }

}
