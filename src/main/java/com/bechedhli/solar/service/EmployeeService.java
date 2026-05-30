package com.bechedhli.solar.service;

import com.bechedhli.solar.entity.Employee;
import com.bechedhli.solar.exception.ResourceNotFoundException;
import com.bechedhli.solar.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = findById(id);
        existing.setName(updated.getName());
        existing.setRole(updated.getRole());
        existing.setDept(updated.getDept());
        existing.setStatus(updated.getStatus());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        existing.setSalary(updated.getSalary());
        existing.setJoinDate(updated.getJoinDate());
        return repository.save(existing);
    }

    public boolean delete(Long id) {
        findById(id);
        repository.deleteById(id);
        return true;
    }
}
