package base_class;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import threadLocal.DriverHelper;

public class Browser extends DriverHelper {


	public static void launch_browser(String selected_browser) {
		switch (selected_browser) {
		case "CHROME":
			//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver","./src/main/resources/driver_files/chromedriver.exe" );
			ChromeOptions chrome_Options = new ChromeOptions();
			//			co.addArguments("--headless");
			chrome_Options.addArguments("--incognito");
			chrome_Options.addArguments("--window-size");
			chrome_Options.addArguments("--disable-extensions");
			chrome_Options.addArguments("--disable-infobars");
			chrome_Options.addArguments("--disable-popup-blocking");
			chrome_Options.addArguments("--start-maximized");
			chrome_Options.addArguments("--enable-automation");
			chrome_Options.addArguments("--disable-logging");

			setDriver(new ChromeDriver(chrome_Options));
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
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");// nac danh
			options.addArguments("--incognito");
			options.addArguments("start-maximized");
			options.addArguments("--headless");
			setDriver(new FirefoxDriver(options));
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
