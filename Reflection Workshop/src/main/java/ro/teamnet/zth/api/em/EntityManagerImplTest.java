package ro.teamnet.zth.api.em;

import org.testng.annotations.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.sql.ResultSet;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImplTest {
    @Test
    public void testFindById() {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department res = entityManager.findById(Department.class, (long) 10);
        assertEquals("Administration", res.getDepartmentName());
    }

}
