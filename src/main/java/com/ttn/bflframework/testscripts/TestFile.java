package com.ttn.bflframework.testscripts;

import com.ttn.bflframework.utils.ExcelUtils;
import com.ttn.bflframework.utils.GenericUtils;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;


import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;

public class TestFile {


//     public static void main(String args[]) throws IOException {
//         String data= ExcelUtils.getCellValue("D:\\Selenium\\ExcelTestData","Locators_English.xls","WishlistPage","dropdownSelector","Locator");
//        System.out.println(data);

//         String expectedUrl= "Tchibo Wind Protection Gloves, Unisex, Black OMR 1.36 OMR 1.88 28% off";
//         System.out.println(expectedUrl.split("OMR"));


    public Properties getMailProp() {
    //    log.info("Setting properties file to pick properties for the email config");
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    public Folder connectEmailInbox(Store store) throws MessagingException {
        String host = "smtp.gmail.com";// change accordingly
        //Properties config = BaseClass.initPropFromFile("/src/main/resources/config.properties");

        store.connect(host, "srikant.pandey@tothenew.com"
                ,"TTN081192");
       // log.info("create the folder object and open the inbox");
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        return inbox;
    }

    public String getMessageBody(Message message) {
        try {
            if (message.isMimeType("text/plain")) {
                return message.getContent().toString();
//            } else if (message.isMimeType("multipart/*")) {
//                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
//                return getMimeMultipartContent(mimeMultipart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

//    private String getMimeMultipartContent(MimeMultipart mimeMultipart) throws Exception {
//        String body = "";
//        int count = mimeMultipart.getCount();
//
//        for (int i = 0; i < count; i++) {
//            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
//            if (bodyPart.isMimeType("text/plain")) {
//                body = body + "\n" + bodyPart.getContent();
//                break; // without break same text appears twice in my tests
//            } else if (bodyPart.isMimeType("text/html")) {
//                String html = (String) bodyPart.getContent();
//                body = body + "\n" + org.jsoup.Jsoup.parse(html);
//            } else if (bodyPart.getContent() instanceof MimeMultipart) {
//                body = body + getMimeMultipartContent((MimeMultipart) bodyPart.getContent());
//            }
//        }
//        return body;
//    }

    private String getMailContentsUsingTimeout() throws MessagingException, InterruptedException {
        int polling = 1;

        try {
          //  log.info("Starting email session with given parameters");
            Session emailSession = Session.getDefaultInstance(getMailProp(), null);
      //      log.info("create the store object and connect with the server");
            Store store = emailSession.getStore("imaps");
            //connect to mailbox and fetch the inbox
            Folder inbox = null;
            try {
                inbox = connectEmailInbox(store);
            } catch (MessagingException e1) {
                e1.printStackTrace();
            }

            //each polling counter takes around 4sec to complete traverse of recent 10 mails
         //   while (polling < 500) {
         //       log.info("Retrieve the messages from the folder in an array. Polling " + polling);
            Message[] mailList = new Message[0];
            try {
                mailList = inbox.getMessages();
            } catch (MessagingException e1) {
                e1.printStackTrace();
            }

            for (int i = mailList.length - 1; i > mailList.length - 100; i--) {
                    Message message = mailList[i];
                    if (message.getSubject().contains("reset your password") &&
                            message.getReceivedDate().toString().contains(Calendar.getInstance().getTime().toString().substring(0, 10))) {

                     //   log.info("Got email successfully. " + message.getSubject());
                        System.out.println("Subject: " + message.getSubject());
                        if(message.getSubject().contains("automatically")){
                            return "auto approved orders";
                        }

                        return getMessageBody(message);
                    }

                Thread.sleep(2000);
                polling += 1;
            }
         //   log.info("close the store and folder objects");
            inbox.close(true);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
      //      log.error("No email found, throwing exception: " + e.getMessage());
        }
        return null;
    }

    public static void main(String args[]) throws MessagingException, InterruptedException {
        TestFile tf= new TestFile();
        tf.getMailContentsUsingTimeout();
    }
}


