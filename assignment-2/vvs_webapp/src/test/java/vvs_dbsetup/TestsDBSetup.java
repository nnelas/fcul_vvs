package vvs_dbsetup;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static vvs_dbsetup.Utils.DB_PASSWORD;
import static vvs_dbsetup.Utils.DB_URL;
import static vvs_dbsetup.Utils.DB_USERNAME;
import static vvs_dbsetup.Utils.DELETE_ALL;
import static vvs_dbsetup.Utils.INSERT_CUSTOMER_ADDRESS_DATA;
import static vvs_dbsetup.Utils.startApplicationDatabaseForTesting;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

import webapp.services.AddressesDTO;
import webapp.services.ApplicationException;
import webapp.services.CustomerService;
import webapp.services.SaleService;
import webapp.services.SalesDTO;

public class TestsDBSetup {
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

	/*
	 * a)
	 *
	 * after the update of a costumer contact, that information should be properly
	 * saved;
	 */
	@Test
	public void updateCustomerContact() throws ApplicationException {
		final int vat = 197672337;
		final int newContact = 123456789;

		//Actualiza o contacto do customer
		CustomerService.INSTANCE.updateCustomerPhone(vat, newContact);

		//Recupera o customer e valida que o contacto foi actualizado
		assertEquals(newContact, CustomerService.INSTANCE.getCustomerByVat(vat).phoneNumber);
	}

	/*
	 * b) after deleting all but one costumer, the list of all customers should have
	 * only that remaining customer;
	 */
	@Test
	public void deleteButOne() throws ApplicationException {
		final int VAT = 197672337;

		//Recupera a listagem de todos os utilizadores que não tenho o VAT predefinido
		List<Integer> vats = CustomerService.INSTANCE.getAllCustomers().customers.stream()
				.filter(customer -> customer.vat != VAT).map(customer -> customer.vat).collect(Collectors.toList());

		//Apaga todos esses utilizadores
		for (Integer vat : vats)
			CustomerService.INSTANCE.removeCustomer(vat);

		//Recupera o utilizador com o VAT predefinido e valida o seu VAT
		assertEquals(VAT, CustomerService.INSTANCE.getCustomerByVat(VAT).vat);
		
		//Valida que apenas existe um customer
		assertEquals(1, CustomerService.INSTANCE.getAllCustomers().customers.size());
	}

	/*
	 * c) after deleting a certain customer, its deliveries should be removed from
	 * the database;
	 */
	@Test
	public void deleteACustomerAndCheckDeliveries() throws ApplicationException {
		final int VAT = 197672337;

		//Adiciona uma sale ao VAT
		SaleService.INSTANCE.addSale(VAT);

		//Recupera essa sale
		SalesDTO sales = SaleService.INSTANCE.getSaleByCustomerVat(VAT);
		
		//Recupera todos os endereços do customer
		AddressesDTO addresses = CustomerService.INSTANCE.getAllAddresses(VAT);
		
		//Cria uma sale service com a sale e o address
		SaleService.INSTANCE.addSaleDelivery(sales.sales.get(0).id, addresses.addrs.get(0).id);
		
		//Conta numero de deliveries
		int numOfDeliveries = SaleService.INSTANCE.getSalesDeliveryByVat(VAT).sales_delivery.size();

		// valido que existe uma entrega
		assertEquals(1, numOfDeliveries);

		// removo o customer
		CustomerService.INSTANCE.removeCustomer(VAT);

		// valido que depois de removido já não existem entregas para esse customer
		assertEquals(0, SaleService.INSTANCE.getSalesDeliveryByVat(VAT).sales_delivery.size());
	}

	/*
	 * d) after deleting a certain customer, it’s possible to add it back without
	 * lifting exceptions;
	 */
	@Test
	public void deleteAndAddCustomerTest() throws ApplicationException {
		final int VAT = 197672337;
		final String NAME = "JOSE FARIA";
		final int PHONE = 914276732;

		//Remove o customer com este VAT
		CustomerService.INSTANCE.removeCustomer(VAT);

		//Valida que o customer foi mesmo apagado
		assertThrows(ApplicationException.class, () -> CustomerService.INSTANCE.getCustomerByVat(VAT));
		
		//Adiciona o mesmo customer
		CustomerService.INSTANCE.addCustomer(VAT, NAME, PHONE);

		//Valida que todos os dados foram bem inseridos
		assertEquals(NAME, CustomerService.INSTANCE.getCustomerByVat(VAT).designation);
		assertEquals(VAT, CustomerService.INSTANCE.getCustomerByVat(VAT).vat);
		assertEquals(PHONE, CustomerService.INSTANCE.getCustomerByVat(VAT).phoneNumber);
	}

	/*
	 * e) adding a new delivery increases the total number of all deliveries by one;
	 */
	@Test
	public void addDeliveriesByOneTest() throws ApplicationException {
		final int VAT = 197672337;

		//Adiciona uma nova compra
		SaleService.INSTANCE.addSale(VAT);
		
		//Recupera todas as compras de um customer
		SalesDTO sales = SaleService.INSTANCE.getSaleByCustomerVat(VAT);
		
		//Recupera todos os endereços de um customer
		AddressesDTO addresses = CustomerService.INSTANCE.getAllAddresses(VAT);

		//Conta o número de encomendas antes de adicionar uma nova
		int before = SaleService.INSTANCE.getSalesDeliveryByVat(VAT).sales_delivery.size();

		//Adiciona uma nova encomenda
		SaleService.INSTANCE.addSaleDelivery(sales.sales.get(0).id, addresses.addrs.get(0).id);

		//Valida que o numero de encomendas aumentou 1
		assertEquals(before + 1, SaleService.INSTANCE.getSalesDeliveryByVat(VAT).sales_delivery.size());
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

	// 2) test if it's possible to add a sale with a non existing VAT inside DB
	@Test
	public void addSaleExistingVAT() throws ApplicationException {
		final int VAT = 217173535;

		//Valida que adicionar uma compra com um VAT que não exita lança um erro
		assertThrows(ApplicationException.class, () -> {
			SaleService.INSTANCE.addSale(VAT);
		});
	}

}
