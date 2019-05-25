package vvs_HtmlUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * Class que adicionar um address a um cliente ja existente Verifica se foi
 * adicionado com sucesso no GetCustomerPageController Verifica se o cliente tem
 * um address a mais, isto e, se o numero total de address do cliente foi
 * aumentado em 1
 * 
 * @author Simao Ferreira
 *
 */
// insert a new address for an existing customer, then the table of addresses of
// that client includes that address and its total row size increases by one;
public class AddressTest {

	private static final String APPLICATION_URL = "http://localhost:8080/VVS_webappdemo/";
	private static HtmlPage page;

	private static final String NPC = "197672337";
	private static final String ADDRESS = "Rua das Bananas";
	private static final String DOOR = "90";
	private static final String POSTAL_CODE = "2670-356";
	private static final String LOCALITY = "Lisboa";

	@BeforeClass
	public static void setUpClass() throws Exception {

		page = SetupClass.setupClass();

		// CreateDatabase.main(new String[0]);
	}

	// insert a new address for an existing customer, then the table of addresses of
	// that client includes that address and its total row size increases by one;

	@Test
	public void insertAddressCheckOnTable() throws IOException {

		int numeroAddress = getNumeroAddressByNPC();
		insertAddress();
		verificarTabela();
		assertEquals(numeroAddress + 1, getNumeroAddressByNPC());
	}

	/**
	 * Verifica se a tabela tem o novo address
	 * 
	 * @throws IOException
	 */
	private void verificarTabela() throws IOException {
		HtmlPage reportPage;

		// Build a GET request
		try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) {
			java.net.URL url = new java.net.URL(APPLICATION_URL + "GetCustomerPageController");
			WebRequest requestSettings = new WebRequest(url, HttpMethod.GET);

			// Set the request parameters
			requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
			requestSettings.getRequestParameters().add(new NameValuePair("vat", NPC));
			requestSettings.getRequestParameters().add(new NameValuePair("submit", "Get+Customer"));

			reportPage = webClient.getPage(requestSettings);
			assertEquals(HttpMethod.GET, reportPage.getWebResponse().getWebRequest().getHttpMethod());
		}

		String expected = ADDRESS + '\t' + DOOR + '\t' + POSTAL_CODE + '\t' + LOCALITY;
		assertTrue(reportPage.asText().contains(expected));

	}

	/**
	 * ve o numero de address que o cliente VAT tem
	 * 
	 * @return numero de addrress que o client Vat tem
	 * @throws IOException
	 */
	private int getNumeroAddressByNPC() throws IOException {
		HtmlPage reportPage;

		// Build a GET request
		try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) {
			java.net.URL url = new java.net.URL(APPLICATION_URL + "GetCustomerPageController");
			WebRequest requestSettings = new WebRequest(url, HttpMethod.GET);

			// Set the request parameters
			requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
			requestSettings.getRequestParameters().add(new NameValuePair("vat", NPC));
			requestSettings.getRequestParameters().add(new NameValuePair("submit", "Get+Customer"));

			reportPage = webClient.getPage(requestSettings);
			assertEquals(HttpMethod.GET, reportPage.getWebResponse().getWebRequest().getHttpMethod());
		}

		if (!reportPage.asXml().contains("table")) {
			return 0;
		} else {
			Pattern padrao = Pattern.compile("<tr class=\"w3-black\">");
			Matcher matcher = padrao.matcher(reportPage.asXml());
			int count = -1;
			while (matcher.find())
				count++;
			return count;
		}
	}

	/**
	 * Inserir um address num customer ja existente
	 * 
	 * @throws IOException
	 */
	private void insertAddress() throws IOException {
		HtmlAnchor addAddressToCustomerLink = page.getAnchorByHref("addAddressToCustomer.html");
		HtmlPage nextPage = (HtmlPage) addAddressToCustomerLink.openLinkInNewWindow();

		// Verifica o titulo ta pag
		assertEquals("Enter Address", nextPage.getTitleText());

		// Obter o forma da pagina:
		HtmlForm addAddressToCustomerForm = nextPage.getForms().get(0);

		// preencher o formulario
		HtmlInput vatInput = addAddressToCustomerForm.getInputByName("vat");
		vatInput.setValueAttribute(NPC);

		HtmlInput addressInput = addAddressToCustomerForm.getInputByName("address");
		addressInput.setValueAttribute(ADDRESS);

		HtmlInput doorInput = addAddressToCustomerForm.getInputByName("door");
		doorInput.setValueAttribute(DOOR);

		HtmlInput postalCodeInput = addAddressToCustomerForm.getInputByName("postalCode");
		postalCodeInput.setValueAttribute(POSTAL_CODE);

		HtmlInput localityInput = addAddressToCustomerForm.getInputByName("locality");
		localityInput.setValueAttribute(LOCALITY);

		// submit form
		HtmlInput submit = addAddressToCustomerForm.getInputByValue("Insert");

		// check if report page includes the proper values
		HtmlPage reportPage = submit.click();
		String textReportPage = reportPage.asText();
		assertTrue(textReportPage.contains(NPC));
		assertTrue(textReportPage.contains(ADDRESS));
		assertTrue(textReportPage.contains(DOOR));
		assertTrue(textReportPage.contains(POSTAL_CODE));
		assertTrue(textReportPage.contains(LOCALITY));
	}

}
