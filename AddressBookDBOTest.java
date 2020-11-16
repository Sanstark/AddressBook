import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AddressBookDBOTest {
    AddressBookDBOperations dbo;
    @Before
    public void init(){
        dbo = new AddressBookDBOperations();
    }
    @Test
    public void check_retrieveData(){
        List<Contact> list = dbo.retrieveDataFromDB();
        Assert.assertEquals(4, list.size());
    }
}
