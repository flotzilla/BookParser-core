package org.home.media;

import org.home.utils.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bitybite on 5/2/16.
 */
public class MediaCollection {
    private int id;
    private String name;
    private String description;
    private Device device;
    private int mediaCollectionDirectoriesCount;
    private boolean isActive;
    private boolean isDeleted;
    private List<MediaCollectionDirectory> directoryList;

    public MediaCollection() {
        directoryList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getMediaCollectionDirectoriesCount() {
        return mediaCollectionDirectoriesCount;
    }

    public void setMediaCollectionDirectoriesCount(int mediaCollectionDirectoriesCount) {
        this.mediaCollectionDirectoriesCount = mediaCollectionDirectoriesCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<MediaCollectionDirectory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<MediaCollectionDirectory> directoryList) {
        this.directoryList = directoryList;
    }

    @Override
    public String toString() {
        return "MediaCollection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", device=" + device +
                ", mediaCollectionDirectoriesCount=" + mediaCollectionDirectoriesCount +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
