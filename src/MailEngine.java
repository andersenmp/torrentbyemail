/*
 The MIT License (MIT)

Copyright (c) 2013-2014 Andersen Pecorone

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE. 
 */


import java.util.Properties;
import java.util.Date;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class MailEngine {

	private String pathAppDownload 	= "";
	private String emailUser 		= "";
	private String emailPassword	= "";
	private String allowedEmails	= "";
	private String propStore		= "imaps";
	private String server			= "imap.gmail.com";
	
	
		
	public void fetchMail() {
			 Properties props = System.getProperties();
		 
			 // Uncomment if you are using proxy server to access Internet
			 /*   	props.setProperty("http.proxyHost", "192.168.0.1");
					props.setProperty("http.proxyPort", "8080"); */
		 
			 props.setProperty("mail.store.protocol", this.propStore);
			 try {
				 	Session session = Session.getDefaultInstance(props, null);
				 	session.setDebug(false);
				 	Store store = session.getStore(this.propStore);
				 	store.connect(this.server, this.emailUser , this.emailPassword);
				 	//System.out.println(store);
		 
				 	Folder inbox = store.getFolder("Inbox");
				 	inbox.open(Folder.READ_WRITE);
				 	FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
				 	Message messages[] = inbox.search(ft);
				 	
				 	System.out.println("Inbox new messages: " + messages.length + " at " + new Date()); 
				 	
				 	for (Message message : messages) {
				 		String subject = message.getSubject();
				 		Address[] from = message.getFrom();
	 		
					 	if(!this.allowedEmails.isEmpty() & this.allowedEmails.indexOf(from[0].toString())>=0){	
				 			if(subject.contains(".torrent")) {
						 		try {
						 			System.out.println("E-mail download!");
						 			System.out.println("Subject:" + subject);
						 			System.out.println("From:" + from[0]);
						 			Runtime run = Runtime.getRuntime();
						 			run.exec(this.pathAppDownload + " " + subject);
						 			message.setFlag(Flags.Flag.SEEN, true);
						 			
						 		} catch (Exception e) {
						 			// TODO Auto-generated catch block
						 			e.printStackTrace();
						 		}
					 		}
					 	}else{
					 		System.out.println("E-mail skipped!");
					 		System.out.println("Subject:" + subject);
				 			System.out.println("From:" + from[0].toString());
					 	}
				 		
				 	}
				 	
				 	System.out.println("End at " + new Date()); 
			 
			 } catch (NoSuchProviderException e) {
				 e.printStackTrace();
				 System.exit(1);
			 
			 } catch (MessagingException e) {
				 e.printStackTrace();
				 System.exit(2);
			}

		}
	
	public String getPathAppDownload() {
		return pathAppDownload;
	}



	public void setPathAppDownload(String pathAppDownload) {
		this.pathAppDownload = pathAppDownload;
	}



	public String getEmailUser() {
		return emailUser;
	}



	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}



	public String getEmailPassword() {
		return emailPassword;
	}



	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}



	public String getAllowedEmails() {
		return allowedEmails;
	}



	public void setAllowedEmails(String allowedEmails) {
		this.allowedEmails = allowedEmails;
	}



	public String getPropStore() {
		return propStore;
	}



	public void setPropStore(String propStore) {
		this.propStore = propStore;
	}



	public String getServer() {
		return server;
	}



	public void setServer(String server) {
		this.server = server;
	}
}
