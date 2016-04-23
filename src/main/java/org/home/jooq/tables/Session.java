/**
 * This class is generated by jOOQ
 */
package org.home.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.home.jooq.DefaultSchema;
import org.home.jooq.Keys;
import org.home.jooq.tables.records.SessionRecord;
import org.jooq.Field;
import org.jooq.Identity;
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
public class Session extends TableImpl<SessionRecord> {

	private static final long serialVersionUID = 803561946;

	/**
	 * The reference instance of <code>Session</code>
	 */
	public static final Session SESSION = new Session();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<SessionRecord> getRecordType() {
		return SessionRecord.class;
	}

	/**
	 * The column <code>Session.record_id</code>.
	 */
	public final TableField<SessionRecord, Integer> RECORD_ID = createField("record_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>Session.session_name</code>.
	 */
	public final TableField<SessionRecord, String> SESSION_NAME = createField("session_name", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>Session.id</code>.
	 */
	public final TableField<SessionRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>Session.home_dir</code>.
	 */
	public final TableField<SessionRecord, String> HOME_DIR = createField("home_dir", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Session.user_name</code>.
	 */
	public final TableField<SessionRecord, String> USER_NAME = createField("user_name", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Session.os_name</code>.
	 */
	public final TableField<SessionRecord, String> OS_NAME = createField("os_name", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Session.os_version</code>.
	 */
	public final TableField<SessionRecord, String> OS_VERSION = createField("os_version", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Session.os_arch</code>.
	 */
	public final TableField<SessionRecord, String> OS_ARCH = createField("os_arch", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * The column <code>Session.temp_directory</code>.
	 */
	public final TableField<SessionRecord, String> TEMP_DIRECTORY = createField("temp_directory", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>Session</code> table reference
	 */
	public Session() {
		this("Session", null);
	}

	/**
	 * Create an aliased <code>Session</code> table reference
	 */
	public Session(String alias) {
		this(alias, SESSION);
	}

	private Session(String alias, Table<SessionRecord> aliased) {
		this(alias, aliased, null);
	}

	private Session(String alias, Table<SessionRecord> aliased, Field<?>[] parameters) {
		super(alias, DefaultSchema.DEFAULT_SCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<SessionRecord, Integer> getIdentity() {
		return Keys.IDENTITY_SESSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<SessionRecord> getPrimaryKey() {
		return Keys.PK_SESSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<SessionRecord>> getKeys() {
		return Arrays.<UniqueKey<SessionRecord>>asList(Keys.PK_SESSION);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Session as(String alias) {
		return new Session(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Session rename(String name) {
		return new Session(name, null);
	}
}
