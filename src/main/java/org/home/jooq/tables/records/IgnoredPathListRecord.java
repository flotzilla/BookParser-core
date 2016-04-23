/**
 * This class is generated by jOOQ
 */
package org.home.jooq.tables.records;


import javax.annotation.Generated;

import org.home.jooq.tables.IgnoredPathList;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class IgnoredPathListRecord extends UpdatableRecordImpl<IgnoredPathListRecord> implements Record2<Integer, String> {

	private static final long serialVersionUID = 2038005726;

	/**
	 * Setter for <code>ignored_path_list.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ignored_path_list.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>ignored_path_list.file_path</code>.
	 */
	public void setFilePath(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ignored_path_list.file_path</code>.
	 */
	public String getFilePath() {
		return (String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, String> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<Integer, String> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return IgnoredPathList.IGNORED_PATH_LIST.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return IgnoredPathList.IGNORED_PATH_LIST.FILE_PATH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getFilePath();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IgnoredPathListRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IgnoredPathListRecord value2(String value) {
		setFilePath(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IgnoredPathListRecord values(Integer value1, String value2) {
		value1(value1);
		value2(value2);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached IgnoredPathListRecord
	 */
	public IgnoredPathListRecord() {
		super(IgnoredPathList.IGNORED_PATH_LIST);
	}

	/**
	 * Create a detached, initialised IgnoredPathListRecord
	 */
	public IgnoredPathListRecord(Integer id, String filePath) {
		super(IgnoredPathList.IGNORED_PATH_LIST);

		setValue(0, id);
		setValue(1, filePath);
	}
}
