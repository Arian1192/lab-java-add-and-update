package com.ironhack.lab.Intro.Spring.Boot.service;

import com.ironhack.lab.Intro.Spring.Boot.Enum.Department;
import com.ironhack.lab.Intro.Spring.Boot.Enum.Status;
import com.ironhack.lab.Intro.Spring.Boot.model.Employee;
import com.ironhack.lab.Intro.Spring.Boot.repository.EmployeeRepository;
import com.ironhack.lab.Intro.Spring.Boot.serviceInterface.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements CrudServiceInterface<Employee> {

    @Autowired
    private EmployeeRepository employeeRepository;


    // ---- CRUD SERVICE ---
    @Override
    public Employee getEntityById(String id) {
        try {
            Optional<Employee> doctorFound = employeeRepository.findById(Long.parseLong(id));
            if (doctorFound.isPresent()) {
                return doctorFound.get();
            } else {
                throw new ChangeSetPersister.NotFoundException();
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while finding the doctor");
        }
    }

    @Override
    public List<Employee> getAllEntities() {
        return employeeRepository.findAll();
    }

    @Override
    public void addNewEntity(Employee entity) {

    }

    @Override
    public void putEntity(String id, Employee entity) {

    }

    @Override
    public void patchEntity(Object dto) {

    }

    public List<Employee> findAllByStatus(String department){
        Status statusEnum = Status.valueOf(department.toUpperCase());
        return employeeRepository.findAllByStatus(statusEnum);
    }
    public List<Employee> findAllByDepartment(String department){
        Department departmentEnum = Department.valueOf(department.toUpperCase());
        return employeeRepository.findAllByDepartment(departmentEnum);
    }
}
