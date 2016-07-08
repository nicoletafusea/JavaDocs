package ro.teamnet.zth.api.database;

import org.testng.annotations.Test;
import ro.teamnet.zth.api.em.EntityUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by user on 7/8/2016.
 */
public class DBManagerTest {

    @Test
    public void testGetConnection() {
        assertNotNull(DBManager.getConnection());
    }

    @Test
    public void testCheckConnection() {
        Connection c = DBManager.getConnection();
        assertEquals(true,DBManager.checkConnection(c));
    }

}
