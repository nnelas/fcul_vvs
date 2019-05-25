package vvs_dbsetup;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

import org.junit.*;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

import static vvs_dbsetup.Utils.*;

import webapp.services.*;

public class TestCustomersDB {

	private static Destination dataSource;

	// the tracker is static because JUnit uses a separate Test instance for every
	// test method.
	private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

	@BeforeClass
	public static void setupClass() {
		startApplicationDatabaseForTesting();
		dataSource = DriverManagerDestination.with(DB_URL, DB_USERNAME, DB_PASSWORD);
	}

	@Before
	public void setup() throws SQLException {

		Operation initDBOperations = Operations.sequenceOf(DELETE_ALL, INSERT_CUSTOMER_ADDRESS_DATA);

		DbSetup dbSetup = new DbSetup(dataSource, initDBOperations);

		// Use the tracker to launch the DbSetup. This will speed-up tests
		// that do not not change the BD. Otherwise, just use dbSetup.launch();
		dbSetupTracker.launchIfNecessary(dbSetup);

	}

	@Test
	public void queryCustomerNumberTest() throws ApplicationException {
		// read-only test: unnecessary to re-launch setup after test has been run
		dbSetupTracker.skipNextLaunch();

		int expected = NUM_INIT_CUSTOMERS;
		int actual = CustomerService.INSTANCE.getAllCustomers().customers.size();

		assertEquals(expected, actual);
	}


}
