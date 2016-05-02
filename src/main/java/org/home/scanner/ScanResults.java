package org.home.scanner;

import org.home.entity.Book;

import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ScanResults {
    private List<Book> bookList; //books that have been founded in search request
    private List<Book> emptyBookList; //files that have zero size or not exists
    private List<Book> undefinedBookList; //contains founded book files that cannot be parsed and files without needed extension
    private List<Path> scannedPathList; //scanned dirs and files
    private List<Path> badFilesPathList; //if file not exists or cannot be read
    private List<Path> ignoredPathFileList; //ignored for scan files and dirs
    private Duration scanTime;
    private Duration parseTime;
    private Duration globalTime;
    private long scan_id;

    private int foundPdfBooksCount = 0;
    private int foundEpubBooksCount = 0;
    private int foundfb2BooksCount = 0;
    private int foundDjvuBooksCount = 0;
    private int foundDocBooksCount = 0;
    private int foundcbrBooksCount = 0;
    private int foundTxtBooksCount = 0;

    public ScanResults(long scan_id) {
        this.scan_id = scan_id;
        init();
    }

    public ScanResults(){
        init();
    }

    private void init(){
        this.emptyBookList = new ArrayList<>();
        this.bookList = new ArrayList<>();
        this.badFilesPathList = new ArrayList<>();
        this.undefinedBookList = new ArrayList<>();
        this.scannedPathList = new ArrayList<>();
        this.ignoredPathFileList = new ArrayList<>();
        scanTime = parseTime = globalTime = Duration.ZERO;
    }

    public void clearResults(){
        this.emptyBookList.clear();
        this.bookList.clear();
        this.badFilesPathList.clear();
        this.undefinedBookList.clear();
        this.scannedPathList.clear();
        this.ignoredPathFileList.clear();
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

    public List<Book> getUndefinedBookList() {
        return undefinedBookList;
    }

    public void setUndefinedBookList(List<Book> undefinedBookList) {
        this.undefinedBookList = undefinedBookList;
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

    public long getScan_id() {
        return scan_id;
    }

    public void setScan_id(long scan_id) {
        this.scan_id = scan_id;
    }

    @Override
    public String toString() {
        return "ScanResults{" +
                " scan_id=" + scan_id +
                ", scanTime=" + scanTime +
                ", parseTime=" + parseTime +
                ", globalTime=" + globalTime +
                ", foundPdfBooksCount=" + foundPdfBooksCount +
                ", foundEpubBooksCount=" + foundEpubBooksCount +
                ", foundfb2BooksCount=" + foundfb2BooksCount +
                ", foundDjvuBooksCount=" + foundDjvuBooksCount +
                ", foundDocBooksCount=" + foundDocBooksCount +
                ", foundcbrBooksCount=" + foundcbrBooksCount +
                ", foundTxtBooksCount=" + foundTxtBooksCount +
                '}';
    }
}
