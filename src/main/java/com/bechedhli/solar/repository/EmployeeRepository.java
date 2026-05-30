package com.bechedhli.solar.repository;

import com.bechedhli.solar.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
