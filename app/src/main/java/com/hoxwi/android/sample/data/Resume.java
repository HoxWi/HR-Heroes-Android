package com.hoxwi.android.sample.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cezar on 22/11/2017.
 */

public class Resume {

    @SerializedName("_id")
    private String id;

    private String name;
    private String jobTitle;
    private String bio;

    public Resume() {
    }

    public Resume(String name, String jobTitle, String bio) {
        this(null, name, jobTitle, bio);
    }

    public Resume(String id, String name, String jobTitle, String bio) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
