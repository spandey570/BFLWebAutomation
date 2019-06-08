package com.ttn.bflframework.testscripts;

import com.ttn.bflframework.utils.ExcelUtils;
import com.ttn.bflframework.utils.GenericUtils;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
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
    private Object MessagingException;


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
       System.out.println("create the folder object and open the inbox");
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        return inbox;
    }

    public String getMessageBody(Message message) {
        try {
            if (message.isMimeType("text/plain")) {

                return message.getContent().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private String getMailContentsUsingTimeout() throws MessagingException, InterruptedException {
        int polling = 1;

        try {

          System.out.println("Starting email session with given parameters");
            Session emailSession = Session.getDefaultInstance(getMailProp(), null);
         System.out.println("create the store object and connect with the server");
            Store store = emailSession.getStore("imaps");
            //connect to mailbox and fetch the inbox
            Folder inbox = null;
            try {
                inbox = connectEmailInbox(store);
            } catch (MessagingException e1) {
                e1.printStackTrace();
            }

            //each polling counter takes around 4sec to complete traverse of recent 10 mails
           // while (polling < 500) {
         System.out.println("Retrieve the messages from the folder in an array. Polling " + polling);
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

                     System.out.println("Got email successfully. " + message.getSubject());
                        System.out.println("Subject: " + message.getSubject());


                        return getMessageBody(message);
                    }

                Thread.sleep(2000);
                polling += 1;
            }
         System.out.println("close the store and folder objects");
            inbox.close(true);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
         System.out.println("No email found, throwing exception: " + e.getMessage());
        }
        return null;
    }

    public String getLinkFromEmail( ) throws MessagingException, InterruptedException {
        String msgBody = getMailContentsUsingTimeout();
        System.out.println("Email message body: " + msgBody);
        if(msgBody.contains("bfltest-web-client.qa3.tothenew.net/en/reset-password")){
            return "reset password link found";

        }
        if (msgBody != null) {
            try {
                return org.jsoup.Jsoup.parse(msgBody).select("a").first().attr("href");
            } catch (NullPointerException npe) {
                try {
                    return org.jsoup.Jsoup.parse(msgBody).getElementsByAttributeValue("style", "margin-bottom:20px").first().text();
                } catch (NullPointerException np) {
                    return org.jsoup.Jsoup.parse(msgBody).select("p").first().text();
                }
            }
        } else {
            return "No Email Found Yet";
        }
    }



    public static void main(String args[]) throws MessagingException, InterruptedException {
        TestFile tf= new TestFile();
        tf.getMailContentsUsingTimeout();
        tf.getLinkFromEmail();
    }
}


