package com.mail;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailUtil {

	public static void sendmail(final String myAccountEmail, final String password, int senderCount)
			throws MessagingException, Exception {

		int recepientCount = 1;
		System.out.println("Prearing to sent ..");
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		File myObj = new File("receiver.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] userpass = data.split(",");
			System.out.println(" username " + userpass[0]);

			// Login
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myAccountEmail, password);
				}
			});

			Message message = prepareMessage(senderCount, session, password, userpass[0], recepientCount);

			Transport.send(message);
			System.out.println("Message sent");
			recepientCount++;
		}
		myReader.close();

	}

	private static Message prepareMessage(int senderCount, Session session, String myAccountEmail, String recepient,
			int recepientCount) throws Exception, MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("myAccountEmail"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
		message.setSubject(senderCount + " ROHIT SINGH " + recepientCount);
		String content = new String(Files.readAllBytes(Paths.get("mailBody.txt")), StandardCharsets.UTF_8);

		// Create the message part
		BodyPart messageBodyPart = new MimeBodyPart();

		// Now set the actual message
		messageBodyPart.setText(content);

		// Create a multipart message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String filename = "attachment.txt";
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));

		FileWriter myWriter = new FileWriter(filename);
		myWriter.write(senderCount + " ROHIT SINGH " + recepientCount);
		myWriter.close();

		messageBodyPart.setFileName("attachment.txt");
		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);
		return message;
	}
}
