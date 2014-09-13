/**
 * The <CODE>MailboxRunner</CODE> class contains the main method of the
 * 	program and displays the menu options. When the program begins if the 
 * 	file "mailbox.obj" exists in the current directory it initializes the
 *  mailbox with the data in this file using serialization. Otherwise, 
 *  the program starts with an empty mailbox.
 * 
 * @author Erica Troge (erica.troge@stonybrook.edu) 106861428
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MailboxRunner {
	static Scanner input = new Scanner(System.in);
	public static Mailbox mailbox = null;

	/**
	 * The main method that prompts the user pick a menu option.
	 * @param args
	 */
	public static void main(String[] args) {
		readMailBox();
		if (mailbox == null) {
			mailbox = new Mailbox();
		}
		do {
			printMenu();
		} while (takeMenuOption());
	}

	/**
	 * This method sees if the file "mailbox.obj" exists in the current
	 *  directory. If so, it initializes the mailbox with the data in 
	 *  this file using serialization.
	 *  @param - none
	 */
	private static void readMailBox() {
		try {
			FileInputStream file = new FileInputStream("mailbox.obj");
			ObjectInputStream fin;
			while (file.available() > 0) {
				fin = new ObjectInputStream(file);
				mailbox = (Mailbox) fin.readObject();
			}
			file.close();
		} catch (Exception e) {
			System.out.println("Previous save not found, starting with " +
					"an empty mailbox.");
		}

	}

	/**
	 * This method saves the contents of the session to the file "mailbox.obj"
	 * @param - none
	 */
	private static void saveMailbox() {
		try {
			FileOutputStream file = new FileOutputStream("mailbox.obj");
			ObjectOutputStream fout = new ObjectOutputStream(file);
			fout.writeObject(mailbox);
			fout.close();
		} catch (IOException a) {
			a.printStackTrace();
		}
	}

	/**
	 * A method that prints the menu options.
	 * @param - none
	 * @precondition
	 * 	The Mailbox object has been instantiated.
	 * @postcondition
	 * 	A list of all menu options that can be performed on 
	 * 	the Mailbox during this session.
	 */
	public static void printMenu() {
		mailbox.printMailboxFolders();
		System.out.println("");
		System.out.println("A) Add Folder");
		System.out.println("R) Remove Folder");
		System.out.println("C) Compose Email");
		System.out.println("F) View Folder"); // submenu
		System.out.println("I) View Inbox"); // submenu
		System.out.println("T) View Trash"); // submenu
		System.out.println("E) Empty Trash");
		System.out.println("Q) Quit");
		System.out.println("\nChoice: ");
	}

	/**
	 * A method that prints the sub-menu options.
	 * @param - none
	 * @precondition
	 * 	The Mailbox object has been instantiated.
	 * @postcondition
	 * 	A list of all sub-menu options that can be performed on 
	 * 	the Mailbox during this session.
	 */
	public static void printSubMenu() {
		System.out.println("");
		System.out.println("M) Move Email");
		System.out.println("D) Delete Email");
		System.out.println("V) View Email Contents");
		System.out.println("SA) Sort by subject ascending");
		System.out.println("SD) Sort by subject descending");
		System.out.println("DA) Sort by date ascending");
		System.out.println("DD) Sort by date descending");
		System.out.println("R) Return to main menu");
		System.out.println("\nChoice: ");
	}

	/**
	 * A method that takes the menu option input from the user and
	 * 	then calls the corresponding method associated with it. It
	 * 	is also case insensitive.
	 * @param - none
	 * @postcondition
	 * 	The corresponding method that is associated with each menu
	 * 	option is called.
	 */
	public static boolean takeMenuOption() {
		char menuOption = input.next().toLowerCase().charAt(0);
		switch (menuOption) {
		case 'a':
			mailbox.addFolder();
			return true;
		case 'r':
			mailbox.deleteFolder();
			return true;
		case 'c':
			mailbox.composeEmail();
			return true;
		case 'f':
			System.out.print("Enter folder name: ");
			String folderName = input.nextLine().trim();

			do {
				mailbox.viewFolder(folderName);
				printSubMenu();
			} while (takeSubMenuOption());
			return true;
		case 'i':
			do {
				mailbox.viewInbox();
				printSubMenu();
			} while (takeSubMenuOption());
			return true;
		case 't':
			do {
				mailbox.viewTrash();
				printSubMenu();
			} while (takeSubMenuOption());
			return true;
		case 'e':
			mailbox.clearTrash();
			return true;
		case 'q':
			saveMailbox();
			System.out.println("Program successfully exited and " +
					"mailbox saved.");
			System.exit(0);
		default:
			System.out.println("You have not entered a correct menu "
					+ "option, please try again.");
			return true;
		}
	}

	/**
	 * A method that takes the sub-menu option input from the user and
	 * 	then calls the corresponding method associated with it. It
	 * 	is also case insensitive.
	 * @param - none
	 * @postcondition
	 * 	The corresponding method that is associated with each sub-menu
	 * 	option is called.
	 */
	private static boolean takeSubMenuOption() {
		String menuOption = input.next().toLowerCase().trim();
		switch (menuOption) {
		case "m":
			mailbox.moveEmail();
			return true;
		case "d":
			mailbox.deleteEmail();
			return true;
		case "v":
			mailbox.viewEmailContents();
			return true;
		case "sa":
			mailbox.sortSubjectAscending();
			return true;
		case "sd":
			mailbox.sortSubjectDescending();
			return true;
		case "da":
			mailbox.sortDateAscending();
			return true;
		case "dd":
			mailbox.sortDateDescending();
			return true;
		case "r":
			return false;
		default:
			System.out.println("You have not entered a correct menu "
					+ "option, please try again.");
			return true;
		}
	}
}
