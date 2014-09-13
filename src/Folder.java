/**
 * The <CODE>Folder</CODE> class represents an email folder and 
 * handles all of the logic for adding and removing emails. It 
 * also contains the data values emails, name and current sorting
 * method.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Folder implements Serializable {
	private ArrayList<Email> emails;
	private String name;
	private String currentSortingMethod;

	/**
	 * Returns an instance of <code>Folder</code>.
	 * @param name
	 * 	the name of this Folder
	 */
	public Folder(String name){
		emails = new ArrayList<Email>();
		this.name = name;
		this.currentSortingMethod = "date desc";
	}

	/**
	 * Get the list of emails in this Folder
	 * @param - none
	 * @return the list of emails in this Folder
	 */
	public ArrayList<Email> getEmails() {
		return emails;
	}

	/**
	 * Set the list of emails in this Folder
	 * @param emails
	 * 	the list of emails in this Folder
	 * @postcondition emails is changed to the given parameter
	 */
	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}

	/**
	 * Get the name of this Folder
	 * @param - none
	 * @return the name of this Folder
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this Folder
	 * @param name
	 * 	the name of this Folder
	 * @postcondition name is changed to the given parameter
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the current sorting method of this Folder
	 * @param - none
	 * @return the current sorting method of this Folder
	 */
	public String getCurrentSortingMethod() {
		return currentSortingMethod;
	}

	/**
	 * Set the current sorting method of this Folder
	 * @param currentSortingMethod
	 * 	the current sorting method of this Folder
	 * @postcondition currentSortingMethod is changed to the given parameter
	 */
	public void setCurrentSortingMethod(String currentSortingMethod) {
		this.currentSortingMethod = currentSortingMethod;
	}

	/**
	 * Adds an email to the folder according to the current sorting method.
	 * @param email
	 * 	an email in this Folder
	 */
	public void addEmail(Email email){
		if (currentSortingMethod == null) currentSortingMethod = "date desc";
		String[] sortMethod = currentSortingMethod.split(" ");
		String sortBy = sortMethod[0];
		String sortOrder = sortMethod[1];
		if (emails.isEmpty()) {
			emails.add(email);
			return;
		}
		else {
			for (int i = 0; i < emails.size(); i++) {
				Email e = emails.get(i);
				if (sortBy.equals("date")) {
					if (sortOrder.equals("desc")) {
						if (email.getTimestamp().compareTo(e.getTimestamp()) >= 0) {
							emails.add(i, email);
							return;
						}
					}
					else {
						if (email.getTimestamp().compareTo(e.getTimestamp()) <= 0) {
							emails.add(i, email);
							return;
						}
					}
				}
				else {
					if (sortOrder.equals("desc")) {
						if (email.getSubject().compareTo(e.getSubject()) >= 0) {
							emails.add(i, email);
							return;
						}
					}
					else {
						if (email.getSubject().compareTo(e.getSubject()) <= 0) {
							emails.add(i, email);
							return;
						}
					}
				}
			}
		}
		emails.add(email);
	}

	/**
	 * Removes an email from the folder by index.
	 * @param index
	 * 	the index of the Email
	 * @return the Email at the given index
	 */
	public Email removeEmail(int index){
		return this.emails.remove(index); 
	}

	/**
	 *  Sorts the emails alphabetically by subject in ascending order.
	 *  @param - none
	 */
	public void sortBySubjectAscending(){
		this.currentSortingMethod = "subj asc";
		Collections.sort(emails, new Comparator<Email>() {
			@Override
			public int compare(Email o1, Email o2) {
				return o1.getSubject().compareTo(o2.getSubject());
			}
		});
	}

	/**
	 * Sorts the emails alphabetically by subject in descending order.
	 * @param - none
	 */
	public void sortBySubjectDescending(){
		this.currentSortingMethod = "subj desc";
		Collections.sort(emails, new Comparator<Email>() {
			@Override
			public int compare(Email o1, Email o2) {
				return -o1.getSubject().compareTo(o2.getSubject());
			}
		});
	}

	/**
	 * Sorts the emails by date in ascending order.
	 * @param - none
	 */
	public void sortByDateAscending(){
		this.currentSortingMethod = "date asc";
		Collections.sort(emails, new Comparator<Email>() {
			@Override
			public int compare(Email o1, Email o2) {
				return o1.getTimestamp().compareTo(o2.getTimestamp());
			}
		});
	}

	/**
	 * Sorts the emails by date in descending order.
	 * @param - none
	 */
	public void sortByDateDescending(){
		this.currentSortingMethod = "date desc";
		Collections.sort(emails, new Comparator<Email>() {
			@Override
			public int compare(Email o1, Email o2) {
				return -o1.getTimestamp().compareTo(o2.getTimestamp());
			}
		});		 
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String out = this.name + "\n\n";
		out += String.format("%-6s|%-20s|%-8s", "Index", "       Time", " Subject");
		out += "\n-------------------------------------------\n";
		for(int i = 0; i < emails.size(); i++){			 
			out += String.format("  %-4d| %-19s| %-7s\n", (i+1), 
					emails.get(i), emails.get(i).getSubject());
		}
		return out;
	}
}
