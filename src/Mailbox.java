/**
 * The <CODE>Mailbox</CODE> class represents an email box and it
 * 	contains all of the folders and the remaining logic as well as 
 * the data values inbox, trash, current folder and folders.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Mailbox implements Serializable {
	static Scanner input = new Scanner(System.in);
	private Folder inbox = new Folder("inbox");
	private Folder trash = new Folder("trash");
	private ArrayList<Folder> folders = new ArrayList<Folder>();
	private Folder currentFolder = null;

	/**
	 * Returns an instance of <code>Mailbox</code>.
	 */
	public Mailbox() {
		inbox = new Folder("inbox");
		trash = new Folder("trash");
		folders = new ArrayList<Folder>();
	}

	/**
	 * Get the current Folder
	 * @param - none
	 * @return the current Folder
	 */
	public Folder getCurrentFolder() {
		return currentFolder;
	}

	/**
	 * Set the current Folder
	 * @param currentFolder
	 * 	the current Folder the user is accessing
	 * @postcondition currentFolder is changed to the given parameter
	 */
	public void setCurrentFolder(Folder currentFolder) {
		this.currentFolder = currentFolder;
	}

	/**
	 * Prints the folders in this Mailbox
	 * @param - none
	 */
	public void printMailboxFolders() {
		System.out.println();
		System.out.println("Mailbox:");
		System.out.println("---------");
		System.out.println("Inbox");
		System.out.println("Trash");
		if (folders != null) {
			for (Folder folder : folders) {
				System.out.println(folder.getName());
			}
		}
	}

	/**
	 * Removes all emails from the trash folder.
	 * @param - none
	 */
	public void clearTrash() {
		for (int i = 0; i < trash.getEmails().size(); i++) {
			trash.getEmails().remove(i);
		}
		System.out.println("Trash emptied successfully.");
	}

	/**
	 * Prints out the emails in the trash
	 * @param - none
	 */
	public void viewTrash() {
		currentFolder = trash;
		System.out.println(currentFolder);
	}

	/**
	 * Prints out the emails in the inbox
	 * @param - none
	 */
	public void viewInbox() {
		currentFolder = inbox;
		System.out.println(currentFolder);
	}

	/**
	 * Prints out the emails in the specified Folder
	 * @param folderName
	 * 	the name of the folder
	 */
	public void viewFolder(String folderName) {
		int index = findFolder(folderName);
		if (index == -1) {
			System.out.println("Folder does not exist");
			return;
		}
		currentFolder = folders.get(index);
		if (currentFolder.getEmails().size() == 0) {
			System.out.println(currentFolder.getName() + " is empty");
		} else {
			System.out.println(currentFolder);
		}
	}

	/**
	 * Sorts the current Folder by subject descending
	 * @param - none
	 */
	public void sortSubjectDescending() {
		currentFolder.sortBySubjectDescending();
		currentFolder.setCurrentSortingMethod("subj desc");
	}

	/**
	 * Sorts the current Folder by date ascending
	 * @param - none
	 */
	public void sortDateAscending() {
		currentFolder.sortByDateAscending();
		currentFolder.setCurrentSortingMethod("date asc");
	}

	/**
	 * Sorts the current Folder by date descending
	 * @param - none
	 */
	public void sortDateDescending() {
		currentFolder.sortByDateDescending();
		currentFolder.setCurrentSortingMethod("date desc");
	}

	/**
	 * Sorts the current Folder by subject ascending
	 * @param - none
	 */
	public void sortSubjectAscending() {
		currentFolder.sortBySubjectAscending();
		currentFolder.setCurrentSortingMethod("subj asc");
	}

	/**
	 * Prints out all the fields of an Email at the specified index
	 * @param - none
	 */
	public void viewEmailContents() {
		System.out.println("Enter email index: ");
		String strIndex = input.next();
		int index = Integer.parseInt(strIndex);
		index--;
		if (currentFolder.getEmails().size() <= index || index < 0) {
			System.out.println("Invalid index!");
			return;
		}
		System.out.println("To: "
				+ currentFolder.getEmails().get(index).getTo());
		System.out.println("CC: "
				+ currentFolder.getEmails().get(index).getCc());
		System.out.println("BCC: "
				+ currentFolder.getEmails().get(index).getBcc());
		System.out.println("Subject: "
				+ currentFolder.getEmails().get(index).getSubject());
		System.out.println(currentFolder.getEmails().get(index).getBody()
				+ "\n");
	}

	/**
	 * Moves the email to the trash.
	 * @param - none
	 */
	public void deleteEmail() {
		System.out.print("Enter email index: ");
		String strIndex = input.nextLine();
		int index = Integer.parseInt(strIndex);
		if (currentFolder.getEmails().size() < index || index < 1) {
			System.out.println("Invalid index!");
			return;
		}
		if(currentFolder == trash){
			System.out.println("This email is already in the trash silly!");
		}
		else{
			Email email = currentFolder.removeEmail(index - 1);
			trash.addEmail(email);
			System.out.println("\"" + email.getSubject()
					+ "\" has successfully been moved to the trash.\n");
		}
	}

	/**
	 * Takes the given email and puts in in the given folder. If the folder 
	 * cannot be found it is moved to the Inbox.
	 * @param - none
	 */
	public void moveEmail() {
		System.out.print("Enter the index of the email to move: ");
		String strIndex = input.nextLine();
		int index = Integer.parseInt(strIndex);
		if (currentFolder.getEmails().size() < index || index < 1) {
			System.out.println("Invalid index!");
			return;
		}
		Email email = currentFolder.removeEmail(index - 1);
		System.out.println("Folders:");
		System.out.println("Inbox");
		System.out.println("Trash");
		for (Folder folder : folders) {
			System.out.println(folder.getName());
		}
		System.out.print("Select a folder to move \"" + email.getSubject()
				+ "\" to: ");
		String folderName = input.nextLine().trim();
		if (folderName.toLowerCase().equals("inbox") || 
				findFolder(folderName) == -1) {
			inbox.addEmail(email);
		} else if (folderName.toLowerCase().equals("trash")) {
			trash.addEmail(email);
		} else {
			int folderIndex = findFolder(folderName);
			folders.get(folderIndex).addEmail(email);
		}
	}

	/**
	 * Finds the specified folder and returns it.
	 * @param name
	 * 	the name of the folder
	 * @return  the specified folder, if it is not found it returns -1.
	 */
	public int findFolder(String name) {
		if (folders == null) {
			return -1;
		}
		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Removes the given folder from the list of custom folders
	 * @param - none
	 */
	public void deleteFolder() {
		System.out.print("Folder name to remove: ");
		String folderName = input.nextLine().trim();
		int folderIndex = findFolder(folderName);
		if (folderIndex == -1) {
			System.out.println("This folder does not exist!");
			return;
		} else {
			folders.remove(folderIndex);
		}
	}

	/**
	 * Adds the given folder to the list of custom folders. If a folder 
	 * with that given name already exists it warns the user.
	 * @param - none
	 */
	public void addFolder() {
		System.out.print("Enter the folder name: ");
		String folderName = input.nextLine().trim();
		int folderIndex = findFolder(folderName);
		if (folderIndex != -1) {
			System.out.println("This folder already exists!");
			return;
		}
		Folder folder = new Folder(folderName);
		folders.add(folder);
	}

	/**
	 * Gets user input on the contents of the email and adds it to the inbox.
	 * @param - none
	 */
	public void composeEmail() {
		System.out.print("Enter Recipient (To): ");
		String to = input.nextLine();
		System.out.print("Enter Carbon Copy Recipients (CC): ");
		String cc = input.nextLine();
		System.out.print("Enter Blind Carbon Copy Recipients (BCC): ");
		String bcc = input.nextLine();
		System.out.print("Enter Subject Line: ");
		String subject = input.nextLine();
		System.out.print("Enter Body: ");
		String body = input.nextLine();
		Email email = new Email(to, cc, bcc, subject, body);
		inbox.addEmail(email);
		System.out.println("Email successfully added to inbox!");
	}

	/**
	 * Returns a folder by folder name.
	 * @param name
	 * 	the name of the folder
	 * @return the folder by folder name
	 */
	public Folder getFolder(String name) {
		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(name)) {
				return folders.get(i);
			}
		}
		return null;
	}
}
