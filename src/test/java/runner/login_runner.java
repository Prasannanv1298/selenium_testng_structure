package runner;

import org.testng.annotations.Test;

import base_class.Browser;

import static java.lang.Thread.sleep;
import static Enum.Browser_Enum.*;

public class login_runner extends Browser {

	
	@Test
	public void opening_browser() {
		launch_browser(String.valueOf(CHROME));
		//sleep(3);
		getDriver().get("https://learn.microsoft.com/en-us/answers/questions/839496/how-to-set-edge-capabilities-for-edge-browser-in-p");
	}
}
