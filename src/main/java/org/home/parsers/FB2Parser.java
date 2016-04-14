package org.home.parsers;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.home.entity.Book;
import org.home.entity.Fb2Book;
import org.home.utils.PropertiesHandler;
import org.home.utils.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;

public class FB2Parser {
    private static Logger logger =
            LoggerFactory.getLogger(FB2Parser.class);

    private SAXReader reader;

    public FB2Parser() {
        reader = new SAXReader();
    }

    public void startPArse(Path file, Book book) throws DocumentException, IOException {
        Document doc = reader.read(file.toFile());
        Fb2Book fb2Book = (Fb2Book) book;

        logger.trace("Parsing " + file.getFileName());
        logger.trace("Root name " + doc.getRootElement().getName());
        if(!doc.getRootElement().getName().toLowerCase().equals("fictionbook")){
            logger.debug("Probably this is not a FB2 document");
        }

        if(doc.getDocType() != null){
            logger.debug(doc.getDocType().getElementName());
            logger.debug(doc.getDocType().getNodeTypeName());
            logger.debug("node type" + doc.getDocType().getNodeType());
        }

        Element description = doc.getRootElement().element("description");
        logger.debug("Descr elements size " + description.elements().size());
        if(description.elements().size() > 0){
            for (Object el: description.elements()){
                Element el_obg = (Element) el;
                logger.trace("Node name " + el_obg.getName());
                if (el_obg.getName().equals("title-info")) {
                        parseTitleInfo(el_obg, fb2Book);
                }
                if(el_obg.getName().equals("src-title-info")){
                        parseSrcTitleInfo(el_obg, fb2Book);
                }
                if(el_obg.getName().equals("publish-info")){
                        parsePublishInfo(el_obg, fb2Book);
                }
            }
        }else{
            logger.trace("Empty description node tree");
        }

        parseDocumentCover(doc, fb2Book);
    }

    private void parsePublishInfo(Element el_obg, Fb2Book fb2Book) {
        for(Object el: el_obg.elements()) {
            Element publisher_el = (Element) el;
            String publisher_el_name = publisher_el.getName();
            logger.trace("El name " + publisher_el.getName());
            switch (publisher_el_name) {
                case "book-name":
                    fb2Book.setPublisherBookName(publisher_el.getTextTrim());
                    continue;
                case "publisher":
                    fb2Book.setPublisherName(publisher_el.getTextTrim());
                    continue;
                case "city":
                    fb2Book.setPublisherCity(publisher_el.getTextTrim());
                    continue;
                case "year":
                    fb2Book.setPublishYear(publisher_el.getTextTrim());
                    continue;
                case "isbn":
                    fb2Book.setIsbn(publisher_el.getTextTrim());
            }
        }
    }

    private void parseSrcTitleInfo(Element el_obg, Fb2Book fb2Book) {
        StringBuilder sb = new StringBuilder();
        for(Object el: el_obg.elements()) {
            Element srcTitle_info_el = (Element) el;
            String srcTitle_info_elName = srcTitle_info_el.getName();
            logger.trace("El name " + srcTitle_info_el.getName());
            switch (srcTitle_info_elName){
                case "genre":
                    sb.setLength(0);
                    if(srcTitle_info_el.elements().size() > 0){
                        srcTitle_info_el.elements().forEach(e-> {
                            Element obj = (Element) e;
                            sb.append(obj.getTextTrim());
                        });
                        fb2Book.setSrcGenre(sb.toString());
                    }else{
                        fb2Book.setSrcGenre(srcTitle_info_el.getTextTrim());
                    }
                    continue;
                case "author":
                    logger.trace("first name is null " + String.valueOf(srcTitle_info_el.element("first-name") == null));
                    logger.trace("last name is null " + String.valueOf(srcTitle_info_el.element("last-name") == null));
                    logger.trace("middle name is null " + String.valueOf(srcTitle_info_el.element("middle-name") == null));
                    sb.setLength(0);
                    if(srcTitle_info_el.element("first-name") != null){
                        sb.append(srcTitle_info_el.element("first-name").getText()).append(" ");
                    }
                    if(srcTitle_info_el.element("middle-name") != null){
                        sb.append(srcTitle_info_el.element("middle-name").getText()).append(" ");
                    }
                    if(srcTitle_info_el.element("last-name") != null){
                        sb.append(srcTitle_info_el.element("last-name").getText());
                    }
                    fb2Book.setSrcAuthor(sb.toString());
                    continue;
                case "book-title":
                    fb2Book.setSrcTitle(srcTitle_info_el.getTextTrim());
                    continue;
                case "annotations":
                    sb.setLength(0);
                    srcTitle_info_el.elements().forEach(o -> {
                        Element obj = (Element) o;
                        sb.append("\n").append(obj.getTextTrim());
                    });
                    sb.append("\n");
                    fb2Book.setSrcAnnotation(sb.toString());
                    continue;
                case "keywords":
                    sb.setLength(0);
                    srcTitle_info_el.elements().forEach(o -> {
                        Element obj = (Element) o;
                        sb.append(obj.getTextTrim()).append(";");
                    });
                    fb2Book.setSrcKeywords(srcTitle_info_el.getTextTrim());
                    continue;
                case "date":
                    fb2Book.setSrcDate(srcTitle_info_el.getTextTrim());
                    continue;
                case "lang":
                    fb2Book.setSrcLang(srcTitle_info_el.getTextTrim());
            }
        }
    }

