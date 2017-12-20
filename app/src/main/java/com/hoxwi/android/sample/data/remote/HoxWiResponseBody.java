package com.hoxwi.android.sample.data.remote;

import com.hoxwi.android.sample.data.Resume;

import java.util.List;

/**
 * Created by Cezar on 19/12/2017.
 */

public class HoxWiResponseBody {

    private Boolean success;
    private String header;
    private String result;
    private String error;
    private String message;
    private List<Resume> results;

    public HoxWiResponseBody() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Resume> getResults() {
        return results;
    }

    public void setResults(List<Resume> results) {
        this.results = results;
    }
}
