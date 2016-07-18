package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao = new DepartmentDao();

    public List<Department> findAllDepartments() {
        return departmentDao.findAllDepartments();
    }

    public Department findOneDepartment(Long id) {
        return departmentDao.findDepartmentById(id);
    }
}
