package net.vksn.test;


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
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
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
	
	@After
	public void tearDown() throws ClassNotFoundException, SQLException, DatabaseUnitException {
		IDatabaseConnection con = getConnection();
		DatabaseOperation.DELETE_ALL.execute(con, getDataSet(con));
	}

	@SuppressWarnings("deprecation")
	private IDataSet getDataSet(IDatabaseConnection connection) {
		FlatXmlDataSet dataSet = null;
		
		try {	
			dataSet = new FlatXmlDataSet(getDataSetXmlPath());
			ITableFilter filter = new DatabaseSequenceFilter(connection);
			new FilteredDataSet(filter, dataSet);
			
		} catch (DataSetException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException("Cannot find dataset file: ");
		} catch (SQLException e) {
			throw new RuntimeException("Could not create filter " + e);
		}
		ReplacementDataSet replacementDataset = new ReplacementDataSet(dataSet);
		replacementDataset.addReplacementObject("[NULL]", null);
		return replacementDataset;
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
