package de.lezleoh.webscraping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class LoginSchulportal {
	static String uri = "https://schule-infoportal.de/olchigym/schule_portal/project/auth/login.php";
	static String href = "https://schule-infoportal.de/olchigym/schule_portal/index.php?nav_index_php=&std_nav_id=4&std_nav_sub_id=1&std_inc=postkorb/pk_intro.inc&nav_mode=r#pk";
	static String user = "höl";

	public static void main(String[] args) {
		WebClient webClient = new WebClient();
		webClient.getOptions().setUseInsecureSSL(true);
		HtmlPage page;
		try {
			page = (HtmlPage) webClient.getPage(uri);

			System.out.println("------Beginn Ausgabe der Webseite:-----------");
			System.out.println(page.asText());
			System.out.println("------Ende Ausgabe der Webseite:-----------");

			final HtmlForm form = (HtmlForm) page.getElementByName("signon");
			form.setActionAttribute("login.php");
			form.setMethodAttribute("post");

			final HtmlInput usernameInput = form.getInputByName("user");
			usernameInput.setValueAttribute(user);
			System.out.println("Username: " + usernameInput.asText());

			final HtmlInput userpassInput = form.getInputByName("password");
			System.out.println("Enter password: ");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String password = bufferedReader.readLine();
			userpassInput.setValueAttribute(password);

			System.out.println("Password: " + userpassInput.asText());

			final HtmlInput clicklogin = form.getInputByValue("Enter");
			page = (HtmlPage) clicklogin.click();

			System.out.println("------Beginn Ausgabe der Webseite:-----------");
			System.out.println(page.asText());
			System.out.println("------Ende Ausgabe der Webseite:-----------");

			List<HtmlAnchor> allAnchors = page.getAnchors();

			System.out.println("------Beginn Ausgabe der Links:-----------");
			HtmlAnchor desiredAnchor = null;
			for (HtmlAnchor anchor : allAnchors) {
				System.out.println(anchor.getAttribute("href"));
				if (anchor.getAttribute("href").toString().equals(href)) {
					desiredAnchor = anchor;
				}
			}
			System.out.println("------Ende Ausgabe der Links:-----------");
			
			if (desiredAnchor != null) {
				page = desiredAnchor.click();

				System.out.println("------Beginn Ausgabe der Webseite:-----------");
				System.out.println(page.asText());
				System.out.println("------Ende Ausgabe der Webseite:-----------");
			}
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webClient.close();

	}

}
