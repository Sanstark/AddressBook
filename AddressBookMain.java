import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
    static Scanner sc= new Scanner(System.in);
    static AddressBook adbook = new AddressBook();
    static AddressBookMain abm = new AddressBookMain();

    /* UC5 - UC6  -- Add multiple contacts and multiple address book */
    public static void initialize() throws CsvException, IOException {
        int main_menu_choice = 1;
        /* UC6 -- Add multiple books */
        while (main_menu_choice != 5) {
            System.out.println("1. Add an AddressBook");
            System.out.println("2. Modify an AddressBook");
            System.out.println("3. Queries on all AddressBooks");
            System.out.println("4. Read and Write Operations");
            System.out.println("5. Exit");

            main_menu_choice = sc.nextInt();
            sc.nextLine();
            switch (main_menu_choice) {
                case 1: {
                    abm.addAddressBook();
                    break;
                }
                case 2: {
                    abm.modifyAddressBook();
                    break;
                }
                case 3: {
                    abm.queriesAddressBookMap();
                    break;
                }
                case 4: {
                    abm.readWriteOperations();
                    break;
                }
            }
        }
    }

    private void addAddressBook() throws IOException, CsvException {
        int adbook_choice = 0;
        System.out.println("Do you want to add an Address Book? \n0. NO \n1. YES");
        adbook_choice = sc.nextInt(); sc.nextLine();
        if(adbook_choice == 1){
            System.out.println("Enter the name of the Address Book");
            String addressbook_name = sc.nextLine();
            AddressBook person1 = new AddressBook(addressbook_name);
            adbook.getAddressbook_map().put(addressbook_name, person1);
            System.out.println("Added A New Address Book");
        }
    }

    private void modifyAddressBook() {
        int modify_choice = 1;
        while(modify_choice != 5) {
            System.out.println("1. Add a Contact");
            System.out.println("2. Edit Details");
            System.out.println("3. Delete a Contact");
            System.out.println("4. View a Contact");
            System.out.println("5. Exit");

            modify_choice = sc.nextInt(); sc.nextLine();
            switch (modify_choice) {
                case 1: {
                    System.out.println("Enter the name of the address book you want to add the contact to: ");
                    String name_addbook = sc.nextLine();
                    List<Contact> foundBook = adbook.findBook(name_addbook);
                    if (foundBook != null)
                        adbook.addContact(foundBook);
                    break;
                }
                case 2: {
                    System.out.println("Enter the name of the address book you want to edit a contact from: ");
                    String name_adbook = sc.nextLine();
                    adbook.editDetails(name_adbook);
                    break;
                }
                case 3: {
                    System.out.println("Enter the first name and last name of the person you want to delete: ");
                    String del_fname = sc.nextLine();
                    String del_lname = sc.nextLine();
                    adbook.deleteContact(del_fname, del_lname);
                    break;
                }
                case 4: {
                    System.out.println("Enter the first name and last name of the person: ");
                    String view_fname = sc.nextLine();
                    String view_lname = sc.nextLine();
                    adbook.viewContact(view_fname, view_lname);
                }
            }
        }
    }

    private void queriesAddressBookMap() {
        int querymap_choice = 1;
        while(querymap_choice != 7) {
            System.out.println("1. Search Person in a City or State");
            System.out.println("2. Count Person by City or State");
            System.out.println("3. Sort Person's Details");
            System.out.println("4. Sort by City");
            System.out.println("5. Sort by State");
            System.out.println("6. Sort by ZipCode");
            System.out.println("7. Exit");

            querymap_choice = sc.nextInt(); sc.nextLine();
            switch (querymap_choice) {
                case 1: {
                    adbook.searchPersonCityState();
                    break;
                }
                case 2: {
                    long count = adbook.countByCityState();
                    System.out.println("The Count is : "+count);
                    break;
                }
                case 3: {
                    adbook.sortPersonDetails();
                    break;
                }
                case 4: {
                    adbook.sortByCity();
                    break;
                }
                case 5: {
                    adbook.sortByState();
                    break;
                }
                case 6: {
                    adbook.sortByZipCode();
                    break;
                }
            }
        }
    }

    private void readWriteOperations() throws IOException, CsvException {
        int rw_choice = 1;
        while(rw_choice != 7) {
            System.out.println("1. Write Data to File");
            System.out.println("2. Read Data from File");
            System.out.println("3. Read Data from CSV File");
            System.out.println("4. Write Data to CSV File");
            System.out.println("5. Read Data from Json File");
            System.out.println("6. Write Data to Json File");
            System.out.println("7. Exit");

            rw_choice = sc.nextInt(); sc.nextLine();
            switch (rw_choice) {
                case 1: {
                    adbook.writeDataToFile(); break;
                }
                case 2: {
                    adbook.readDataFromFile(); break;
                }
                case 3: {
                    adbook.readDataFromCSV(); break;
                }
                case 4: {
                    System.out.println("Enter the book that you want to write");
                    String bookName = sc.nextLine();
                    adbook.writeDataToCsv(bookName);
                    break;
                }
                case 5: {
                    adbook.readDataFromJson();
                    break;
                }
                case 6: {
                    System.out.println("Enter the book that you want to write");
                    String bookName = sc.nextLine();
                    adbook.writeDataToJson(bookName);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws CsvException, IOException {
        // TODO Auto-generated method stub

        System.out.println("Welcome to Address Book Program");
        initialize();
        return;
    }
}
