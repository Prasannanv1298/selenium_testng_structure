package base_class;

import org.openqa.selenium.WebDriver;

import interfaces.sleeps;

public class drivers implements sleeps{
	
	public ThreadLocal<WebDriver>driver = new ThreadLocal<WebDriver>(); 

	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	public WebDriver getDriver() {
		return this.driver.get();
	}

	public void sleep(int secondes) {
		try {
			Thread.sleep(secondes*1000);
			System.out.println("Lakshmanan");
			System.out.println("secondes = " + secondes);
			System.out.println("secondes = " + secondes);
			System.out.println("Automate");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
}