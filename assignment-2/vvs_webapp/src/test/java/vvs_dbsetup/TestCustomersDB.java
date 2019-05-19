package vvs_dbsetup;

import java.sql.SQLException;

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

	/* 
	 * d) after deleting a certain costumer, it’s possible to add it back without
	 * lifting exceptions;
	 */
	@Test
	public void deleteAndAddCustomerTest() throws ApplicationException {
		final int VAT = 197672337;
		final String NAME = "JOSE FARIA";
		final int PHONE = 914276732;

		CustomerService.INSTANCE.removeCustomer(VAT);
		CustomerService.INSTANCE.addCustomer(VAT, NAME, PHONE);

		assertEquals(VAT, CustomerService.INSTANCE.getCustomerByVat(VAT).vat);
	}

	/* 
	 * e) adding a new delivery increases the total number of all deliveries by one;
	 */
	@Test
	public void addDeliveriesByOneTest() throws ApplicationException {
		final int VAT = 197672337;

		SaleService.INSTANCE.addSale(VAT);
		
		SalesDTO sales = SaleService.INSTANCE.getSaleByCustomerVat(VAT);
		AddressesDTO addresses = CustomerService.INSTANCE.getAllAddresses(VAT);
		
		int before = SaleService.INSTANCE.getSalesDeliveryByVat(VAT).sales_delivery.size();

		SaleService.INSTANCE.addSaleDelivery(sales.sales.get(0).id, addresses.addrs.get(0).id);

		int after = SaleService.INSTANCE.getSalesDeliveryByVat(VAT).sales_delivery.size();

		assertEquals(before + 1, after);
	}
	
	/*
	 * Add two extra tests concerning the expected behaviour of sales 
	 */
	
	// 1) test update behavior on a sale
	@Test
	public void updateSaleTest() throws ApplicationException {
		final int VAT = 197672337;

		SaleService.INSTANCE.addSale(VAT);
		
		SalesDTO sales = SaleService.INSTANCE.getSaleByCustomerVat(VAT);
		
		SaleService.INSTANCE.updateSale(sales.sales.get(0).id);
		
		assertEquals("C", SaleService.INSTANCE.getSaleByCustomerVat(VAT).sales.get(0).statusId);
	}
	
	// 2) test if it's possible to add a sale on a existing VAT 
	@Test
	public void addSaleExistingVAT() throws ApplicationException {
		final int VAT = 197672337;
		
		SaleService.INSTANCE.addSale(VAT);
		
		assertEquals("O", SaleService.INSTANCE.getSaleByCustomerVat(VAT).sales.get(0).statusId);
	}
}
