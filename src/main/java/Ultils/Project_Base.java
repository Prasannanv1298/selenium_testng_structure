package Ultils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import threadLocal.DriverHelper;

public class Project_Base extends Reusable_Base{


    public void success_snackbar_alert(){

    }

    public void failure_snackbar_alert(){
    }

    public void error_alert(){
    }

    public void extent_report_info(String information){
        extentTest.log(Status.INFO,information);
    }
    public void extent_report_warning(String warning_information){
        extentTest.log(Status.WARNING,warning_information);
    }


}
