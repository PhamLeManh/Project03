package k23cnt3.plmDay03.service;

import k23cnt3.plmDay03.entity.PlmEmployee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlmEmployeeService {
    private List<PlmEmployee> employeeList = new ArrayList<>();

    public PlmEmployeeService() {
        employeeList.addAll(Arrays.asList(
                new PlmEmployee(1L, "Nguyễn Văn A", "Nam", 25, 15000000),
                new PlmEmployee(2L, "Trần Thị B", "Nữ", 28, 18000000),
                new PlmEmployee(3L, "Lê Văn C", "Nam", 32, 22000000),
                new PlmEmployee(4L, "Phạm Thị D", "Nữ", 26, 16000000),
                new PlmEmployee(5L, "Hoàng Văn E", "Nam", 30, 20000000)
        ));
    }

    public List<PlmEmployee> getAllEmployees() {
        return employeeList;
    }

    public PlmEmployee getEmployeeById(Long id) {
        return employeeList.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public PlmEmployee addEmployee(PlmEmployee employee) {
        employeeList.add(employee);
        return employee;
    }

    public PlmEmployee updateEmployee(Long id, PlmEmployee employee) {
        PlmEmployee existingEmployee = getEmployeeById(id);
        if (existingEmployee == null) {
            return null;
        }

        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setSalary(employee.getSalary());
        return existingEmployee;
    }

    public boolean deleteEmployee(Long id) {
        PlmEmployee employee = getEmployeeById(id);
        if (employee != null) {
            return employeeList.remove(employee);
        }
        return false;
    }
}