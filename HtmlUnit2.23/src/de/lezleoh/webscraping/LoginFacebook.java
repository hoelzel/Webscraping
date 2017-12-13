package de.lezleoh.webscraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class LoginFacebook {
	static String uri = "https://www.facebook.com/";
	static String loginformId = "login_form";
	static String loginformAction = "https://www.facebook.com/login.php?login_attempt=1&amp;lwv=110";
	static String loginformOnsubmit = "return window.Event &amp;&amp; Event.__inlineSubmit &amp;&amp; Event.__inlineSubmit(this,event)";
	static String user = "seppl.zappl@gmx.de";
	static String password = "sepplzappl1";

	public static void main(String[] args) {
		//
		// String applicationName = "Netscape";
		// String applicationVersion = "5.0 (Windows NT 6.2; Win64; x64;
		// Trident/7.0; rv:11.0) like Gecko";
		// String userAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64;
		// Trident/7.0; rv:11.0) like Gecko";
		// int browserVersionNumeric = 11;

		// BrowserVersion browser = new BrowserVersion(applicationName,
		// applicationVersion, userAgent,
		// browserVersionNumeric) {
		// public boolean hasFeature(BrowserVersionFeatures property) {

		// // change features here
		// return BrowserVersion.INTERNET_EXPLORER.hasFeature(property);
		// }
		// };

		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getBrowserVersion();
		HtmlPage page;
		try {
			page = (HtmlPage) webClient.getPage(uri);

			System.out.println("------Beginn Ausgabe der Webseite als Text:-----------");
			System.out.println(page.asText());
			System.out.println("------Ende Ausgabe der Webseite als Text:-----------");
			System.out.println();

			System.out.println("------Beginn Ausgabe der Webseite als XML:-----------");
			String pageAsXML = page.asXml();
			System.out.println(pageAsXML);
			System.out.println("------Ende Ausgabe der Webseite als XML:-----------");
			System.out.println();

			final HtmlForm form = (HtmlForm) page.getElementById(loginformId);
			System.out.println("------Beginn Ausgabe des LoginForms als Text:-----------");
			form.asText();
			System.out.println("------Ende Ausgabe der des LoginForms als Text:-----------");
			System.out.println();

			System.out.println("------Beginn Ausgabe des LoginForms als XML:-----------");
			form.asXml();
			System.out.println("------Ende Ausgabe der des LoginForms als XML:-----------");
			System.out.println();

			form.setActionAttribute("loginformAction");
			form.setMethodAttribute("post");

			final HtmlInput usernameInput = form.getInputByName("email");
			usernameInput.setValueAttribute(user);
			System.out.println("Username: " + usernameInput.asText());

			final HtmlInput passwordInput = form.getInputByName("email");
			passwordInput.setValueAttribute(user);

			final HtmlInput userpassInput = form.getInputByName("pass");
			//System.out.println("Enter password: ");
			//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			//String password = bufferedReader.readLine();
			userpassInput.setValueAttribute(password);

			final HtmlInput input = form.getInputByValue("Anmelden");
			final HtmlButton label = (HtmlButton) input.getParentNode();
			
			page = (HtmlPage) input.click();
			System.out.println(page.asText());
			System.out.println(page.asXml());

		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webClient.close();

	}

}
