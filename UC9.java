package AddressBook;

import java.util.*;


public class AddressBookMain {
	public static Contact getContact(String first, String last) {
		Scanner sc = new Scanner(System.in);
		Contact c = new Contact();
		c.setFirst(first);
		c.setLast(last);
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

	public static void main(String[] args) {
		AddressBookDictionary abd = new AddressBookDictionary();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Address Book System");
		boolean loop1 = true, loop2 = true,add_avail;
		int choice1, choice2;
		AddressBook book;
		String name,address_book_name,first_name,last_name,city,state;
		while (loop1) {
			System.out.println("Enter 1 to add new Address Book\n" + "Enter 2 to modify an Address Book\n" +
					"Enter 3 to view all people of a city\n" + "Enter 4 to view all people of a state\n" +
					"Enter 0 to exit");
			choice1 = Integer.parseInt(sc.nextLine());
			switch (choice1) {
			case 1:
				System.out.println("Enter the name of Address Book");
				address_book_name=sc.nextLine();
				abd.addAddressBook(address_book_name,getAddressBook(address_book_name));
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
							System.out.println("Enter first name");
							first_name=sc.nextLine();
							System.out.println("Enter last name");
							last_name=sc.nextLine();
							if(book.nameExists(first_name,last_name))
								System.out.println("Contact already exists");
							else
								book.addDetails(getContact(first_name, last_name));
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
			case 3 :
				System.out.println("Enter the name of city");
				city= sc.nextLine();
				abd.viewByCity(city);
				break;
			case 4 :
				System.out.println("Enter the name of State");
				state= sc.nextLine();
				abd.viewByState(state);
				break;

			default:
				loop1 = false;
				break;

			}
		}
	}
}

class Contact {
	protected String f_name;
	protected String l_name;
	protected String address;
	protected String city;
	protected String state;
	protected String zip;
	protected String phone_number;
	protected String email;

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
		return "Name :" + getFirst() + " " + getLast() + "\nAddress :" + getAddress() + " " + getCity() + " " + getState() + " " + getZip()
				+ "\nPhone Number " + getPhone() + "\nEmail id :  " + getEmail();
	}

}

class AddressBook extends Contact {
	private ArrayList<Contact> address_book = new ArrayList<>();
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean nameExists(String first_name, String last_name) {
		return address_book.stream().anyMatch(contact -> contact.getFirst().equalsIgnoreCase(first_name) &&
				contact.getLast().equalsIgnoreCase(last_name));
	}

	public void addDetails(Contact contact) {
		address_book.add(contact);
	}

	public void viewAllContacts() {
		for (Contact c : address_book) {
			System.out.println(c);
		}
	}
	public int countByCity(String city){
		return (int) address_book.stream().filter(c -> c.getCity().equalsIgnoreCase(city)).count();
	}
	public int countByState(String state){
		return (int) address_book.stream().filter(c -> c.getState().equalsIgnoreCase(state)).count();
	}
	public void viewByCity(String city){
		address_book.stream().filter(contact -> contact.getCity().equalsIgnoreCase(city)).forEach(contact -> System.out.println(contact));
	}
	public void viewByState(String state){
		address_book.stream().filter(contact -> contact.getState().equalsIgnoreCase(state)).forEach(contact -> System.out.println(contact));
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
				c.setFirst(f_name);
				c.setLast(l_name);
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

	AddressBook() {

	}
}

class AddressBookDictionary extends AddressBook {

	Map<String, AddressBook> address_book_dictionary = new HashMap<>();

	public void addAddressBook(String name,AddressBook addressbook) {
		address_book_dictionary.put(name,addressbook);
	}

	public boolean isPresentAddressBook(String name) {
		return address_book_dictionary.containsKey(name);
	}

	public AddressBook returnAddressBook(String name) {
		return address_book_dictionary.get(name);
	}

	public int countByCity(String city) {
		return address_book_dictionary.values().stream().mapToInt(addressBook -> addressBook.countByCity(city)).sum();
	}
	public int countByState(String state) {
		return address_book_dictionary.values().stream().mapToInt(addressBook -> addressBook.countByState(state)).sum();
	}
	public void viewByCity(String city) {
		address_book_dictionary.values().stream().forEach(addressBook -> addressBook.viewByCity(city));
	}
	public void viewByState(String state) {
		address_book_dictionary.values().stream().forEach(addressBook -> addressBook.viewByState(state));
	}

}
