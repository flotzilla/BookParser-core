package org.home.scanner;

import org.home.entity.Book;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ScanResults {
    private List<Book> bookList;
    private List<Book> emptyBookList;
    private List<Book> notParsedBookList;
    private List<Path> scannedPathList;
    private List<Path> badFilesPathList;
    private List<Path> ignoredPathFileList;
    private Duration scanTime;
    private Duration parseTime;
    private Duration globalTime;

    private int foundPdfBooksCount = 0;
    private int foundEpubBooksCount = 0;
    private int foundfb2BooksCount = 0;
    private int foundDjvuBooksCount = 0;
    private int foundDocBooksCount = 0;
    private int foundcbrBooksCount = 0;
    private int foundTxtBooksCount = 0;

    public ScanResults() {
        this.emptyBookList = new ArrayList<>();
        this.bookList = new ArrayList<>();
        this.badFilesPathList = new ArrayList<>();
        this.notParsedBookList = new ArrayList<>();
        this.scannedPathList = new ArrayList<>();
        this.ignoredPathFileList = new ArrayList<>();
    }

    public  void increaseTxtCount(){ foundTxtBooksCount++; }

    public void increasePdfCount(){
        foundPdfBooksCount++;
    }

    public void increaseEpubCount(){
        foundEpubBooksCount++;
    }

    public void increaseFb2Count(){
        foundfb2BooksCount++;
    }

    public void increaseDjvuCount(){
        foundDjvuBooksCount++;
    }

    public void increaseDocCount(){
        foundDocBooksCount++;
    }

    public void increaseCbrCount(){
        foundcbrBooksCount++;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Duration getScanTime() {
        return scanTime;
    }

    public void setScanTime(Duration scanTime) {
        this.scanTime = scanTime;
    }

    public Duration getParseTime() {
        return parseTime;
    }

    public void setParseTime(Duration parseTime) {
        this.parseTime = parseTime;
    }

    public List<Path> getScannedPathList() {
        return scannedPathList;
    }

    public void setScannedPathList(List<Path> scannedPathList) {
        this.scannedPathList = scannedPathList;
    }

    public int getFoundPdfBooksCount() {
        return foundPdfBooksCount;
    }

    public void setFoundPdfBooksCount(int foundPdfBooksCount) {
        this.foundPdfBooksCount = foundPdfBooksCount;
    }

    public int getFoundEpubBooksCount() {
        return foundEpubBooksCount;
    }

    public void setFoundEpubBooksCount(int foundEpubBooksCount) {
        this.foundEpubBooksCount = foundEpubBooksCount;
    }

    public int getFoundfb2BooksCount() {
        return foundfb2BooksCount;
    }

    public void setFoundfb2BooksCount(int foundfb2BooksCount) {
        this.foundfb2BooksCount = foundfb2BooksCount;
    }

    public int getFoundDjvuBooksCount() {
        return foundDjvuBooksCount;
    }

    public void setFoundDjvuBooksCount(int foundDjvuBooksCount) {
        this.foundDjvuBooksCount = foundDjvuBooksCount;
    }

    public int getFoundDocBooksCount() {
        return foundDocBooksCount;
    }

    public void setFoundDocBooksCount(int foundDocBooksCount) {
        this.foundDocBooksCount = foundDocBooksCount;
    }

    public int getFoundcbrBooksCount() {
        return foundcbrBooksCount;
    }

    public void setFoundcbrBooksCount(int foundcbrBooksCount) {
        this.foundcbrBooksCount = foundcbrBooksCount;
    }

    public Duration getGlobalTime() {
        return globalTime;
    }

    public void setGlobalTime(Duration globalTime) {
        this.globalTime = globalTime;
    }

    public int getFoundTxtBooksCount() {
        return foundTxtBooksCount;
    }

    public void setFoundTxtBooksCount(int foundTxtBooksCount) {
        this.foundTxtBooksCount = foundTxtBooksCount;
    }

    public List<Book> getEmptyBookList() {
        return emptyBookList;
    }

    public void setEmptyBookList(List<Book> emptyBookList) {
        this.emptyBookList = emptyBookList;
    }

    public List<Book> getNotParsedBookList() {
        return notParsedBookList;
    }

    public void setNotParsedBookList(List<Book> notParsedBookList) {
        this.notParsedBookList = notParsedBookList;
    }

    public List<Path> getBadFilesPathList() {
        return badFilesPathList;
    }

    public void setBadFilesPathList(List<Path> badFilesPathList) {
        this.badFilesPathList = badFilesPathList;
    }

    public List<Path> getIgnoredPathFileList() {
        return ignoredPathFileList;
    }

    public void setIgnoredPathFileList(List<Path> ignoredPathFileList) {
        this.ignoredPathFileList = ignoredPathFileList;
    }
}
