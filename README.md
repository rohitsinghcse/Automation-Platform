# Automation-Platform
Send email using Java

This code Login using the ID and Password combination mentioned in sender.txt. 
Compose a new mail. 
a - In subject line, write the sender number, your name in all caps and receiver number. For example, for the first sender sending a mail to fifth recipient write “1 JOHN DOE 5”   
B -   Add the text provided in mailBody.txt 
C -      Open attachment.txt. Replace the text in the file with the same text as subject line. For example, “1 JOHN DOE 5”. Then attach the file in the mail. 
D.      Send the mail. 
Action repeated for all items in receiver.txt 

Build Tool - Maven

IDE used 
Eclipse IDE for Enterprise Java Developers.
Version: 2019-09 R (4.13.0)

Target folder has the jar file

Open cmd and run below command
java -jar mail-automation-0.0.1-SNAPSHOT-jar-with-dependencies.jar

