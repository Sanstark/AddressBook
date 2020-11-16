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

    public int retrieveContactsByCity(String city) {
        int count = 0;
        try(Connection con = ab_dbo.getDBConnection()){
            String query = String.format("Select count(contact_id) from address_details where city = '" + city + "'");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                count = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public void addContactAtomicTransaction(String cid, String fname, String lname, String phone, String mail,
                                        String date, String addr, String city, String state, String zip, String type) throws SQLException {

        boolean result1 = false, result2 = false, result3 = false;
        Connection con = ab_dbo.getDBConnection();
        try{
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            String query1 = String.format("Insert into contact values ('" + cid + "','" + fname + "','" + lname + "','" + phone + "','" + mail + "','" + date + "');");
            int result = stmt.executeUpdate(query1);
            if(result == 1)
                result1 = true;

            String query2 = String.format("Insert into address_details values ('" + cid + "','" + addr + "','" + city + "','" + state + "','" + zip + "');");
            result = stmt.executeUpdate(query2);
            if(result == 1)
                result2 = true;

            String query3 = String.format("Insert into type values ('" + cid + "','" + type + "');");
            result = stmt.executeUpdate(query3);
            if(result == 1)
                result3 = true;

            if(result1 && result2 && result3)
                con.commit();
        }catch (SQLException e){
            e.printStackTrace();
            con.rollback();
        }finally {
            con.close();
        }
    }

    public void addContactsWithThread(List<Contact> contacts) {
        contacts.forEach(contact ->{
            Runnable task = () -> {
                try {
                    this.addContactAtomicTransaction(contact.getCid(), contact.getFirstName(), contact.getLastName(), contact.getPhNo(),
                                                     contact.getEmail(), contact.getDate(), contact.getAddress(), contact.getCity(),
                                                     contact.getState(), contact.getZipcode(), contact.getType());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            };
            Thread thread =  new Thread(task);
            thread.start();
        });
        try{
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int countNumEntries() throws SQLException {
        int count = 0;
        try(Connection con = this.getDBConnection()){
            String query = "select count(contact_id) from contact ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                count = rs.getInt(1);
            }
        }
        return count;
    }
}
