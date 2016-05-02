package org.home.media;

/**
 * Created by bitybite on 5/2/16.
 */
public class MediaCollectionDirectory {
    private int id;
    private String folderName;
    private String modificatoinDate;
    private int filesCount;
    private boolean isActive;
    private boolean isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getModificatoinDate() {
        return modificatoinDate;
    }

    public void setModificatoinDate(String modificatoinDate) {
        this.modificatoinDate = modificatoinDate;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
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
}
