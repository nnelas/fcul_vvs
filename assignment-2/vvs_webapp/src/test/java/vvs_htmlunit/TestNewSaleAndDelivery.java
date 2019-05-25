package vvs_htmlunit;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

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
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class TestNewSaleAndDelivery {

    private static HtmlPage page;
    
    @BeforeClass
	public static void setUpClass() throws Exception {
		page = SetupClass.setupClass();
	}

    /*
     * d) create a new sale for an existing customer,
     * insert a delivery for that sale and then show the sale delivery.
     * Check that all intermediate pages have the expected information.
     *
     * Added id="addresses" on addSaleDelivery.jsp to obtain that table inside this method
     * Added id="deliveries" on ShowSalesDelivery.jsp to obtain that table inside this method
     * Added id="sales" on SalesInfo.jsp to obtain that table inside this method
     */
    @Test
    public void insertSaleDeliveryTest() throws IOException {

        final String NPC = "168027852";

        String saleId = createSale(NPC);
        insertAddress(NPC, "ADDRESS", "DOOR", "POSTALCODE", "LOCALITY");

        // get a specific link
        HtmlAnchor addCustomerLink = page.getAnchorByHref("saleDeliveryVat.html");
        // click on it
        HtmlPage nextPage = (HtmlPage) addCustomerLink.openLinkInNewWindow();
        // check if title is the one expected
        assertEquals("Enter Name", nextPage.getTitleText());

        // get the page first form:
        HtmlForm addDeliveryToSaleForm = nextPage.getForms().get(0);

        // place data at form
        HtmlInput vatInput = addDeliveryToSaleForm.getInputByName("vat");
        vatInput.setValueAttribute(NPC);

        // submit form
        HtmlInput submit = addDeliveryToSaleForm.getInputByValue("Get Customer");

        // check if report page includes the proper values
        HtmlPage reportPage = submit.click();
        String textReportPage = reportPage.asText();
        assertTrue(textReportPage.contains(NPC));

        // Build a GET request
        WebRequest request = new WebRequest(new java.net.URL(SetupClass.APPLICATION_URL +
                "AddSaleDeliveryPageController"),
                HttpMethod.GET);

        // Set the request parameters
        request.setRequestParameters(new ArrayList<NameValuePair>());
        request.getRequestParameters().add(new NameValuePair("vat", NPC));
        request.getRequestParameters().add(new NameValuePair("submit", "Get+Sales"));

        try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) {
            reportPage = webClient.getPage(request);
            assertEquals(HttpMethod.GET,
                    reportPage.getWebResponse().getWebRequest().getHttpMethod());
        }

        String addrId = "";

        final HtmlTable table = reportPage.getHtmlElementById("addresses");
        for (final HtmlTableRow row : table.getRows()) {
            addrId = row.getCell(0).asText();
        }

        // get the page first form:
        addDeliveryToSaleForm = reportPage.getForms().get(0);

        // place data at form
        HtmlInput addrInput = addDeliveryToSaleForm.getInputByName("addr_id");
        addrInput.setValueAttribute(addrId);
        HtmlInput saleInput = addDeliveryToSaleForm.getInputByName("sale_id");
        saleInput.setValueAttribute(saleId);

        // submit form
        submit = addDeliveryToSaleForm.getInputByValue("Insert");

        // check if report page includes the proper values
        reportPage = submit.click();
        textReportPage = reportPage.asText();
        assertTrue(textReportPage.contains(NPC));

        // Build a GET request
        request = new WebRequest(new java.net.URL(SetupClass.APPLICATION_URL +
                "GetSaleDeliveryPageController"),
                HttpMethod.GET);

        // Set the request parameters
        request.setRequestParameters(new ArrayList<NameValuePair>());
        request.getRequestParameters().add(new NameValuePair("vat", NPC));
        request.getRequestParameters().add(new NameValuePair("submit", "Get+Sales"));

        try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) {
            reportPage = webClient.getPage(request);
            assertEquals(HttpMethod.GET,
                    reportPage.getWebResponse().getWebRequest().getHttpMethod());
        }

        final HtmlTable table2 = reportPage.getHtmlElementById("deliveries");
        HtmlTableRow aux = null;
        for (final HtmlTableRow row : table2.getRows()) {
            aux = row;
        }

        assertTrue(aux.getCell(1).asText().equals(saleId));
        assertTrue(aux.getCell(2).asText().equals(addrId));

    }

    private String createSale(String NPC) throws IOException {

        // get a specific link
        HtmlAnchor addSaleLink = page.getAnchorByHref("addSale.html");
        // click on it
        HtmlPage nextPage = (HtmlPage) addSaleLink.openLinkInNewWindow();
        // check if title is the one expected
        assertEquals("New Sale", nextPage.getTitleText());

        // get the page first form:
        HtmlForm addSaleForm = nextPage.getForms().get(0);

        // place data at form
        HtmlInput vatInput = addSaleForm.getInputByName("customerVat");
        vatInput.setValueAttribute(NPC);

        // submit form
        HtmlInput submit = addSaleForm.getInputByValue("Add Sale");

        // check if report page includes the proper values
        HtmlPage reportPage = submit.click();
        String textReportPage = reportPage.asText();
        assertTrue(textReportPage.contains(NPC));

        // Build a GET request
        WebRequest request = new WebRequest(new java.net.URL(SetupClass.APPLICATION_URL + "GetSalePageController"),
                HttpMethod.GET);

        // Set the request parameters
        request.setRequestParameters(new ArrayList<NameValuePair>());
        request.getRequestParameters().add(new NameValuePair("customerVat", NPC));
        request.getRequestParameters().add(new NameValuePair("submit", "Get+Sales"));

        try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) {
            reportPage = (HtmlPage) webClient.getPage(request);
            assertEquals(HttpMethod.GET, reportPage.getWebResponse().getWebRequest().getHttpMethod());
        }

        HtmlTableRow aux = null;

        final HtmlTable table = reportPage.getHtmlElementById("sales");
        for (final HtmlTableRow row : table.getRows()) {
            aux = row;
        }

        return aux.getCell(0).asText();
    }

    private void insertAddress(String npc, String address, String door, String postalcode, String locality)
            throws IOException {
        // get a specific link
        HtmlAnchor addAddressLink = page.getAnchorByHref("addAddressToCustomer.html");
        // click on it
        HtmlPage nextPage = (HtmlPage) addAddressLink.openLinkInNewWindow();
        // check if title is the one expected
        assertEquals("Enter Address", nextPage.getTitleText());

        // get the page first form:
        HtmlForm addAddressForm = nextPage.getForms().get(0);
        // place data at form
        HtmlInput vatInput = addAddressForm.getInputByName("vat");
        vatInput.setValueAttribute(npc);
        HtmlInput addressInput = addAddressForm.getInputByName("address");
        addressInput.setValueAttribute(address);
        HtmlInput doorInput = addAddressForm.getInputByName("door");
        doorInput.setValueAttribute(door);
        HtmlInput postalCodeInput = addAddressForm.getInputByName("postalCode");
        postalCodeInput.setValueAttribute(postalcode);
        HtmlInput localityInput = addAddressForm.getInputByName("locality");
        localityInput.setValueAttribute(locality);
        // submit form
        HtmlInput submit = addAddressForm.getInputByValue("Insert");

        // check if report page includes the proper values
        HtmlPage reportPage = submit.click();
        String textReportPage = reportPage.asText();
        assertTrue(textReportPage.contains(npc));
        assertTrue(textReportPage.contains(address));
        assertTrue(textReportPage.contains(door));
        assertTrue(textReportPage.contains(postalcode));
        assertTrue(textReportPage.contains(locality));

    }
}
