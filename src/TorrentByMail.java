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

import java.util.Date;
import java.util.Properties;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


public class TorrentByMail extends TimerTask{

	private static final String PROP_FILE="myConfig.properties";
	private int runInterval = 0;
	
	public TorrentByMail() {
		super();
		this.runInterval = 3000; //5 minutes
	}

	
	public int getRunInterval() {
		return runInterval;
	}

	public void setRunInterval(int runInterval) {
		this.runInterval = runInterval;
	}

	
	public static void main(String[] args) {				
		System.out.println("TorrentByMail started at " + new Date());
		TorrentByMail task = new TorrentByMail();

	    Timer timer = new Timer();
	    timer.scheduleAtFixedRate(task, task.getRunInterval(), task.getRunInterval());
	}
	
	public void run(){	
		
		MailEngine mailEngine = new MailEngine();
		
		try{
			FileInputStream is = new FileInputStream(PROP_FILE);
			Properties prop = new Properties();  
			prop.load(is);        
			mailEngine.setPathAppDownload(prop.getProperty("torrentByMail.pathAppDownload"));
			mailEngine.setEmailUser(prop.getProperty("torrentByMail.emailUser"));
			mailEngine.setEmailPassword(prop.getProperty("torrentByMail.emailPassword"));
			mailEngine.setPropStore(prop.getProperty("torrentByMail.propStore"));
			mailEngine.setServer(prop.getProperty("torrentByMail.server"));
			mailEngine.setAllowedEmails(prop.getProperty("torrentByMail.allowedEmails"));
			is.close();
			mailEngine.fetchMail();
		}catch(Exception e){  
          System.out.println("Failed to read from " + PROP_FILE + " file.");  
          System.exit(1);
        }  
	
	}
	

}
