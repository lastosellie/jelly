package application;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Message {

	  private String text;
	  private String user;
	  private String time;
	    
	   
		

	    public Message(String text, String user, String time) {
	        this.text = text;
	        this.user = user;
	        this.time = time;
	    }

	    public String getText() {
	        return text;
	    }

	    public void setText(String text) {
	        this.text = text;
	    }

	    public String getUser() {
	        return user;
	    }

	    public void setUser(String user) {
	        this.user = user;
	    }
	    public void setTime(String time) {
	        this.time = time;
	    }
	    public String getTime() {
	    	Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			String time =dateFormat.format(calendar.getTime());
	        return time;
	    }
}
