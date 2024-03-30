package base_class;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;


public class email {

	  // Send email method
    public void sendEmail(String subject, String body, String attachmentFile) throws IOException {
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
