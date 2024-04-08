package threadLocal;

import com.aventstack.extentreports.ExtentReports;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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


    public static void sendEmail(String subject, String body, String attachmentFile) throws IOException {
        // Configure email server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.outlook.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Authenticate sender
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("citestchennai2@hotmail.com", "Comp@123");
            }
        };

        // Create session
        Session session = Session.getInstance(properties, authenticator);

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("citestchennai2@hotmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("prsannanv1212@gmail.com"));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress("cindiatest1@gmail.com"));
            message.setSubject(subject);
            message.setText(body);

            // Create multipart
            Multipart multipart = new MimeMultipart();

            // Create text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("This is a test email with attachments.");

            // Attach text part to multipart
            multipart.addBodyPart(textPart);

            // Attach files
            // Paths to attachment files
//            String[] attachmentFiles = {"D:\\eclipse-workspace\\deeptext\\All_Reports\\Creation_Classifier_No_.html"};
//            for (String attachmentFile : attachmentFiles) {
//                MimeBodyPart attachmentPart = new MimeBodyPart();
//                attachmentPart.attachFile(attachmentFile);
//                multipart.addBodyPart(attachmentPart);
//            }

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(attachmentFile);
            multipart.addBodyPart(attachmentPart);


            // Set content of message
            message.setContent(multipart);


            // Send email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
