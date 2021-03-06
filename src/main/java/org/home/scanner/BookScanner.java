package org.home.scanner;

import org.dom4j.DocumentException;
import org.home.entity.Book;
import org.home.entity.Fb2Book;
import org.home.exceptions.BadInputListDataException;
import org.home.parsers.EpubParser;
import org.home.parsers.FB2Parser;
import org.home.parsers.PDFParser;
import org.home.utils.FileUtils;
import org.home.utils.PropertiesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookScanner {

    private static final Logger logger =
            LoggerFactory.getLogger(BookScanner.class);
    private List<Path> includedPathList;
    private List<Path> excludedPathList;
    private List<Path> bookPathList;

    public BookScanner() {}

    public BookScanner(List<Path> includedPathList, List<Path> excludedPathList) {
        this.includedPathList = includedPathList;
        this.excludedPathList = excludedPathList;
        bookPathList = new ArrayList<>();
    }

    public ScanResults scan() throws BadInputListDataException {
        if(!isIncludeListOk()){
            throw new BadInputListDataException("Input List is not ready");
        }

        if(!isExcludeListOk()){
            excludedPathList = new ArrayList<>();
        }

        ScanResults scanResults = new ScanResults(
                new Timestamp(new Date().getTime()).getTime()
        );
        startScan(scanResults);
        parseBooks(scanResults);

        return scanResults;
    }

    private boolean isIncludeListOk() {
        return this.includedPathList != null && this.includedPathList.size() > 0;
    }

    private boolean isExcludeListOk() {
        return this.excludedPathList != null && this.excludedPathList.size() > 0;
    }

    private void startScan(ScanResults scanResults){
        if(bookPathList != null){
            bookPathList.clear();
        }
        logger.debug("Directory scanner have been started");
        Instant start = Instant.now();

        if(includedPathList != null){
                if(includedPathList.size() != 0){
                    for (Path searchHerePAth : includedPathList){
                        try {
                            FindBooksFileVisitor findBooksFileVisitor =
                                    new FindBooksFileVisitor(excludedPathList, bookPathList,
                                            scanResults.getIgnoredPathFileList(),
                                            scanResults.getBadFilesPathList());
                            Files.walkFileTree(searchHerePAth, findBooksFileVisitor);
                            logger.trace("Not appropriate files count " + scanResults.getBadFilesPathList().size());
                            logger.trace("Not skipped files count " + scanResults.getIgnoredPathFileList().size());
                        } catch (IOException e) {
                            logger.debug("Searching error" + e.getMessage());

                        }
                    }
                }else{
                    logger.debug("Nothing to scan");
                }
            }else{
                logger.debug("Nothing to scan");
            }

        logger.debug("Directory scanner have been finished");
        Instant stop = Instant.now();

        Duration scanTime = Duration.between(start, stop);
        logger.debug("Elapsing scan time " + scanTime);

        scanResults.setScanTime(scanTime);
        scanResults.setScannedPathList(bookPathList);
    }

    private ScanResults parseBooks(ScanResults scanResults){
        Instant start = Instant.now();
        logger.debug("Starting book parser");

        FB2Parser fb2Parser = new FB2Parser();
        EpubParser epubParser = new EpubParser();
        for (Path pathItem: scanResults.getScannedPathList()){
            Book book = null;
            try {
                book = parseFileData(pathItem);
            } catch (SecurityException se){
                logger.error("Security exception " + se.getMessage());
            } catch(IOException e) {
                logger.error("Cannot find the file " + e.getMessage());
            }
            if(book != null){
                String[] fileExtension = FileUtils.getFileExtension(pathItem);
                book.setFileName(fileExtension[0]);
                book.setExtension(fileExtension[1]);
                book.setScanId(scanResults.getScan_id());
                if(!book.is_deleted()){
                    if(!book.getSize().equals("0")){ //if have some content
                        try {
                            switch (fileExtension[1]){
                                /* TODO refract this to something like
                                    if (PropertiesHandler.getProperty("extension") == pdf )
                                      than do
                                 */
                                case "pdf":
                                    PDFParser.parseFileMetadata(pathItem, book);
                                    scanResults.getBookList().add(book);
                                    scanResults.increasePdfCount();
                                    break;
                                case "fb2":
                                    scanResults.increaseFb2Count();
                                    Fb2Book fb2Book = fb2Parser.startParse(pathItem, book);
                                    scanResults.getBookList().add(fb2Book);
                                    break;
                                case "txt":
                                    if (PropertiesHandler.getProperty("txt_is_book").equals("true")){
                                        parseTxtFile(scanResults, book);
                                        scanResults.getBookList().add(book);
                                        scanResults.increaseTxtCount();
                                    }else{
                                        logger.trace("Skip txt file " + book.getLocationPath().toString());
                                        scanResults.getIgnoredPathFileList().add(book.getLocationPath());
                                    }
                                    break;
                                case "epub":
                                    scanResults.increaseEpubCount();
                                    epubParser.parse(pathItem, book);
                                    scanResults.getBookList().add(book);
                                    break;
                                case "doc": case "docx" : case "rtf":
                                    parseDocFiles(scanResults, book);
                                    scanResults.increaseDocCount();
                                    break;
                                case "cbr": case "cbz":
                                    parseComicBook(scanResults, book);
                                    break;
                                case "djvu":
                                    break;
                                case "undefined":
                                    parseUndefinedBook(scanResults, book);
                                    break;
                                default:
                                    parseUndefinedBook(scanResults, book);
                                    break;
                            }
                        } catch (IOException | DocumentException e) {
                            logger.error("File parsing problem" + e.getMessage());
                            parseUndefinedBook(scanResults, book);
                        }
                    }else{ //empty file
                        parseEmptyContentFile(scanResults, book);
                    }
                }else{ //file was deleted or not exist
                    parseEmptyContentFile(scanResults, book);
                }
            }else{ //if file was not found or have no permission to read
                parseBadFiles(scanResults, pathItem);
            }
        }
        Duration parseTime = Duration.between(start, Instant.now());
        scanResults.setParseTime(parseTime);

        logger.debug("Book parsing is finished");
        logger.debug("Elapsing scan time " + parseTime);

        return scanResults;
    }

    private void parseComicBook(ScanResults scanResults, Book book) {
        //TODO implement this
    }

    private void parseTxtFile(ScanResults scanResults, Book book) {
        logger.trace("Add txt file");
        if(PropertiesHandler.getProperty("dash_separator").equals("true")){
            if(book.getFileName().contains("-")){
                String[] strings = FileUtils.parseFileNameBydashSeparator(book.getFileName());
                if(!strings[0].equals("undefined")){
                    book.setAuthor(strings[0]);
                    book.setTitle(strings[1]);
                }
            }
        }
    }

    private void parseUndefinedBook(ScanResults scanResults, Book book) {
        logger.trace("Cannot parse book with this extension or parser problem"
                + book.getLocationPath().toString());
        scanResults.getUndefinedBookList().add(book);
    }

    private void parseEmptyContentFile(ScanResults scanResults, Book book) {
        logger.trace("Empty book found" + book.getLocationPath().toString());
        scanResults.getEmptyBookList().add(book);
    }

    private void parseDocFiles(ScanResults scanResults, Book book){
        logger.trace("Add ms document");
        scanResults.getBookList().add(book);
    }

    private void parseBadFiles(ScanResults scanResults, Path file){
        logger.trace("File no found " + file.toString());
        scanResults.getBadFilesPathList().add(file);
    }

    private static Book parseFileData(Path file) throws SecurityException, IOException {

        boolean exists = Files.exists(file, LinkOption.NOFOLLOW_LINKS);
        if(exists) {
            Book book = new Book();
            logger.trace("New book id: " + book.getId());
            book.setIs_deleted(!exists);
            book.setLocationPath(file);

            book.setSize(FileUtils.calculateFileSize(file));

            return book;
        }else{
            return null;
        }
    }

    private static class FindBooksFileVisitor extends SimpleFileVisitor<Path> {
        ExcludedPathList excludedList;
        private List<Path> bookPathList;
        private PathMatcher pathMatcher;
        private List<Path> ignoredPathList;
        private List<Path> badFilesPathList;

        public FindBooksFileVisitor(List<Path> excludedPathList, List<Path> bookPathList,
                                    List<Path> ignoredPathList, List<Path> badFilesPathList) {
            this.bookPathList = bookPathList;
            this.excludedList = new ExcludedPathList(excludedPathList);
            this.ignoredPathList = ignoredPathList;
            this.badFilesPathList = badFilesPathList;

            pathMatcher = FileSystems.getDefault()
                    .getPathMatcher("glob:{" + preparePathMatcherExtensions() + "}");
            logger.trace("Pathmatcher " + preparePathMatcherExtensions());
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if(isContains(excludedList.getDirectoryList(), dir)){
                logger.trace("Scan for directory " + dir + " will ne skipped");
                ignoredPathList.add(dir);
                return FileVisitResult.SKIP_SUBTREE;
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(attrs.isSymbolicLink()){
                logger.trace("Skip symbol link " + file.getFileName().toString());
                ignoredPathList.add(file);
            }

            //skip dirs and files
            if(isContains(excludedList.getFileList(), file)){
                logger.trace("Found item in excluded list, will be skipped");
                ignoredPathList.add(file);
                return FileVisitResult.CONTINUE;
            }

            if(attrs.isRegularFile()){
                if(pathMatcher.matches(file.getFileName())){
                    bookPathList.add(file);
                }else{
                    logger.trace("File does not match " + file.toString());
                    ignoredPathList.add(file);
                }
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            logger.error("Cannot visit file " + file + "Exception" + exc.getMessage());
            logger.error("File will be added to badfiles list");
            badFilesPathList.add(file);
            return FileVisitResult.CONTINUE;
        }
    }

    private static String preparePathMatcherExtensions(){
        List<String> fileExtensions = PropertiesHandler.getFileExtensions();
        StringBuilder sb = new StringBuilder();
        for(String ext: fileExtensions){
            sb.append("*.").append(ext).append(",");
        }
        String extString = sb.toString();
        return extString.substring(0, extString.length()-1);
    }

        private static boolean isContains(List<Path> pathList, Path item){
        boolean isContains = false;
        for(Path listItem: pathList){
            if(listItem.toAbsolutePath().toString().equals(item.toAbsolutePath().toString())){
                isContains = true;
            }
        }
        return isContains;
    }



}
