package de.lezleoh.webscraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class LoginSourceforge {
	static String uri = "http://htmlunit.sourceforge.net";
	

	public static void main(String[] args) {
		//BrowserVersion browserVersion = new 
		WebClient webClient = new WebClient();
		webClient.getOptions().setUseInsecureSSL(true);
		//webClient.getBrowserVersion();
		HtmlPage page;
		try {
			page = (HtmlPage) webClient.getPage(uri);

			System.out.println("------Beginn Ausgabe der Webseite als Text:-----------");
			System.out.println(page.asText());
			System.out.println("------Ende Ausgabe der Webseite als Text:-----------");
			System.out.println();
			
			System.out.println("------Beginn Ausgabe der Webseite als XML:-----------");
			System.out.println(page.asXml());
			System.out.println("------Ende Ausgabe der Webseite als XML:-----------");
			System.out.println();
			
			

			
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webClient.close();

	}

}
