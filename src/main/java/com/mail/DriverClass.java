package com.mail;

import java.io.File;
import java.util.Scanner;

import javax.mail.MessagingException;

public class DriverClass {

	public static void main(String[] args) throws MessagingException, Exception {
//		JavaMailUtil.sendmail("newtechtrickstv@gmail.com");
		
		 int senderCount = 1;
		  File myObj = new File("src\\main\\resources\\sender.txt");
		  Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String[] userpass = data.split(",");
	        System.out.print(" username " + userpass[0]);
	        System.out.println(" password " + userpass[1]);
	        JavaMailUtil.sendmail(userpass[0], userpass[1],senderCount);
	        senderCount++;
	      }
	      myReader.close();
		
		
	}

}
