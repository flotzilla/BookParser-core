package org.home.parsers;

import ch.qos.logback.classic.Logger;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.home.entity.Book;
import org.home.utils.PropertiesHandler;
import org.home.utils.Session;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

public class EpubParser {
    public static final Logger logger =
            (ch.qos.logback.classic.Logger)  LoggerFactory.getLogger(EpubParser.class);

    private EpubReader epubReader;

    public EpubParser() {
        epubReader = new EpubReader();
    }

    public void parse(Path file, Book book ) throws FileNotFoundException, IOException {
        logger.trace("parsing " + file.getFileName());

        nl.siegmann.epublib.domain.Book epubBook
                = epubReader.readEpub(new FileInputStream(file.toFile()));
        StringBuilder sb = new StringBuilder();


        Metadata metadata = epubBook.getMetadata();
        String format = metadata.getFormat();
        logger.trace("epub format " + format);

        List<Author> authors = metadata.getAuthors();
        if(authors.size() > 0){
            authors.forEach(author -> {
                sb.append(author.getFirstname() + " " + author.getLastname() + ";");
                logger.trace("Found author name " +author.getFirstname() + " " + author.getLastname());
            });
            book.setAuthor(sb.toString());
        }

        if(metadata.getDescriptions().size() > 0){
            sb.setLength(0);
            metadata.getDescriptions().forEach(descr->{
                logger.trace("Description found " + descr);
                sb.append(descr).append(";");
            });
            book.setDescription(sb.toString());
        }

        if(metadata.getPublishers().size() > 0 ){
            sb.setLength(0);
            metadata.getPublishers().forEach(pub -> {
                logger.trace("Publisher " + pub);
                sb.append(pub);
            });
            book.setPublisherName(sb.toString());
        }

        logger.trace("Lang " + metadata.getLanguage());
        book.setLanguage(metadata.getLanguage());

        logger.trace("Book title" + epubBook.getTitle());
        book.setTitle(epubBook.getTitle());

        metadata.getOtherProperties().forEach((qName, s) -> {
            logger.trace("prop " + qName.toString() + " " + s);
        });

        logger.trace("Attempt to receive cover image");
        if(epubBook.getCoverImage() != null){
            logger.trace("Cover image exists");
            book.setPreviewImageFile(Session.getSessionName() + book.getId() + ".jpg");
            book.setHaveCover(true);
            byte[] imageData = epubBook.getCoverImage().getData();
            BufferedImage read = ImageIO.read(new ByteArrayInputStream(imageData));

            ImageIO.write(read, "jpg", new File(
                    PropertiesHandler.getProperty("temp_directory")
                            + File.separator
                            + Session.getSessionName() + book.getId() + ".jpg"
            ));
        }else{
            book.setHaveCover(false);
        }
    }
}
