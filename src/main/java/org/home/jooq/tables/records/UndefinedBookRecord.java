/**
 * This class is generated by jOOQ
 */
package org.home.jooq.tables.records;


import javax.annotation.Generated;

import org.home.jooq.tables.UndefinedBook;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UndefinedBookRecord extends UpdatableRecordImpl<UndefinedBookRecord> {

	private static final long serialVersionUID = -1966509910;

	/**
	 * Setter for <code>Undefined_book.scan_id</code>.
	 */
	public void setScanId(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>Undefined_book.scan_id</code>.
	 */
	public String getScanId() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>Undefined_book.id</code>.
	 */
	public void setId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>Undefined_book.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>Undefined_book.file_name</code>.
	 */
	public void setFileName(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>Undefined_book.file_name</code>.
	 */
	public String getFileName() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>Undefined_book.location_path</code>.
	 */
	public void setLocationPath(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>Undefined_book.location_path</code>.
	 */
	public String getLocationPath() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>Undefined_book.ext</code>.
	 */
	public void setExt(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>Undefined_book.ext</code>.
	 */
	public String getExt() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>Undefined_book.file_size</code>.
	 */
	public void setFileSize(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>Undefined_book.file_size</code>.
	 */
	public String getFileSize() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>Undefined_book.title</code>.
	 */
	public void setTitle(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>Undefined_book.title</code>.
	 */
	public String getTitle() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>Undefined_book.author</code>.
	 */
	public void setAuthor(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>Undefined_book.author</code>.
	 */
	public String getAuthor() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>Undefined_book.number_of_pages</code>.
	 */
	public void setNumberOfPages(Integer value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>Undefined_book.number_of_pages</code>.
	 */
	public Integer getNumberOfPages() {
		return (Integer) getValue(8);
	}

	/**
	 * Setter for <code>Undefined_book.subject</code>.
	 */
	public void setSubject(String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>Undefined_book.subject</code>.
	 */
	public String getSubject() {
		return (String) getValue(9);
	}

	/**
	 * Setter for <code>Undefined_book.description</code>.
	 */
	public void setDescription(String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>Undefined_book.description</code>.
	 */
	public String getDescription() {
		return (String) getValue(10);
	}

	/**
	 * Setter for <code>Undefined_book.creation_date</code>.
	 */
	public void setCreationDate(String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>Undefined_book.creation_date</code>.
	 */
	public String getCreationDate() {
		return (String) getValue(11);
	}

	/**
	 * Setter for <code>Undefined_book.modification_date</code>.
	 */
	public void setModificationDate(String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>Undefined_book.modification_date</code>.
	 */
	public String getModificationDate() {
		return (String) getValue(12);
	}

	/**
	 * Setter for <code>Undefined_book.creator</code>.
	 */
	public void setCreator(String value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>Undefined_book.creator</code>.
	 */
	public String getCreator() {
		return (String) getValue(13);
	}

	/**
	 * Setter for <code>Undefined_book.producer</code>.
	 */
	public void setProducer(String value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>Undefined_book.producer</code>.
	 */
	public String getProducer() {
		return (String) getValue(14);
	}

	/**
	 * Setter for <code>Undefined_book.keywords</code>.
	 */
	public void setKeywords(String value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>Undefined_book.keywords</code>.
	 */
	public String getKeywords() {
		return (String) getValue(15);
	}

	/**
	 * Setter for <code>Undefined_book.lang</code>.
	 */
	public void setLang(String value) {
		setValue(16, value);
	}

	/**
	 * Getter for <code>Undefined_book.lang</code>.
	 */
	public String getLang() {
		return (String) getValue(16);
	}

	/**
	 * Setter for <code>Undefined_book.version</code>.
	 */
	public void setVersion(String value) {
		setValue(17, value);
	}

	/**
	 * Getter for <code>Undefined_book.version</code>.
	 */
	public String getVersion() {
		return (String) getValue(17);
	}

	/**
	 * Setter for <code>Undefined_book.have_cover</code>.
	 */
	public void setHaveCover(Object value) {
		setValue(18, value);
	}

	/**
	 * Getter for <code>Undefined_book.have_cover</code>.
	 */
	public Object getHaveCover() {
		return (Object) getValue(18);
	}

	/**
	 * Setter for <code>Undefined_book.preview_image_file</code>.
	 */
	public void setPreviewImageFile(String value) {
		setValue(19, value);
	}

	/**
	 * Getter for <code>Undefined_book.preview_image_file</code>.
	 */
	public String getPreviewImageFile() {
		return (String) getValue(19);
	}

	/**
	 * Setter for <code>Undefined_book.isbn</code>.
	 */
	public void setIsbn(String value) {
		setValue(20, value);
	}

	/**
	 * Getter for <code>Undefined_book.isbn</code>.
	 */
	public String getIsbn() {
		return (String) getValue(20);
	}

	/**
	 * Setter for <code>Undefined_book.publisher_name</code>.
	 */
	public void setPublisherName(String value) {
		setValue(21, value);
	}

	/**
	 * Getter for <code>Undefined_book.publisher_name</code>.
	 */
	public String getPublisherName() {
		return (String) getValue(21);
	}

	/**
	 * Setter for <code>Undefined_book.publisher_book_name</code>.
	 */
	public void setPublisherBookName(String value) {
		setValue(22, value);
	}

	/**
	 * Getter for <code>Undefined_book.publisher_book_name</code>.
	 */
	public String getPublisherBookName() {
		return (String) getValue(22);
	}

	/**
	 * Setter for <code>Undefined_book.publisher_city</code>.
	 */
	public void setPublisherCity(String value) {
		setValue(23, value);
	}

	/**
	 * Getter for <code>Undefined_book.publisher_city</code>.
	 */
	public String getPublisherCity() {
		return (String) getValue(23);
	}

	/**
	 * Setter for <code>Undefined_book.publish_year</code>.
	 */
	public void setPublishYear(String value) {
		setValue(24, value);
	}

	/**
	 * Getter for <code>Undefined_book.publish_year</code>.
	 */
	public String getPublishYear() {
		return (String) getValue(24);
	}

	/**
	 * Setter for <code>Undefined_book.is_deleted</code>.
	 */
	public void setIsDeleted(Object value) {
		setValue(25, value);
	}

	/**
	 * Getter for <code>Undefined_book.is_deleted</code>.
	 */
	public Object getIsDeleted() {
		return (Object) getValue(25);
	}

	/**
	 * Setter for <code>Undefined_book.encoding</code>.
	 */
	public void setEncoding(String value) {
		setValue(26, value);
	}

	/**
	 * Getter for <code>Undefined_book.encoding</code>.
	 */
	public String getEncoding() {
		return (String) getValue(26);
	}

	/**
	 * Setter for <code>Undefined_book.annotaion</code>.
	 */
	public void setAnnotaion(String value) {
		setValue(27, value);
	}

	/**
	 * Getter for <code>Undefined_book.annotaion</code>.
	 */
	public String getAnnotaion() {
		return (String) getValue(27);
	}

	/**
	 * Setter for <code>Undefined_book.genre</code>.
	 */
	public void setGenre(String value) {
		setValue(28, value);
	}

	/**
	 * Getter for <code>Undefined_book.genre</code>.
	 */
	public String getGenre() {
		return (String) getValue(28);
	}

	/**
	 * Setter for <code>Undefined_book.translator</code>.
	 */
	public void setTranslator(String value) {
		setValue(29, value);
	}

	/**
	 * Getter for <code>Undefined_book.translator</code>.
	 */
	public String getTranslator() {
		return (String) getValue(29);
	}

	/**
	 * Setter for <code>Undefined_book.src_lang</code>.
	 */
	public void setSrcLang(String value) {
		setValue(30, value);
	}

	/**
	 * Getter for <code>Undefined_book.src_lang</code>.
	 */
	public String getSrcLang() {
		return (String) getValue(30);
	}

	/**
	 * Setter for <code>Undefined_book.src_genre</code>.
	 */
	public void setSrcGenre(String value) {
		setValue(31, value);
	}

	/**
	 * Getter for <code>Undefined_book.src_genre</code>.
	 */
	public String getSrcGenre() {
		return (String) getValue(31);
	}

	/**
	 * Setter for <code>Undefined_book.src_author</code>.
	 */
	public void setSrcAuthor(String value) {
		setValue(32, value);
	}

	/**
	 * Getter for <code>Undefined_book.src_author</code>.
	 */
	public String getSrcAuthor() {
		return (String) getValue(32);
	}

	/**
	 * Setter for <code>Undefined_book.src_title</code>.
	 */
	public void setSrcTitle(String value) {
		setValue(33, value);
	}

	/**
	 * Getter for <code>Undefined_book.src_title</code>.
	 */
	public String getSrcTitle() {
		return (String) getValue(33);
	}

	/**
	 * Setter for <code>Undefined_book.src_annotation</code>.
	 */
	public void setSrcAnnotation(String value) {
		setValue(34, value);
	}

	/**
	 * Getter for <code>Undefined_book.src_annotation</code>.
	 */
	public String getSrcAnnotation() {
		return (String) getValue(34);
	}

	/**
	 * Setter for <code>Undefined_book.src_keywords</code>.
	 */
	public void setSrcKeywords(String value) {
		setValue(35, value);
	}

	/**
	 * Getter for <code>Undefined_book.src_keywords</code>.
	 */
	public String getSrcKeywords() {
		return (String) getValue(35);
	}

	/**
	 * Setter for <code>Undefined_book.src_date</code>.
	 */
	public void setSrcDate(String value) {
		setValue(36, value);
	}

	/**
	 * Getter for <code>Undefined_book.src_date</code>.
	 */
	public String getSrcDate() {
		return (String) getValue(36);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<String> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UndefinedBookRecord
	 */
	public UndefinedBookRecord() {
		super(UndefinedBook.UNDEFINED_BOOK);
	}

	/**
	 * Create a detached, initialised UndefinedBookRecord
	 */
	public UndefinedBookRecord(String scanId, Integer id, String fileName, String locationPath, String ext, String fileSize, String title, String author, Integer numberOfPages, String subject, String description, String creationDate, String modificationDate, String creator, String producer, String keywords, String lang, String version, Object haveCover, String previewImageFile, String isbn, String publisherName, String publisherBookName, String publisherCity, String publishYear, Object isDeleted, String encoding, String annotaion, String genre, String translator, String srcLang, String srcGenre, String srcAuthor, String srcTitle, String srcAnnotation, String srcKeywords, String srcDate) {
		super(UndefinedBook.UNDEFINED_BOOK);

		setValue(0, scanId);
		setValue(1, id);
		setValue(2, fileName);
		setValue(3, locationPath);
		setValue(4, ext);
		setValue(5, fileSize);
		setValue(6, title);
		setValue(7, author);
		setValue(8, numberOfPages);
		setValue(9, subject);
		setValue(10, description);
		setValue(11, creationDate);
		setValue(12, modificationDate);
		setValue(13, creator);
		setValue(14, producer);
		setValue(15, keywords);
		setValue(16, lang);
		setValue(17, version);
		setValue(18, haveCover);
		setValue(19, previewImageFile);
		setValue(20, isbn);
		setValue(21, publisherName);
		setValue(22, publisherBookName);
		setValue(23, publisherCity);
		setValue(24, publishYear);
		setValue(25, isDeleted);
		setValue(26, encoding);
		setValue(27, annotaion);
		setValue(28, genre);
		setValue(29, translator);
		setValue(30, srcLang);
		setValue(31, srcGenre);
		setValue(32, srcAuthor);
		setValue(33, srcTitle);
		setValue(34, srcAnnotation);
		setValue(35, srcKeywords);
		setValue(36, srcDate);
	}
}
