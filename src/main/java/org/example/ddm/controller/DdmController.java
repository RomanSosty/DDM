package org.example.ddm.controller;

import org.example.ddm.entity.Employee;
import org.example.ddm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DdmController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/index")
    public String index() {
        return "Hlavní stránka DDM Orlová";
    }

    @PostMapping("/addNewEmployee")
    public String addNewEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }
}
