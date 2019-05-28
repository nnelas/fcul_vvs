package vvs_htmlunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

/**
 * create a new customer, them remove him, and 
 * check if the list of all clients does not change
 * 
 * Teste 1c
 * 
 */
public class TestInsertAndRemove {

	private static final String GET_ALL_CUSTOMERS_PAGE_CONTROLLER = "GetAllCustomersPageController";
	
	private static final String REMOVE_CUSTOMER_PAGE_CONTROLLER = "RemoveCustomerPageController";

	private static final String ADD_CUSTOMER_TO_CUSTOMER_HTML = "addCustomer.html";
	
	private static HtmlPage page;

	private static final String APPLICATION_URL = "http://localhost:8080/VVS_webappdemo/";
	
	private static final String NPC = "100999921";
	private static final String DESIGNATION = "FCUL";
	private static final String PHONE = "217500000";

	@BeforeClass
	public static void setUpClass() throws Exception {
		page = SetupClass.setupClass();
	}
	
	@Test
	public void insertAndRemoveClientTest() throws IOException {
		
		HtmlPage reportPage;
		
		// get a specific link
		HtmlAnchor addCustomerLink = page.getAnchorByHref(ADD_CUSTOMER_TO_CUSTOMER_HTML);
		// click on it
		HtmlPage nextPage = (HtmlPage) addCustomerLink.openLinkInNewWindow();
		// check if title is the one expected
		assertEquals("Enter Name", nextPage.getTitleText());

		// get the page first form:
		HtmlForm addCustomerForm = nextPage.getForms().get(0);

		// place data at form
		HtmlInput vatInput = addCustomerForm.getInputByName("vat");
		vatInput.setValueAttribute(NPC);
		HtmlInput designationInput = addCustomerForm.getInputByName("designation");
		designationInput.setValueAttribute(DESIGNATION);
		HtmlInput phoneInput = addCustomerForm.getInputByName("phone");
		phoneInput.setValueAttribute(PHONE);
		
		//query params for add customer request
        String addBody = String.format("vat=%s&designation=%s&phone=%s", NPC, DESIGNATION, PHONE);
                
        //create request with body
        WebRequest addRequest =
            new WebRequest(new java.net.URL(APPLICATION_URL+"AddCustomerPageController"),
                           HttpMethod.POST);
        addRequest.setRequestBody(addBody);
        
        //do the request and get result page
        try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) { 
            reportPage = (HtmlPage) webClient.getPage(addRequest);
        }

		// check if report page includes the proper values
        String textReportPage = reportPage.asText();		
		assertTrue(textReportPage.contains(DESIGNATION));
		assertTrue(textReportPage.contains(PHONE));

		// at index, goto Remove case use and remove the previous client
		HtmlAnchor removeCustomerLink = page.getAnchorByHref(REMOVE_CUSTOMER_PAGE_CONTROLLER);
		nextPage = (HtmlPage) removeCustomerLink.openLinkInNewWindow();
		assertTrue(nextPage.asText().contains(NPC));

		HtmlForm removeCustomerForm = nextPage.getForms().get(0);
		vatInput = removeCustomerForm.getInputByName("vat");
		vatInput.setValueAttribute(NPC);
		
		//query params for remove request
		String removeBody = String.format("vat=%s&submit=Remove", NPC);
		
		//prepare remove request
		WebRequest removeRequest = 
				new WebRequest(new java.net.URL(APPLICATION_URL+REMOVE_CUSTOMER_PAGE_CONTROLLER),
	                           HttpMethod.POST);
		removeRequest.setRequestBody(removeBody);
	        
        //do the request and get result page
        try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) { 
            reportPage = (HtmlPage) webClient.getPage(removeRequest);
        }

		// now check that the new client was erased
		HtmlAnchor getCustomersLink = page.getAnchorByHref(GET_ALL_CUSTOMERS_PAGE_CONTROLLER);
		nextPage = (HtmlPage) getCustomersLink.openLinkInNewWindow();
		assertFalse(nextPage.asText().contains(NPC));
	}
	
}
