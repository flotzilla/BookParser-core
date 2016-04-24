/**
 * This class is generated by jOOQ
 */
package org.home.jooq.tables.records;


import java.math.BigDecimal;

import javax.annotation.Generated;

import org.home.jooq.tables.ScanEmptyBook;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


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
public class ScanEmptyBookRecord extends TableRecordImpl<ScanEmptyBookRecord> implements Record2<BigDecimal, String> {

	private static final long serialVersionUID = -104167791;

	/**
	 * Setter for <code>Scan_Empty_Book.scan_id</code>.
	 */
	public void setScanId(BigDecimal value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>Scan_Empty_Book.scan_id</code>.
	 */
	public BigDecimal getScanId() {
		return (BigDecimal) getValue(0);
	}

	/**
	 * Setter for <code>Scan_Empty_Book.book_scan_id</code>.
	 */
	public void setBookScanId(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>Scan_Empty_Book.book_scan_id</code>.
	 */
	public String getBookScanId() {
		return (String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<BigDecimal, String> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<BigDecimal, String> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<BigDecimal> field1() {
		return ScanEmptyBook.SCAN_EMPTY_BOOK.SCAN_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return ScanEmptyBook.SCAN_EMPTY_BOOK.BOOK_SCAN_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal value1() {
		return getScanId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getBookScanId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScanEmptyBookRecord value1(BigDecimal value) {
		setScanId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScanEmptyBookRecord value2(String value) {
		setBookScanId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ScanEmptyBookRecord values(BigDecimal value1, String value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ScanEmptyBookRecord
	 */
	public ScanEmptyBookRecord() {
		super(ScanEmptyBook.SCAN_EMPTY_BOOK);
	}

	/**
	 * Create a detached, initialised ScanEmptyBookRecord
	 */
	public ScanEmptyBookRecord(BigDecimal scanId, String bookScanId) {
		super(ScanEmptyBook.SCAN_EMPTY_BOOK);

		setValue(0, scanId);
		setValue(1, bookScanId);
	}
}