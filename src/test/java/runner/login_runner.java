package runner;

import org.testng.annotations.Test;

import base_class.browser;

public class login_runner extends browser {

	
	@Test
	public void opening_browser() {
		launch_browser(Chrome);
		sleep(3);
		getDriver().get("https://learn.microsoft.com/en-us/answers/questions/839496/how-to-set-edge-capabilities-for-edge-browser-in-p");
	}
}
