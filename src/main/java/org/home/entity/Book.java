package org.home.entity;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String fileName;
    private Path locationPath;
    private String extension;
    private String size;

    private String title;
    private String author;
    private int numberOfPages;
    private String subject;

    private String creationDate;
    private String modifDate;

    private String creator;
    private String producer;
    private String keywords;
    private String trapped;

    private String language;
    private String version;
    private String previewImageFile;

    private boolean is_deleted;

    public Book() {
        id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Path getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(Path locationPath) {
        this.locationPath = locationPath;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTrapped() {
        return trapped;
    }

    public void setTrapped(String trapped) {
        this.trapped = trapped;
    }

    public String getModifDate() {
        return modifDate;
    }

    public void setModifDate(String modifDate) {
        this.modifDate = modifDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPreviewImageFile() {
        return previewImageFile;
    }

    public void setPreviewImageFile(String previewImageFile) {
        this.previewImageFile = previewImageFile;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", locationPath=" + locationPath +
                ", extension='" + extension + '\'' +
                ", size='" + size + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", subject='" + subject + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", modifDate='" + modifDate + '\'' +
                ", creator='" + creator + '\'' +
                ", producer='" + producer + '\'' +
                ", keywords='" + keywords + '\'' +
                ", trapped='" + trapped + '\'' +
                ", language='" + language + '\'' +
                ", version='" + version + '\'' +
                ", previewImageFile='" + previewImageFile + '\'' +
                ", is_deleted=" + is_deleted +
                '}';
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean is_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
