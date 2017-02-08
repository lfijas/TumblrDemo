package com.example.lukasz.tumblrbrowser.network.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lukasz on 07.02.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostJson {

    String id;
    String photoCaption;
    String photoUrl1280;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty(value = "photo-caption")
    public String getPhotoCaption() {
        return photoCaption;
    }

    @JsonProperty(value = "photo-caption")
    public void setPhotoCaption(String photoCaption) {
        this.photoCaption = photoCaption;
    }

    @JsonProperty(value = "photo-url-1280")
    public String getPhotoUrl1280() {
        return photoUrl1280;
    }

    @JsonProperty(value = "photo-url-1280")
    public void setPhotoUrl1280(String photoUrl1280) {
        this.photoUrl1280 = photoUrl1280;
    }
}
