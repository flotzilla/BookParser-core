package org.home.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fb2Book extends Book {
    private static final Logger logger =
            LoggerFactory.getLogger(Fb2Book.class);

    private String encoding;

    private String annotation;
    private String genre;

    private String Translator;
    private String srcLang;
    private String srcGenre;
    private String srcAuthor;
    private String srcTitle;
    private String srcAnnotation;
    private String srcKeywords;
    private String srcDate;

    public Fb2Book(Book book) {
        count.decrementAndGet();
        logger.trace("Counter is " + count.get());

        setId(book.getId());
        setFileName(book.getFileName());
        setLocationPath(book.getLocationPath());
        setExtension(book.getExtension());
        setSize(book.getSize());
        setIs_deleted(book.is_deleted());

        logger.trace("Fb2book id " + this.getId());
        logger.trace("Created book id " + book.getId());
    }

    public Fb2Book() {
        count.decrementAndGet();
        new Fb2Book(new Book());
        logger.trace("Fb2book id " + this.getId());
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTranslator() {
        return Translator;
    }

    public void setTranslator(String translator) {
        Translator = translator;
    }

    public String getSrcLang() {
        return srcLang;
    }

    public void setSrcLang(String srcLang) {
        this.srcLang = srcLang;
    }

    public String getSrcGenre() {
        return srcGenre;
    }

    public void setSrcGenre(String srcGenre) {
        this.srcGenre = srcGenre;
    }

    public String getSrcAuthor() {
        return srcAuthor;
    }

    public void setSrcAuthor(String srcAuthor) {
        this.srcAuthor = srcAuthor;
    }

    public String getSrcTitle() {
        return srcTitle;
    }

    public void setSrcTitle(String srcTitle) {
        this.srcTitle = srcTitle;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFb2Book{" +
                "encoding='" + encoding + '\'' +
                ", annotation='" + annotation + '\'' +
                ", genre='" + genre + '\'' +
                ", Translator='" + Translator + '\'' +
                ", srcLang='" + srcLang + '\'' +
                ", srcGenre='" + srcGenre + '\'' +
                ", srcAuthor='" + srcAuthor + '\'' +
                ", srcTitle='" + srcTitle + '\'' +
                ", srcAnnotation='" + srcAnnotation + '\'' +
                ", srcKeywords='" + srcKeywords + '\'' +
                ", srcDate='" + srcDate + '\'' +
                '}';
    }

    public String getSrcAnnotation() {
        return srcAnnotation;
    }

    public void setSrcAnnotation(String srcAnnotation) {
        this.srcAnnotation = srcAnnotation;
    }

    public String getSrcKeywords() {
        return srcKeywords;
    }

    public void setSrcKeywords(String srcKeywords) {
        this.srcKeywords = srcKeywords;
    }

    public String getSrcDate() {
        return srcDate;
    }

    public void setSrcDate(String srcDate) {
        this.srcDate = srcDate;
    }
}
