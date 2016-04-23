/**
 * This class is generated by jOOQ
 */
package org.home.jooq;


import javax.annotation.Generated;

import org.home.jooq.tables.BadFilesPathList;
import org.home.jooq.tables.Book;
import org.home.jooq.tables.EmptyBook;
import org.home.jooq.tables.IgnoredPathList;
import org.home.jooq.tables.ScanBadFilesPathList;
import org.home.jooq.tables.ScanBook;
import org.home.jooq.tables.ScanEmptyBook;
import org.home.jooq.tables.ScanScannedPathList;
import org.home.jooq.tables.ScanUndefinedBook;
import org.home.jooq.tables.ScanUndefinedPathList;
import org.home.jooq.tables.ScannedPathList;
import org.home.jooq.tables.Scanresult;
import org.home.jooq.tables.Session;
import org.home.jooq.tables.SessionScan;
import org.home.jooq.tables.SqliteSequence;
import org.home.jooq.tables.UndefinedBook;


/**
 * Convenience access to all tables in 
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.3"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

	/**
	 * The table Bad_files_path_list
	 */
	public static final BadFilesPathList BAD_FILES_PATH_LIST = org.home.jooq.tables.BadFilesPathList.BAD_FILES_PATH_LIST;

	/**
	 * The table Book
	 */
	public static final Book BOOK = org.home.jooq.tables.Book.BOOK;

	/**
	 * The table Empty_book
	 */
	public static final EmptyBook EMPTY_BOOK = org.home.jooq.tables.EmptyBook.EMPTY_BOOK;

	/**
	 * The table ScanResult
	 */
	public static final Scanresult SCANRESULT = org.home.jooq.tables.Scanresult.SCANRESULT;

	/**
	 * The table Scan_Bad_files_path_list
	 */
	public static final ScanBadFilesPathList SCAN_BAD_FILES_PATH_LIST = org.home.jooq.tables.ScanBadFilesPathList.SCAN_BAD_FILES_PATH_LIST;

	/**
	 * The table Scan_Book
	 */
	public static final ScanBook SCAN_BOOK = org.home.jooq.tables.ScanBook.SCAN_BOOK;

	/**
	 * The table Scan_Empty_Book
	 */
	public static final ScanEmptyBook SCAN_EMPTY_BOOK = org.home.jooq.tables.ScanEmptyBook.SCAN_EMPTY_BOOK;

	/**
	 * The table Scan_Scanned_path_list
	 */
	public static final ScanScannedPathList SCAN_SCANNED_PATH_LIST = org.home.jooq.tables.ScanScannedPathList.SCAN_SCANNED_PATH_LIST;

	/**
	 * The table Scan_Undefined_Book
	 */
	public static final ScanUndefinedBook SCAN_UNDEFINED_BOOK = org.home.jooq.tables.ScanUndefinedBook.SCAN_UNDEFINED_BOOK;

	/**
	 * The table Scan_Undefined_path_list
	 */
	public static final ScanUndefinedPathList SCAN_UNDEFINED_PATH_LIST = org.home.jooq.tables.ScanUndefinedPathList.SCAN_UNDEFINED_PATH_LIST;

	/**
	 * The table Scanned_path_list
	 */
	public static final ScannedPathList SCANNED_PATH_LIST = org.home.jooq.tables.ScannedPathList.SCANNED_PATH_LIST;

	/**
	 * The table Session
	 */
	public static final Session SESSION = org.home.jooq.tables.Session.SESSION;

	/**
	 * The table Session_scan
	 */
	public static final SessionScan SESSION_SCAN = org.home.jooq.tables.SessionScan.SESSION_SCAN;

	/**
	 * The table Undefined_book
	 */
	public static final UndefinedBook UNDEFINED_BOOK = org.home.jooq.tables.UndefinedBook.UNDEFINED_BOOK;

	/**
	 * The table ignored_path_list
	 */
	public static final IgnoredPathList IGNORED_PATH_LIST = org.home.jooq.tables.IgnoredPathList.IGNORED_PATH_LIST;

	/**
	 * The table sqlite_sequence
	 */
	public static final SqliteSequence SQLITE_SEQUENCE = org.home.jooq.tables.SqliteSequence.SQLITE_SEQUENCE;
}
