package Ultils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static threadLocal.DriverHelper.*;
import static threadLocal.ExtentReportHelper.*;
import static base_class.Browser.*;

public class Reusable_Base {


    public static WebDriver driver;

    Properties properties;

    Logger log = LogManager.getLogger(Reusable_Base.class);
    public static ExtentReports extentReport;

    public ExtentTest extentTest;

    private static String WORKING_DIRCETORY;

    @BeforeSuite
    public void beforeSuite(ITestContext context){

      //  WORKING_DIRCETORY = System.getProperty("user.dir");

        WORKING_DIRCETORY = System.getProperty("user.dir");


        setExtent(new ExtentReports());

        extentReport = getExtentReporter();

        ExtentSparkReporter spark_All_Case = new ExtentSparkReporter(checkDirectory("1")+"\\All_Cases_Report"+time_stamp()+".html");
        ExtentSparkReporter spark_Failed_Case = new ExtentSparkReporter(checkDirectory("0")+"\\Failed_Cases_Report"+time_stamp()+".html");
        spark_Failed_Case.filter().statusFilter().as(new Status[] {Status.FAIL});
        extentReport.attachReporter(spark_All_Case,spark_Failed_Case);


        extentReport.setSystemInfo("OS",System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
        extentReport.setSystemInfo("User Name",System.getProperty("user.name"));


    }


    @AfterSuite
    public void afterSuite() throws IOException {

        extentReport.flush();
        //sendEmail("Automation Report","Checking Body",System.getProperty("user.dir")+"\\All_Report\\AllCasesReport.html");
        removeExtentReporter();
       // Desktop.getDesktop().browse(new File(WORKING_DIRCETORY + "\\All_Report\\AllCasesReport.html").toURI());
        //Desktop.getDesktop().browse(new File("Failed Cases/FailedCasesReport.html").toURI());

    }

    @AfterMethod
    public void afterMethod(Method method , ITestResult result) throws IOException {

        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.addScreenCaptureFromPath(captureScreenshot(result.getTestContext().getName()+"_"+result.getMethod().getMethodName()+".jpg"));
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass(method.getName() + "  is Passed");
        }

    }


    public String captureScreenshot(String filename) throws IOException {

        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File("./Screenshot/"+filename);
        FileUtils.copyFile(sourceFile,dest);
        return dest.getAbsolutePath();

    }


