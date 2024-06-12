package org.example.ddm.service;

import org.example.ddm.EmployeeRepository;
import org.example.ddm.entity.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String addEmployee(Employee employee) {
      employee.setPassword(passwordEncoder.encode(employee.getPassword()));
      employeeRepository.save(employee);
      return "Employee added successfully";
    }
}
