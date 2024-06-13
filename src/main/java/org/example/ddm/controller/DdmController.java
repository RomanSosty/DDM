package org.example.ddm.controller;

import org.example.ddm.entity.AuthRequest;
import org.example.ddm.entity.Employee;
import org.example.ddm.service.EmployeeServiceImpl;
import org.example.ddm.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DdmController {

    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/addNewEmployee")
    public String addNewEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PostMapping("/login")
    public String authAndGenerateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @GetMapping("/pageJoy")
    @PreAuthorize("hasAuthority('ROLE_JOY')")
    public String employeeProfile() {
        return "Welcome to JOY main page";
    }
}
