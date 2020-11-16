import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBOperations {
    static AddressBookDBOperations ab_dbo = new AddressBookDBOperations();

    public Connection getDBConnection() {
        String jdbc_url = "jdbc:mysql://localhost:3306/addressbook_service?allowPublicKeyRetrieval=true&useSSL=false";
        String userName = "root";
        String password = "Ard2238";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded..");
            System.out.println("Connecting to the Database... " + jdbc_url);
            con = DriverManager.getConnection(jdbc_url, userName, password);
            System.out.println("Connection was successful !!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    public List<Contact> retrieveDataFromDB(){
        List<Contact> contactList = new ArrayList<>();
        try(Connection con = ab_dbo.getDBConnection()){
            String query = "Select * from address_book";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String address = rs.getString(3);
                String city = rs.getString(4);
                String state = rs.getString(5);
                String zipcode = rs.getString(6);
                String phone = rs.getString(7);
                String mail = rs.getString(8);
                String type = rs.getString(9);

                Contact c = new Contact(fname,lname,address,city,state,zipcode,phone,mail,type);
                contactList.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return contactList;
    }

    public void updateDetailsInDB(String firstName, String lastName, String column, String value) {
        try(Connection con = ab_dbo.getDBConnection()){
            String query = String.format("Update contact set " + column + " = %s where firstName = %s and lastName = %s ");
            PreparedStatement p_stmt = con.prepareStatement(query);
            p_stmt.setString(1,value);
            p_stmt.setString(2,firstName);
            p_stmt.setString(3,lastName);

            int result = p_stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public List<Contact> retrieveContactWithinDateRange(String startDate, String endDate) {
        List<Contact> contacts = new ArrayList<>();
        try(Connection con = ab_dbo.getDBConnection()){
            String query = String.format("Select * from contact where date_added between '"+ startDate + "' and '"+ endDate +"'");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String cid = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String date = rs.getString(6);

                Contact c = new Contact(cid,fname,lname,phone,email,date);
                contacts.add(c);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return contacts;
    }
}
