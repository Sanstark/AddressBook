import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AddressBookDBOTest {
    AddressBookDBOperations dbo;
    AddressBook adbook;
    @Before
    public void init(){
        dbo = new AddressBookDBOperations();
        adbook = new AddressBook();
    }
    @Test
    public void check_retrieveData(){
        List<Contact> list = dbo.retrieveDataFromDB();
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void check_UpdateDetailsInBookAndDB(){
    }

    @Test
    public void check_RetrieveContactWithinDateRange(){
        List<Contact> list = dbo.retrieveContactWithinDateRange("2019-02-01","2019-10-01");
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void check_RetrieveNumContactsByCity(){
        int count = dbo.retrieveContactsByCity("GGN");
        Assert.assertEquals(2, count);
    }
}
