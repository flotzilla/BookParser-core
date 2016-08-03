package org.home.entity;

import org.home.utils.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    protected static final AtomicInteger count = new AtomicInteger(0);
    private static final Logger logger =
            LoggerFactory.getLogger(Fb2Book.class);
    private int id;
    private String scanId;
    private String fileName;
    private Path locationPath;
    private String extension;
    private String size;

    private String title;
    private String author;
    private int numberOfPages;
    private String subject;
    private String description;

    private String creationDate;
    private String modifDate;

    private String creator;
    private String producer;
    private String keywords;

    private String language;
    private String version;
    private boolean haveCover;
    private String previewImageFile;

    private String isbn;
    private String publisherName;
    private String publisherBookName;
    private String publisherCity;
    private String publishYear;

    private boolean is_deleted;

    public Book() {
        id = count.incrementAndGet();
        logger.trace("New book was created. Id: " + id);
    }

    public Book(int id){
        this.id = id;
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
                ", scanId='" + scanId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", locationPath=" + locationPath +
                ", extension='" + extension + '\'' +
                ", size='" + size + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", modifDate='" + modifDate + '\'' +
                ", creator='" + creator + '\'' +
                ", producer='" + producer + '\'' +
                ", keywords='" + keywords + '\'' +
                ", language='" + language + '\'' +
                ", version='" + version + '\'' +
                ", haveCover=" + haveCover +
                ", previewImageFile='" + previewImageFile + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", publisherBookName='" + publisherBookName + '\'' +
                ", publisherCity='" + publisherCity + '\'' +
                ", publishYear='" + publishYear + '\'' +
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherBookName() {
        return publisherBookName;
    }

    public void setPublisherBookName(String publisherBookName) {
        this.publisherBookName = publisherBookName;
    }

    public String getPublisherCity() {
        return publisherCity;
    }

    public void setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHaveCover() {
        return haveCover;
    }

    public void setHaveCover(boolean haveCover) {
        this.haveCover = haveCover;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public void setScanId(long scanId) {
        this.scanId = Session.getSessionName() + scanId + "_b" + id;
    }
}
