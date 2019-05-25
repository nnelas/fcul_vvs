package vvs_HtmlUnit;

import static org.junit.Assert.assertEquals;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

class SetupClass {

	private static final String APPLICATION_URL = "http://localhost:8080/VVS_webappdemo/";
	
	protected static HtmlPage setupClass() throws Exception{
		try (final WebClient webClient = new WebClient(BrowserVersion.getDefault())) {
			webClient.setJavaScriptTimeout(15000);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setCssEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setThrowExceptionOnScriptError(false);

			HtmlPage page = webClient.getPage(APPLICATION_URL);
			assertEquals(200, page.getWebResponse().getStatusCode());
			return page;
		}
	}
}
