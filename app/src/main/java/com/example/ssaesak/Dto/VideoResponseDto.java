package com.example.ssaesak.Dto;

import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoResponseDto implements Serializable {

    private int videoId;

    private String title;

    private String source;

    private String link;

    private String type;

    private String crops;

    private String thumbnail;

    private String crops_name;

    private boolean watching;


    public VideoResponseDto() {

    }

    public VideoResponseDto(VideoResponseDto video) {
        this.videoId = video.getVideoId();
        this.title = video.getTitle();
        this.source = video.getSource();
        this.link = video.getLink();
        this.type = video.getType();
        this.crops = video.getCrops();
        this.crops_name = video.getCrops_name();
        this.watching = video.isWatching();
        this.thumbnail = video.getThumbnail();
    }

    public int getVideoId() {
        return videoId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCrops() {
        return crops;
    }

    public void setCrops(String crops) {
        this.crops = crops;
    }

    public String getCrops_name() {
        return crops_name;
    }

    public void setCrops_name(String crops_name) {
        this.crops_name = crops_name;
    }

    public boolean isWatching() {
        return watching;
    }

    public void setWatching(boolean watching) {
        this.watching = watching;
    }

}
