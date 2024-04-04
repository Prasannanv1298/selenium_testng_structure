package threadLocal;

import com.aventstack.extentreports.ExtentReports;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    public static String checkDirectory(String status){

        Date date =new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        String foldername = (status.equalsIgnoreCase("1") ?  "AllReports" : "FailedReports");

        String folderPath = System.getProperty("user.dir")+"/"+foldername+"/"+formatter.format(date);

        // Convert the path String to a Path object
        Path folder = Paths.get(folderPath);

        // Check if the folder exists
        if (!Files.exists(folder)) {
            try {
                // Create the folder if it doesn't exist
                Files.createDirectories(folder);
                System.out.println("Folder created successfully: " + folderPath);
            } catch (IOException e) {
                System.out.println("Error creating folder: " + e.getMessage());
            }
        }
        return folderPath;
    }
public static String time_stamp(){
    Date date =new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("HH_mm_ss");
    return formatter.format(date).toString();
}
}
