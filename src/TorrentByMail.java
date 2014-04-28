
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
