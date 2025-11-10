package k23cnt3.plmDay03.controller;

import k23cnt3.plmDay03.entity.PlmEmployee;
import k23cnt3.plmDay03.service.PlmEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class PlmEmployeeController {

    @Autowired
    private PlmEmployeeService employeeService;

    @GetMapping
    public List<PlmEmployee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public PlmEmployee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public PlmEmployee addEmployee(@RequestBody PlmEmployee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public PlmEmployee updateEmployee(@PathVariable Long id, @RequestBody PlmEmployee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}