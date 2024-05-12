package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import base_class.Browser;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.remote.Browser.CHROME;

public class login_runner extends Browser {

	
	@Test
	public void opening_browser() {
		launch_browser("EDGE");
		//sleep(3);
		getDriver().get("https://www.facebook.com/");

		//getDriver().findElement(By.id("email")).sendKeys("Text" , Keys.TAB);
		//getDriver().findElement(By.id("email")).sendKeys(Keys.TAB);


	}
}
