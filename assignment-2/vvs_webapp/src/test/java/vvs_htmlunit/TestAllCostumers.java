package vvs_htmlunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

/**
 * get the ﬁrst customer listed in the List All Customers use case, then try to
 * insert it again and check if the expected error appears
 * 
 * Teste 1b
 * 
 * Obter o primeiro customer da lista no caso de uso de ListAllCustomers, e
 * depois tentar inserir-lo outra vez e verificar se o erro esperado aparece
 *
 */
public class TestAllCostumers {

	private static final String ADD_CUSTOMER_PAGE_CONTROLLER = "AddCustomerPageController";

	private static HtmlPage page;

	private static final String APPLICATION_URL = "http://localhost:8080/VVS_webappdemo/";

	private static final String USER_CASE_ALL_CUSTOMERS = "GetAllCustomersPageController";
	private static final String TABLE_ID_ALL_CUSTOMERS = "clients";

	private static final String INSERT_CLIENT_URF = "addCustomer.html";
	private static final String TITLE_INSERT = "Enter Name";
	private static final String TITLE_CUSTOMER_INFO = "Customer Info";

	@BeforeClass
	public static void setUpClass() throws Exception {

		page = SetupClass.setupClass();

		// CreateDatabase.main(new String[0]);
	}

	@Test
	public void testeAllCustomersInsert() throws IOException {
		HtmlTableRow primeiraLinha = getFirstRowTable();
		final String name = primeiraLinha.getCell(0).asText();
		final String phone = primeiraLinha.getCell(1).asText();
		final String vat = primeiraLinha.getCell(2).asText();

		insertClientPost(name, phone, vat);

	}

	private final String MENSAGEM_ERRO = "It was not possible to fulfill the request: Can't add customer with vat number ";

	private void insertClientPost(String name, String phone, String vat) throws IOException {
		HtmlPage reportPage;

		try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) {
			WebRequest req = new WebRequest(new java.net.URL(APPLICATION_URL + ADD_CUSTOMER_PAGE_CONTROLLER),
					HttpMethod.POST);

			String formData = String.format("vat=%s&designation=%s&phone=%s", vat, name, phone);
			req.setRequestBody(formData);
			
			reportPage = (HtmlPage) webClient.getPage(req);
		}

		String textReportPage = reportPage.asText();

		// Verificar se o titulo da pagina esta correto
		assertTrue(textReportPage.contains(TITLE_CUSTOMER_INFO));

		// Verificar se existe erro e se o erro esta certo

		assertTrue(textReportPage.contains(MENSAGEM_ERRO + vat));

	}

	/**
	 * Inserir um customer
	 * 
	 * Sem verificacao se foi inserido com sucesso
	 * 
	 * @param name
	 * @param phone
	 * @param vat
	 * @throws IOException
	 */
	@Deprecated
	@SuppressWarnings("unused")
	private void insertClient(final String name, final String phone, final String vat) throws IOException {

		// get a specific link
		HtmlAnchor addCustomerLink = page.getAnchorByHref(INSERT_CLIENT_URF);
		// click on it
		HtmlPage nextPage = (HtmlPage) addCustomerLink.openLinkInNewWindow();
		// check if title is the one expected
		assertEquals(TITLE_INSERT, nextPage.getTitleText());

		// get the page first form:
		HtmlForm addCustomerForm = nextPage.getForms().get(0);

		// place data at form
		HtmlInput vatInput = addCustomerForm.getInputByName("vat");
		vatInput.setValueAttribute(vat);
		HtmlInput designationInput = addCustomerForm.getInputByName("designation");
		designationInput.setValueAttribute(name);
		HtmlInput phoneInput = addCustomerForm.getInputByName("phone");
		phoneInput.setValueAttribute(phone);
		// submit form
		HtmlInput submit = addCustomerForm.getInputByName("submit");

		// check if report page includes the proper values
		HtmlPage reportPage = submit.click();
		String textReportPage = reportPage.asText();
		assertTrue(textReportPage.contains(vat));
		assertTrue(textReportPage.contains(name));
		assertTrue(textReportPage.contains(phone));

	}

	private HtmlTableRow getFirstRowTable() throws IOException {
		HtmlTable table = getTable();
		int linhaQueQuero = 1;
		for (final HtmlTableRow row : table.getRows()) {
			if (linhaQueQuero > 0) {
				linhaQueQuero--;
				continue;
			}
			return row;
		}
		return null;
	}

	private HtmlTable getTable() throws IOException {
		HtmlAnchor getCustomersLink = page.getAnchorByHref(USER_CASE_ALL_CUSTOMERS);
		HtmlPage nextPage = (HtmlPage) getCustomersLink.openLinkInNewWindow();

		return nextPage.getHtmlElementById(TABLE_ID_ALL_CUSTOMERS);

	}

}
