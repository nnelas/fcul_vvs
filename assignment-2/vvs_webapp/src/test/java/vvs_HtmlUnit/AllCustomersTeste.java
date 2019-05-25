package vvs_HtmlUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

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
public class AllCustomersTeste {

	private static HtmlPage page;

	private static final String USER_CASE_ALL_CUSTOMERS = "GetAllCustomersPageController";
	private static final String TABLE_ID_ALL_CUSTOMERS = "clients";

	private static final String INSERT_CLIENT_URF = "addCustomer.html";
	private static final String TITLE_INSERT = "Enter Name";

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

		insertClient(name, phone, vat);

	}

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
