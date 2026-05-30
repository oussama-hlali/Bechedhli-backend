package com.bechedhli.solar.graphql;

import com.bechedhli.solar.entity.Employee;
import com.bechedhli.solar.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeGraphQLController {

    private final EmployeeService service;

    @QueryMapping
    public List<Employee> employees() {
        return service.findAll();
    }

    @QueryMapping
    public Employee employee(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public Employee createEmployee(@Argument Employee input) {
        return service.create(input);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument Long id, @Argument Employee input) {
        return service.update(id, input);
    }

    @MutationMapping
    public boolean deleteEmployee(@Argument Long id) {
        return service.delete(id);
    }
}
