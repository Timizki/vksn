package net.vksn.test;

import java.io.InputStream;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.LowerCaseDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.xml.sax.InputSource;

/**
 * Base class for test that needs databse
 * 
 * @author timii
 * 
 */
public abstract class AbstractDatabaseTestCase {
	private IDatabaseConnection connection;

	@Before
	public void setUpDatabase() {
		try {
			connection = getConnection();

			final IDataSet dataSet = getDataSet(connection);
			DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (DatabaseUnitException e) {
			throw new RuntimeException("Could not initialize database!",
					e.getCause());
		}
	}

	private IDataSet getDataSet(IDatabaseConnection connection) {
		IDataSet dataSet = null;

		try {
			InputSource xmlSource = new InputSource(getDataSetXmlPath());
			FlatXmlProducer flatXmlProducer = new FlatXmlProducer(xmlSource,
					false, true, false);

			dataSet = new FlatXmlDataSet(flatXmlProducer);
			ITableFilter filter = new DatabaseSequenceFilter(connection);
			dataSet = new FilteredDataSet(filter, dataSet);
			ReplacementDataSet replacementDataset = new ReplacementDataSet(
					dataSet);
			replacementDataset.addReplacementObject("[NULL]", null);
			return new LowerCaseDataSet(replacementDataset);
		} catch (DataSetException e) {
			throw new RuntimeException(e.getCause());
		} catch (SQLException e) {
			throw new RuntimeException("Could not create filter " + e);
		}
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

	protected abstract IDatabaseConnection getConnection() throws SQLException;
}
