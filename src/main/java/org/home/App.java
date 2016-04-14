package org.home;

import org.home.entity.Book;
import org.home.scanner.BookScanner;
import org.home.scanner.ScanResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.home.parsers.PDFParser;
import org.home.utils.Session;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

//    private static final Logger logger =
//            LoggerFactory.getLogger(App.class);
//
//    public static void main(String[] args) {
//        Session session = new Session();
//        Session.createTempoDirectory();
//
//        Path file = Paths.get("/media/MegaHard/Book/Java_/spring-framework-reference.4.2.4.pdf");
//        logger.debug("parsing file " + file.getFileName().toString());
//
////        pdfParser.parseFileMetadata(file, "pdf");
//
//        List<Path> includedPathList = new ArrayList<>();
//        includedPathList.add(file);
//        includedPathList.add(Paths.get("/media/MegaHard/Book/Java_/Java train/"));
//        List<Path> excludedPathList = new ArrayList<>();
//
//        BookScanner bookScanner = new BookScanner(includedPathList, excludedPathList);
//        ScanResults results = bookScanner.scan();
//            for (Book b: results.getBookList()){
//                logger.trace("book path: " + b);
//            }
//
//    }

}
