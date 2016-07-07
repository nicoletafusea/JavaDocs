package ro.teamnet.zth.api.em;

import org.testng.annotations.Test;
import ro.teamnet.zth.appl.domain.Department;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by user on 7/8/2016.
 */
public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

}
