package com.cognito.login.controller;

import com.cognito.login.model.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class RestrictedInfoController {

    @RequestMapping(value = "/restrictedInfo", produces = "application/json")
    public Object getEmployeesList(ModelAndView mav) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Santiago", "santiago@email.com"),
                new Employee(2, "Salvador", "salvador@email.com"),
                new Employee(3, "Alicia", "alicia@email.com"),
                new Employee(4, "Laura", "laura@email.com"),
                new Employee(5, "Javier", "javier@email.com")
        );

        mav.setViewName("info");
        mav.addObject("employees", employees);

        return mav;
    }

}
