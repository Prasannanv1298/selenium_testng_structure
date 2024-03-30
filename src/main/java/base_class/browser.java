package base_class;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import Enum.browser_enums;
import io.github.bonigarcia.wdm.WebDriverManager;

public class browser extends drivers implements browser_enums{


	public void launch_browser(String selected_browser) {
		switch (selected_browser) {
		case "CHROME":
			//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver","./src/main/resources/driver_files/chromedriver.exe" );
			ChromeOptions co = new ChromeOptions();
			//			co.addArguments("--headless");
			co.addArguments("--incognito");
			co.addArguments("--window-size");
			co.addArguments("--disable-extensions");
			co.addArguments("--disable-infobars");
			co.addArguments("--disable-popup-blocking");
			co.addArguments("--start-maximized");
			co.addArguments("--enable-automation");
			co.addArguments("--disable-logging");

			setDriver(new ChromeDriver(co));			
			break;
		case "EDGE":
			WebDriverManager.edgedriver().setup();
			EdgeOptions eo= new EdgeOptions();
			eo.addArguments("start-maximized");
			eo.addArguments("inprivate");  
			eo.addArguments("--headless");
			setDriver(new EdgeDriver(eo));			
			break;

		case "FireFox":	
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fo = new FirefoxOptions();
			fo.addArguments("--headless");
			setDriver(new FirefoxDriver(fo));
			break;
			
		case "Opera":
			WebDriverManager.operadriver().setup();
//			setDriver(new OperaDriverManager());
			break;
			
		case "Safari":
			WebDriverManager.safaridriver().setup();
			setDriver(new SafariDriver());
			break;
		default:
			System.out.println(selected_browser + " browser is not found");
			break;
		}
	}

}
