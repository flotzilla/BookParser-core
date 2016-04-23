/**
 * This class is generated by jOOQ
 */
package org.home.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.home.jooq.DefaultSchema;
import org.home.jooq.Keys;
import org.home.jooq.tables.records.BookRecord;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Book extends TableImpl<BookRecord> {

	private static final long serialVersionUID = -293768771;

	/**
	 * The reference instance of <code>Book</code>
	 */
	public static final Book BOOK = new Book();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<BookRecord> getRecordType() {
		return BookRecord.class;
	}

	/**
	 * The column <code>Book.scan_id</code>.
	 */
	public final TableField<BookRecord, String> SCAN_ID = createField("scan_id", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>Book.id</code>.
	 */
	public final TableField<BookRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>Book.file_name</code>.
	 */
	public final TableField<BookRecord, String> FILE_NAME = createField("file_name", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.location_path</code>.
	 */
	public final TableField<BookRecord, String> LOCATION_PATH = createField("location_path", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.ext</code>.
	 */
	public final TableField<BookRecord, String> EXT = createField("ext", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.file_size</code>.
	 */
	public final TableField<BookRecord, String> FILE_SIZE = createField("file_size", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.title</code>.
	 */
	public final TableField<BookRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.author</code>.
	 */
	public final TableField<BookRecord, String> AUTHOR = createField("author", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.number_of_pages</code>.
	 */
	public final TableField<BookRecord, Integer> NUMBER_OF_PAGES = createField("number_of_pages", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>Book.subject</code>.
	 */
	public final TableField<BookRecord, String> SUBJECT = createField("subject", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.description</code>.
	 */
	public final TableField<BookRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.creation_date</code>.
	 */
	public final TableField<BookRecord, String> CREATION_DATE = createField("creation_date", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.modification_date</code>.
	 */
	public final TableField<BookRecord, String> MODIFICATION_DATE = createField("modification_date", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.creator</code>.
	 */
	public final TableField<BookRecord, String> CREATOR = createField("creator", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.producer</code>.
	 */
	public final TableField<BookRecord, String> PRODUCER = createField("producer", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.keywords</code>.
	 */
	public final TableField<BookRecord, String> KEYWORDS = createField("keywords", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.lang</code>.
	 */
	public final TableField<BookRecord, String> LANG = createField("lang", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.version</code>.
	 */
	public final TableField<BookRecord, String> VERSION = createField("version", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.have_cover</code>.
	 */
	public final TableField<BookRecord, Object> HAVE_COVER = createField("have_cover", org.jooq.impl.DefaultDataType.getDefaultDataType("BOOL"), this, "");

	/**
	 * The column <code>Book.preview_image_file</code>.
	 */
	public final TableField<BookRecord, String> PREVIEW_IMAGE_FILE = createField("preview_image_file", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.isbn</code>.
	 */
	public final TableField<BookRecord, String> ISBN = createField("isbn", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.publisher_name</code>.
	 */
	public final TableField<BookRecord, String> PUBLISHER_NAME = createField("publisher_name", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.publisher_book_name</code>.
	 */
	public final TableField<BookRecord, String> PUBLISHER_BOOK_NAME = createField("publisher_book_name", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.publisher_city</code>.
	 */
	public final TableField<BookRecord, String> PUBLISHER_CITY = createField("publisher_city", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.publish_year</code>.
	 */
	public final TableField<BookRecord, String> PUBLISH_YEAR = createField("publish_year", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.is_deleted</code>.
	 */
	public final TableField<BookRecord, Object> IS_DELETED = createField("is_deleted", org.jooq.impl.DefaultDataType.getDefaultDataType("BOOL"), this, "");

	/**
	 * The column <code>Book.encoding</code>.
	 */
	public final TableField<BookRecord, String> ENCODING = createField("encoding", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.annotaion</code>.
	 */
	public final TableField<BookRecord, String> ANNOTAION = createField("annotaion", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.genre</code>.
	 */
	public final TableField<BookRecord, String> GENRE = createField("genre", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.translator</code>.
	 */
	public final TableField<BookRecord, String> TRANSLATOR = createField("translator", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.src_lang</code>.
	 */
	public final TableField<BookRecord, String> SRC_LANG = createField("src_lang", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.src_genre</code>.
	 */
	public final TableField<BookRecord, String> SRC_GENRE = createField("src_genre", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.src_author</code>.
	 */
	public final TableField<BookRecord, String> SRC_AUTHOR = createField("src_author", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.src_title</code>.
	 */
	public final TableField<BookRecord, String> SRC_TITLE = createField("src_title", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.src_annotation</code>.
	 */
	public final TableField<BookRecord, String> SRC_ANNOTATION = createField("src_annotation", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.src_keywords</code>.
	 */
	public final TableField<BookRecord, String> SRC_KEYWORDS = createField("src_keywords", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Book.src_date</code>.
	 */
	public final TableField<BookRecord, String> SRC_DATE = createField("src_date", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>Book</code> table reference
	 */
	public Book() {
		this("Book", null);
	}

	/**
	 * Create an aliased <code>Book</code> table reference
	 */
	public Book(String alias) {
		this(alias, BOOK);
	}

	private Book(String alias, Table<BookRecord> aliased) {
		this(alias, aliased, null);
	}

	private Book(String alias, Table<BookRecord> aliased, Field<?>[] parameters) {
		super(alias, DefaultSchema.DEFAULT_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<BookRecord> getPrimaryKey() {
		return Keys.PK_BOOK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<BookRecord>> getKeys() {
		return Arrays.<UniqueKey<BookRecord>>asList(Keys.PK_BOOK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Book as(String alias) {
		return new Book(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Book rename(String name) {
		return new Book(name, null);
	}
}
