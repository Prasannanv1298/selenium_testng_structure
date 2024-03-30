package threadLocal;

import com.aventstack.extentreports.ExtentReports;

public class ExtentReportHelper {

    public static ThreadLocal<ExtentReports> reportmanager = new ThreadLocal<>();

    public static void setExtent(ExtentReports extent){

        reportmanager.set(extent);

    }

    public static ExtentReports getExtentReporter(){
        return reportmanager.get();
    }

    public static void removeExtentReporter(){
        reportmanager.remove();
    }

}
