/**
 * This class is generated by jOOQ
 */
package org.home.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.home.jooq.DefaultSchema;
import org.home.jooq.Keys;
import org.home.jooq.tables.records.IgnoredPathListRecord;
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
public class IgnoredPathList extends TableImpl<IgnoredPathListRecord> {

	private static final long serialVersionUID = -300842262;

	/**
	 * The reference instance of <code>ignored_path_list</code>
	 */
	public static final IgnoredPathList IGNORED_PATH_LIST = new IgnoredPathList();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<IgnoredPathListRecord> getRecordType() {
		return IgnoredPathListRecord.class;
	}

	/**
	 * The column <code>ignored_path_list.id</code>.
	 */
	public final TableField<IgnoredPathListRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>ignored_path_list.file_path</code>.
	 */
	public final TableField<IgnoredPathListRecord, String> FILE_PATH = createField("file_path", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>ignored_path_list</code> table reference
	 */
	public IgnoredPathList() {
		this("ignored_path_list", null);
	}

	/**
	 * Create an aliased <code>ignored_path_list</code> table reference
	 */
	public IgnoredPathList(String alias) {
		this(alias, IGNORED_PATH_LIST);
	}

	private IgnoredPathList(String alias, Table<IgnoredPathListRecord> aliased) {
		this(alias, aliased, null);
	}

	private IgnoredPathList(String alias, Table<IgnoredPathListRecord> aliased, Field<?>[] parameters) {
		super(alias, DefaultSchema.DEFAULT_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<IgnoredPathListRecord> getPrimaryKey() {
		return Keys.PK_IGNORED_PATH_LIST;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<IgnoredPathListRecord>> getKeys() {
		return Arrays.<UniqueKey<IgnoredPathListRecord>>asList(Keys.PK_IGNORED_PATH_LIST);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IgnoredPathList as(String alias) {
		return new IgnoredPathList(alias, this);
	}

	/**
	 * Rename this table
	 */
	public IgnoredPathList rename(String name) {
		return new IgnoredPathList(name, null);
	}
}
