package AddressBook;

import java.util.*;

public class AddressBookMain {
	public static Contact getContact() {
		Scanner sc = new Scanner(System.in);
		Contact c = new Contact();
		System.out.println("Enter First Name");
		c.setFirst(sc.nextLine());
		System.out.println("Enter Last Name");
		c.setLast(sc.nextLine());
		System.out.println("Enter Address");
		c.setAddress(sc.nextLine());
		System.out.println("Enter City");
		c.setCity(sc.nextLine());
		System.out.println("Enter State");
		c.setState(sc.nextLine());
		System.out.println("Enter ZIP");
		c.setZip(sc.nextLine());
		System.out.println("Enter Phone Number");
		c.setPhone(sc.nextLine());
		System.out.println("Enter Email Id");
		c.setEmail(sc.nextLine());
		return c;

	}

	public static AddressBook getAddressBook(String name) {
		AddressBook ab = new AddressBook();
		ab.setName(name);
		return ab;
	}

	public static void main(String args[]) {
		AddressBookDictionary abd = new AddressBookDictionary();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Address Book System");
		boolean loop1 = true, loop2 = true,is_present1,is_present2,add_avail;
		int choice1, choice2;
		AddressBook book;
		String name;
		while (loop1) {
			System.out.println("Enter 1 to add new Address Book\n" + "Enter 2 to modify an Address Book\n" + "Enter 0 to exit");
			choice1 = Integer.parseInt(sc.nextLine());
			switch (choice1) {
			case 1:
				System.out.println("Enter the name of Address Book");
				abd.addAddressBook(getAddressBook(sc.nextLine()));
				break;
			case 2:
				System.out.println("Enter the name of Address Book");
				name = sc.nextLine();
				add_avail = abd.isPresentAddressBook(name);
				if (add_avail == false)
					System.out.println("Address Book not found");
				else {

					book = abd.returnAddressBook(name);
					loop2=true;
					while (loop2) {
						System.out.println("Enter 1 to add contact\n" + "Enter 2 to view all contacts\n"
								+ "Enter 3 to edit a contact\n" + "Enter 4 to delete a contact\n" + "Enter 0 to exit");
						choice2 = Integer.parseInt(sc.nextLine());
						switch (choice2) {
						case 1:
							book.addDetails(getContact());
							break;
						case 2:
							book.viewAllContacts();
							break;
						case 3:
							book.editContact();
							break;
						case 4:
							book.deleteContact();
							break;
						default:
							loop2=false;
							break;
						}
					}
				
				}
				break;
			default:
				loop1 = false;
				break;

			}
		}
	}
}

class Contact {
	protected String f_name, l_name, address, city, state, zip, phone_number, email;

	public void setFirst(String f_name) {
		this.f_name = f_name;
	}

	public String getFirst() {
		return f_name;
	}

	public void setLast(String l_name) {
		this.l_name = l_name;
	}

	public String getLast() {
		return l_name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return zip;
	}

	public void setPhone(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPhone() {
		return phone_number;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String toString() {
		return "Name :" + f_name + " " + l_name + "\nAddress :" + address + " " + city + " " + state + " " + zip
				+ "\nPhone Number " + phone_number + "\nEmail id :  " + email;
	}

}

class AddressBook extends Contact {
	private ArrayList<Contact> address_book = new ArrayList<Contact>();
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addDetails(Contact contact) {
		address_book.add(contact);
	}

	public void viewAllContacts() {
		for (Contact c : address_book) {
			System.out.println(c);
		}
	}

	public void deleteContact() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first name");
		String first_name = sc.nextLine();
		System.out.println("Enter last name");
		String last_name = sc.nextLine();
		boolean check = false;
		for (Contact c : address_book) {
			if (c.getFirst().equalsIgnoreCase(first_name) && c.getLast().equalsIgnoreCase(last_name)) {
				address_book.remove(c);
				check = true;
				break;
			}
		}
		if (check == false)
			System.out.println("Contact not found");
		else
			System.out.println("Contact deleted");
	}

	public void editContact() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first name");
		String first_name = sc.nextLine();
		System.out.println("Enter last name");
		String last_name = sc.nextLine();
		boolean check = false;
		for (Contact c : address_book) {
			if (c.f_name.equalsIgnoreCase(first_name) && c.l_name.equalsIgnoreCase(last_name)) {
				System.out.println("Enter Address");
				c.setAddress(sc.nextLine());
				System.out.println("Enter City");
				c.setCity(sc.nextLine());
				System.out.println("Enter State");
				c.setState(sc.nextLine());
				System.out.println("Enter ZIP");
				c.setZip(sc.nextLine());
				System.out.println("Enter Phone Number");
				c.setPhone(sc.nextLine());
				System.out.println("Enter Email Id");
				c.setEmail(sc.nextLine());
				check = true;
				break;
			}
		}
		if (check == false)
			System.out.println("Contact not found");
		else
			System.out.println("Contact edited");
	}

	AddressBook(String f_name, String l_name, String address, String city, String state, String zip,
			String phone_number, String email) {

	}

	AddressBook() {

	}
}

class AddressBookDictionary extends AddressBook {
	private ArrayList<AddressBook> address_book = new ArrayList<AddressBook>();

	public void addAddressBook(AddressBook addressbook) {
		address_book.add(addressbook);
	}

	public boolean isPresentAddressBook(String name) {
		boolean check = false;
		for (AddressBook ab : address_book) {
			if (ab.getName().equals(name)) {
				check = true;
				break;
			}

		}
		return check;
	}

	public AddressBook returnAddressBook(String name) {
		AddressBook ret_ab = address_book.get(0);
		for (AddressBook ab : address_book) {
			if (ab.getName().equals(name)) {
				ret_ab = ab;
				break;
			}
		}
		return ret_ab;
	}

}
