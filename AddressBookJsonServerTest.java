import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class AddressBookJsonServerTest {
    AddressBookDBOperations dbo = new AddressBookDBOperations();

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 5000;
    }

    @Test
    public void test_WriteDataToJsonServer() {
        List<Contact> contacts = dbo.retrieveDataFromDB();
        for(Contact e : contacts){
            HashMap<String, String> map = new HashMap<>();
            String fname = e.getFirstName();
            String lname = e.getLastName();
            String phNo = e.getPhNo();
            String email = e.getEmail();
            String date = e.getDate();

            map.put("firstName", fname);
            map.put("lastName", lname);
            map.put("Phone", phNo);
            map.put("Email", email);
            map.put("Date", date);
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(map)
                    .when()
                    .post("/contacts/create");
        }
    }

    @Test
    public void test_ReadFromJsonServer() {
        Response response = RestAssured.get("/contacts/list");
        System.out.println(response.asString());
    }
}
