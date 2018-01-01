package com.hoxwi.android.sample.data.remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.PUT;

/**
 * Created by Cezar on 19/12/2017.
 */

public interface HoxWiService {

    String BASE_URL = "https://hoxwi.com/Dynamic/";

    String CONTAINER = "resumes";

    @PUT("Search")
    Call<HoxWiResponseBody> searchResumes(@Body HoxWiRequestBody body);

    @PUT("Add")
    Call<HoxWiResponseBody> addResume(@Body HoxWiRequestBody body);

    @HTTP(method = "DELETE", path = "Delete", hasBody = true)
    Call<HoxWiResponseBody> deleteResume(@Body HoxWiRequestBody body);

}
