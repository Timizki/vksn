package net.vksn.bedrock;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

/**
 * Base class for test that needs databse
 * 
 * @author timii
 * 
 */
public abstract class AbstractDatabaseTestCase {

	@Before
	public void setUpDatabase() {
		try {
			final IDatabaseConnection connection = getConnection();
			final IDataSet dataSet = getDataSet(connection);
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find needed classes for hsqldb "
					+ e);
		} catch (DatabaseUnitException e) {
			throw new RuntimeException("Could not initialize database " + e);
		}
	}

	private IDataSet getDataSet(IDatabaseConnection connection) {
		FlatXmlDataSet dataSet = null;
		try {
			ITableFilter filter = new DatabaseSequenceFilter(connection);
			dataSet = new FlatXmlDataSet(getDataSetXmlPath());
			new FilteredDataSet(filter, dataSet);
		} catch (DataSetException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException("Cannot find dataset file: ");
		} catch (SQLException e) {
			throw new RuntimeException("Could not create filter " + e);
		}
		return dataSet;
	}

	private InputStream getDataSetXmlPath() {
		String className = getClass().getSimpleName();
		if (className.endsWith("Test")) {
			className = className.substring(0, className.length() - 4);
		}

		String resourceName = className + "-dataset.xml";
		InputStream in = this.getClass().getResourceAsStream(resourceName);

		if (in == null) {
			throw new RuntimeException("Could not find dataset xml: "
					+ resourceName);
		}

		return in;
	}

	private IDatabaseConnection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		try {
			return new DatabaseConnection(DriverManager.getConnection(
					"jdbc:hsqldb:mem:test", "sa", ""));
		} catch (DatabaseUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
