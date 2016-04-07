package org.home.parsers;


import org.home.entity.Book;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.home.utils.PropertiesHandler;
import org.home.utils.Session;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PDFParser {
    private static final Logger logger =
            LoggerFactory.getLogger(PDFParser.class);

    public static void parseFileMetadata(Path file, Book book) throws IOException {
        org.apache.pdfbox.pdfparser.PDFParser parser =
                new org.apache.pdfbox.pdfparser.PDFParser(new RandomAccessFile(file.toFile(), "rw"));
        parser.parse();
        PDDocument document = parser.getPDDocument();
        if (document.isEncrypted()) {
            logger.error("Document is encrypted");
        }

        PDDocumentInformation info = document.getDocumentInformation();

        book.setFileName(file.getFileName().toString());
        book.setLocationPath(file);

        book.setTitle(info.getTitle());
        book.setAuthor(info.getAuthor());
        book.setNumberOfPages(document.getNumberOfPages());
        book.setSubject(info.getSubject());

        book.setCreator(info.getCreator());
        book.setProducer(info.getProducer());
        book.setKeywords(info.getKeywords());
        book.setTrapped(info.getTrapped());

        book.setLanguage(document.getDocumentCatalog().getLanguage());
        book.setVersion(document.getDocumentCatalog().getVersion());

        SimpleDateFormat format1 = new SimpleDateFormat(PropertiesHandler.getProperty("date_format"));
        Calendar creationDate = info.getCreationDate();
        if (creationDate != null) {
            book.setCreationDate(format1.format(creationDate.getTime()));
        } else {
            logger.debug("empty creation date");
        }

        Calendar modificationDate = info.getModificationDate();
        if (modificationDate != null) {
            book.setModifDate(format1.format(modificationDate.getTime()));
        } else {
            logger.debug("empty modification date");
        }

        //generating preview image and save it to temporary directory
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 50, ImageType.RGB);

        book.setPreviewImageFile(Session.getSessionName() + book.getId() + ".png");
        ImageIOUtil.writeImage(bufferedImage,
                PropertiesHandler.getProperty("temp_directory") + File.separator
                        + book.getPreviewImageFile(), 50);

        logger.debug("Book successfully parsed");
        logger.trace(book.toString());
        document.close();
    }
}