    private void parseTitleInfo(Element title_element, Fb2Book fb2Book){
        StringBuilder sb = new StringBuilder();
        for(Object el: title_element.elements()){
            Element title_info_el = (Element) el;
            String title_info_elName = title_info_el.getName();
            logger.trace("El name " + title_info_el.getName());
            switch (title_info_elName){
                case "genre":
                    if(title_info_el.elements().size() > 0){
                        sb.setLength(0);
                        title_info_el.elements().forEach(o -> {
                            Element obj = (Element) o;
                            sb.append(obj.getTextTrim()).append(";");
                        });
                        fb2Book.setGenre(sb.toString());
                    }else{
                        fb2Book.setGenre(title_info_el.getTextTrim());
                    }
                    continue;
                case "author":
                    logger.trace("first name is null " + String.valueOf(title_info_el.element("first-name") == null));
                    logger.trace("last name is null " + String.valueOf(title_info_el.element("last-name") == null));
                    logger.trace("middle name is null " + String.valueOf(title_info_el.element("middle-name") == null));
                    if(title_info_el.element("first-name") != null){
                        fb2Book.setAuthor(title_info_el.element("first-name").getText());
                    }
                    if(title_info_el.element("middle-name") != null){
                        fb2Book.setAuthor(fb2Book.getAuthor()
                                + " " + title_info_el.element("middle-name").getText());
                    }
                    if(title_info_el.element("last-name") != null){
                        fb2Book.setAuthor(fb2Book.getAuthor()
                                + " " + title_info_el.element("last-name").getText());
                    }
                    continue;
                case "annotation":
                    sb.setLength(0);
                    title_info_el.elements().forEach(o -> {
                        Element obj = (Element) o;
                        sb.append("\n").append(obj.getTextTrim());
                    });
                    sb.append("\n");
                    fb2Book.setAnnotation(sb.toString());
                    continue;
                case "date":
                    fb2Book.setCreationDate(title_info_el.getTextTrim());
                    continue;
                case "book-title":
                    fb2Book.setTitle(title_info_el.getTextTrim());
                    continue;
                case "keywords":
                    sb.setLength(0);
                    title_info_el.elements().forEach(o -> {
                        Element obj = (Element) o;
                        sb.append(obj.getTextTrim()).append(";");
                    });
                    fb2Book.setKeywords(title_info_el.getTextTrim());
                    continue;
                case "lang":
                    fb2Book.setLanguage(title_info_el.getTextTrim());
                    continue;
                case "translator":
                    sb.setLength(0);
                    for(Object trans_el: title_info_el.elements()){
                        Element translator = (Element) trans_el;
                        sb.append(translator.getTextTrim()).append(" ");
                    }
                    fb2Book.setTranslator(sb.toString());
                    continue;
                case "src-lang":
                    fb2Book.setSrcLang(title_info_el.getTextTrim());
            }

        }

    }

    private void parseDocumentCover(Document doc, Fb2Book fb2Book) throws IOException {
        Element binary = doc.getRootElement().element("binary");
        if(binary != null){
            fb2Book.setHaveCover(true);
            fb2Book.setPreviewImageFile(Session.getSessionName() + fb2Book.getId() + ".jpg");
            String value = binary.attribute("content-type").getValue();
            logger.trace("binary type " + value);
            logger.trace("Length of binary doc " + binary.getTextTrim().length());

            byte[] bytes = DatatypeConverter.parseBase64Binary(binary.getTextTrim());
            logger.trace("Bytes length " + bytes.length);
            BufferedImage read = ImageIO.read(new ByteArrayInputStream(bytes));

            ImageIO.write(read, "jpg", new File(
                    PropertiesHandler.getProperty("temp_directory")
                            + File.separator
                            + Session.getSessionName() + fb2Book.getId() + ".jpg"
            ));
        }
    }
}
