package vvs_webapp;

import static org.junit.Assert.*;
import org.junit.*;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

import java.net.MalformedURLException;

import java.io.*;
import java.util.*;

public class TestIndex {
	
	private static final String APPLICATION_URL = "http://localhost:8080/VVS_webappdemo/";
	private static final int APPLICATION_NUMBER_USE_CASES = 11;

	private static HtmlPage page;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) { 
	
			// possible configurations needed to prevent JUnit tests to fail for complex HTML pages
            webClient.setJavaScriptTimeout(15000);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
		    
			page = webClient.getPage(APPLICATION_URL);
			assertEquals(200, page.getWebResponse().getStatusCode()); // OK status
		}
	}
	
	@Test
	public void indexTest() throws Exception {
        assertEquals("WebAppDemo Menu", page.getTitleText());

        final String pageAsXml = page.asXml();
        assertTrue(pageAsXml.contains("<div class=\"w3-container w3-blue-grey w3-center w3-allerta\" id=\"body\">"));

        final String pageAsText = page.asText();
        assertTrue(pageAsText.contains("WebAppDemo Menu"));
	}
	
	@Test
	public void numberOfOptionsTest() throws Exception { 
         List<DomElement> inputs = page.getElementsById("botao2");  // get list of case uses
         assertTrue(inputs.size()==APPLICATION_NUMBER_USE_CASES);
	}
	
	// not testing, just to show how to access tables inside the HTML
	@Test
	public void tablesTest() throws MalformedURLException {
		HtmlAnchor getCustomersLink = page.getAnchorByHref("GetAllCustomersPageController");
		HtmlPage nextPage = (HtmlPage) getCustomersLink.openLinkInNewWindow();
		
		final HtmlTable table = nextPage.getHtmlElementById("clients");
		System.out.println("---------------------------------");
		for (final HtmlTableRow row : table.getRows()) {
		    System.out.println("Found row");
		    for (final HtmlTableCell cell : row.getCells()) {
		       System.out.println("   Found cell: " + cell.asText());
		    }
		}
		System.out.println("---------------------------------");
	}
	
	// Eg of testing a GET request.
	// For a POST request cf. stackoverflow.com/questions/30687614
	@Test
	public void parametersGetTest() throws IOException {
		
		HtmlPage reportPage;
		
		// Build a GET request
		try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) { 
			java.net.URL url = new java.net.URL(APPLICATION_URL+"GetCustomerPageController");
			WebRequest requestSettings = new WebRequest(url, HttpMethod.GET);

			// Set the request parameters
			requestSettings.setRequestParameters(new ArrayList<NameValuePair>());
			requestSettings.getRequestParameters().add(new NameValuePair("vat", "197672337"));
			requestSettings.getRequestParameters().add(new NameValuePair("submit", "Get+Customer"));

			reportPage = webClient.getPage(requestSettings);
			assertEquals(HttpMethod.GET, reportPage.getWebResponse().getWebRequest().getHttpMethod());		
		}
		
		assertTrue(reportPage.asXml().contains("JOSE FARIA"));
		
		// to check GET parameter's
//		List<NameValuePair> parameters = reportPage.getWebResponse().getWebRequest().getRequestParameters();
//		for (NameValuePair parameter : parameters) {
//			System.out.println(parameter.getName() + " = " + parameter.getValue());
//		}
	}

}