    @BeforeTest
    public void SetallDatas(ITestContext context) throws Exception {

        properties = loadProperties();

        launch_browser(properties.getProperty("BROWSER"));
        getDriver().get(properties.getProperty("QA_URL"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("WAIT"))));
        getDriver().manage().window().maximize();
        driver = getDriver();
        extentTest = extentReport.createTest(context.getName());

    }

    @AfterTest
    public void cleardriver(){

        getDriver().quit();
        removeDriver();
        System.out.println(getDriver());
    }

    public Properties loadProperties() {

        Properties properties = new Properties();

        try {
            FileInputStream file = new FileInputStream("./src/test/resources/Properties/Application.properties");
            properties.load(file);
        }catch (Exception exception){

        }

        return (properties != null) ? properties : null;

    }


    public void ACCEPT() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void DISMISS() {
        try {
            driver.switchTo().alert().dismiss();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void GETTEXTALERT() {
        try {
            driver.switchTo().alert().getText();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void SENDKEYSALERT(String value) {
        try {
            driver.switchTo().alert().sendKeys(value);
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void LANUCHURL(String url) {

        try {
            driver.get(url);
            log.info("Launching the " + url);
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }

    }

    
    public String GETCURRENTURL() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public String GETTITLE() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }

    }

    
    public void CLOSEBROWSER() {
        try {
            driver.close();
            log.info("Browser session closed!!!!!!");
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void QUITBROWSER() {
        try {
            driver.quit();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void MAXIMIZEWINDOWS() {
        try {
            driver.manage().window().maximize();
            log.info("Maximizing window................");
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void REFRESHBROWSER() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void BACK() {

        try {
            driver.navigate().back();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }

    }

    
    public void FORWARD() {
        try {
            driver.navigate().forward();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void REDIRECTURL(String url) {
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void SELECTBYINDEX(By locators, int index) {

        try {
            new Select(FINDELEMENT(locators)).selectByIndex(index);

        } catch (Exception e) {
            log.error(e + "   " + locators + "\n" + index);
            throw e;
        }

    }

    
    public void SELECTBYVISIBLETEXT(By locators, String option) {
        try {
            new Select(FINDELEMENT(locators)).selectByVisibleText(option);
        } catch (Exception e) {
            log.error(e + "   " + locators + "\n" + option);
            throw e;
        }

    }

    
    public void SELECTBYVALUE(By locators, String value) {
        try {
            new Select(FINDELEMENT(locators)).selectByValue(value);
        } catch (Exception e) {
            log.error(e + "   " + locators + "\n" + value);
            throw e;
        }

    }

    
    public java.util.List<WebElement> GETOPTIONS(By locators) {
        try {
            return new Select(FINDELEMENT(locators)).getOptions();

        } catch (Exception e) {
            log.error(e + "   " + locators);
            throw e;
        }

    }

    
    public void DESELECTALL(By locators) {
        try {
            new Select(FINDELEMENT(locators)).deselectAll();
        } catch (Exception e) {
            log.error(e + "   " + locators);
            throw e;
        }

    }

    
    public void CLICK(By locators) {
        try {
            FINDELEMENT(locators).click();
        } catch (Exception e) {
            log.error(e + "   " + locators);
            throw e;
        }

    }

    
    public void CLEAR(By locators) {

        try {
            FINDELEMENT(locators).clear();
        } catch (Exception e) {
            log.error(e + "   " + locators);
            throw e;
        }


    }

    
    public void SENDKEYS(By locators, String value) {
        try {
            FINDELEMENT(locators).sendKeys(value);
        } catch (Exception e) {
            log.error(e + " : Element " + locators + "\n" + "Value : " + value);
            throw e;
        }

    }

    
    public boolean ISENABLED(By locators) {
        try {
            return FINDELEMENT(locators).isEnabled();
        } catch (Exception e) {
            log.warn(e);
            return false;
        }
    }

    
    public boolean ISDISPLAYED(By locators) {

        try {
            return FINDELEMENT(locators).isDisplayed();
        } catch (Exception e) {
            log.warn(e);
            return false;
        }
    }

    
    public boolean ISSELECTED(By locators) {

        try {
            return FINDELEMENT(locators).isSelected();
        } catch (Exception e) {
            log.warn(e);
            return false;
        }

    }

    
    public String GETTEXT(By locators) {

        try {

            return FINDELEMENT(locators).getText();

        } catch (Exception e) {
            log.error(e);

        }
        return null;
    }

    
    public String GETATTRIBUTE(By locators, String attribute) {

        try {

            return FINDELEMENT(locators).getAttribute(attribute);
        }catch (Exception e){
            log.error(e);
        }

        return null;
    }

    
    public void SWITCHFRAMEIDORNAME(String nameorid) {
        try {
            driver.switchTo().frame(nameorid);
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }


    }

    
    public void SWITCHFRAMEELEMENT(WebElement element) {

        try {
            driver.switchTo().frame(element);
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void SWITCHFRAMEINDEX(int index) {

        try {
            driver.switchTo().frame(index);
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void RETURNMAINFRAME() {

        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void RETURNTOPARENTFRAME() {

        try {
            driver.switchTo().parentFrame();
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public void KEYPRESS(int keycode) throws AWTException {

        Robot robot = new Robot();
        robot.keyPress(keycode);
        log.info("Pressing the key of " + keycode);

    }


    
    public void KEYRELEASE(int keyCode) throws AWTException {

        Robot robot = new Robot();
        robot.keyRelease(keyCode);
        log.info("Releasing the key of " + keyCode);

    }

    
    public WebElement FINDELEMENT(By locators) {
        try {
            return driver.findElement(locators);
        } catch (Exception e) {
            log.error(e);
            throw e;

        }
    }

    
    public List<WebElement> FINDELEMENTS(By locators) {
        try {
            return driver.findElements(locators);
        } catch (Exception e) {
            log.error(e);
            throw e;

        }
    }


    
    public void MOUSEHOVER(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        } catch (Exception e) {
            log.error(e);
            throw e;

        }


    }

    
    public void DRAGDROP(WebElement drag, WebElement drop) {
        try {
            Actions action = new Actions(driver);
            action.dragAndDrop(drag, drop).perform();
        } catch (Exception e) {
            log.error(e);
            throw e;

        }

    }

    
    public void RIGHTCLICK(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.contextClick(element).perform();
        } catch (Exception e) {
            log.error(e);
            throw e;

        }
    }

    
    public void SCROLLMOVETOELEMENT(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
        } catch (Exception e) {
            log.error(e);
            throw e;

        }

    }

    
    public void SENDKEYSWITHACTION(WebElement element, String value) {
        try {
            Actions action = new Actions(driver);
            action.sendKeys(element, value).perform();
        } catch (Exception e) {
            log.error(e);
            throw e;

        }

    }

    
    public void DOUBLECLICK(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.doubleClick(element).perform();
        } catch (Exception e) {
            log.error(e);
            throw e;

        }

    }

    
    public byte[] TAKESCREENSHOTBYTE() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            return screenshot.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }
    }

    
    public File TAKESCREENSHOTFILE() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            return screenshot.getScreenshotAs(OutputType.FILE);
        } catch (Exception e) {
            log.error(e + "Driver is null");
            throw e;
        }

    }


    
    public void COPYSCREENSHOTTOLOCAL(String savescrshotlocation) throws IOException {

        try {
            File source = TAKESCREENSHOTFILE();
            File dest = new File(savescrshotlocation);
            FileUtils.copyFile(source, dest);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public void SENDKEYSWITHJS(WebElement element, String value) {

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('value','" + value + "'+)", element);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public void GETATTRIBUTEWITHJS(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("return arguments[0].getAttribute('value')", element);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public void CLICKWITHJS(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", element);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    
    public void SCROLLFUNCTION(WebElement element, boolean trueorfalse) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(" + trueorfalse + ")", element);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    
    public void WAITUNTILL(Function function, int seconds) {

        try {
            WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(seconds));
            wait.until(function);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public void IMPLICITYWAIT(long seconds) {

        try {
            driver.manage().timeouts().implicitlyWait(seconds , TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public void PAGELOADTIMOUT(int seconds) {

        try {
            driver.manage().timeouts().pageLoadTimeout(seconds , TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public void SWITCHWINDOWBYID(String windowid) {
        try {
            driver.switchTo().window(windowid);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public void SWITCHWINDOWBYTITLE(String title) {
        try {
            driver.switchTo().window(title);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public void SWITCHWINDOWBYURL(String url) {
        try {
            driver.switchTo().window(url);
        } catch (Exception e) {
            log.error(e);
            throw e;
        }

    }

    
    public String GETWINDOWHANDLE() {
        try {
            return driver.getWindowHandle();
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    
    public Set<String> GETWINDOWHANDLES() {
        try {
            return driver.getWindowHandles();
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

}
