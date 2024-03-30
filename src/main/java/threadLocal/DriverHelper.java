package threadLocal;

import org.openqa.selenium.WebDriver;

public class DriverHelper {

    protected static InheritableThreadLocal<WebDriver> driverinstance = new InheritableThreadLocal<>();

    public static void setDriver(WebDriver driver){
        driverinstance.set(driver);
    }

    public static WebDriver getDriver(){

        if(driverinstance == null)
            throw new IllegalStateException("No driver saved here");
        else
            return driverinstance.get();

    }

    public static void getCurrentThread(){
        System.out.println("Current running thread ID is  "+ "  "+Thread.currentThread().getId());
    }

    public static void removeDriver(){
        driverinstance.remove();
    }

}
