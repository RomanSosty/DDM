package org.example.ddm.service;

import org.example.ddm.entity.Employee;
import org.example.ddm.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUsername(username);
        return employee.map(EmployeeInfoDetail::new).orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }


    public String addEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return "Employee added successfully";
    }
}
