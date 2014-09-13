/**
 * The <CODE>Email</CODE> class contains information about the 
 * 	Emails in the Mailbox such as the fields, to, cc, bcc, subject,
 * 	body and the timestamp.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Email implements Serializable{
	private String to;
	private String cc;
	private String bcc;
	private String subject; 
	private String body;
	private GregorianCalendar timestamp;
	
	/**
	 * Creates an instance of <code>Email</code>.
	 * @param to
	 * 	the "to" part of the Email.
	 * @param cc
	 * 	the "cc" part of the Email.
	 * @param bcc
	 * 	the "cc" part of the Email.
	 * @param subject
	 * 	the "subject" of the Email.
	 * @param body
	 * 	the body(message) of the Email.
	 */
	public Email(String to, String cc, String bcc, String subject, 
			String body){
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.body = body;
		this.timestamp = new GregorianCalendar();
	}

	/**
	 * Get the "to" field of this Email
	 * @param - none
	 * @return the "to" field of this Email
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Set the "to" field of this Email
	 * @param to
	 * 	the "to" field of this Email
	 * @postcondition "to" is changed to the given parameter
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Get the "cc" field of this Email
	 * @param - none
	 * @return the "cc" field of this Email
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * Set the "cc" field of this Email
	 * @param cc
	 * 	the "cc" field of this Email
	 * @postcondition "cc" is changed to the given parameter
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * Get the "bcc" field of this Email
	 * @param - none
	 * @return the "bcc" field of this Email
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * Set the "bcc" field of this Email
	 * @param bcc
	 * 	the "bcc" field of this Email
	 * @postcondition "bcc" is changed to the given parameter
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * Get the "subject" field of this Email
	 * @param - none
	 * @return the "subject" field of this Email
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Set the "subject" field of this Email
	 * @param subject
	 * 	the "subject" field of this Email
	 * @postcondition "subject" is changed to the given parameter
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Get the "body" field of this Email
	 * @param - none
	 * @return the "body" field of this Email
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Set the "body" field of this Email
	 * @param body
	 * 	the "body" field of this Email
	 * @postcondition "body" is changed to the given parameter
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @Get the "timeStamp" of this Email
	 * @param - none
	 * @return the "timeStamp" of this Email
	 */
	public GregorianCalendar getTimestamp() {
		return timestamp;
	}

	/**
	 * Set the "timeStamp" field of this Email
	 * @param timeStamp
	 * 	the "timeStamp" field of this Email
	 * @postcondition "timeStamp" is changed to the given parameter
	 */
	public void setTimestamp(GregorianCalendar timestamp) {
		this.timestamp = timestamp;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		SimpleDateFormat fmt = new SimpleDateFormat("h:mm a M/d/yyyy");
	    fmt.setCalendar(timestamp);
	    String dateFormatted = fmt.format(timestamp.getTime());
	    return dateFormatted;
	}
}
