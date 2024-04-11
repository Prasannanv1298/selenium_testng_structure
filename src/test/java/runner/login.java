package runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class login {
	Logger log = LogManager.getLogger(login.class);
	@Test
	public void add() {
		System.out.println("hi");
//		getExtenttest().info("hi");
		log.info("hi");
		
	}
}
