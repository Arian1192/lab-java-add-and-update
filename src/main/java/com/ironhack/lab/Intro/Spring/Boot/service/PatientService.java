package com.ironhack.lab.Intro.Spring.Boot.service;

import com.ironhack.lab.Intro.Spring.Boot.Enum.Department;
import com.ironhack.lab.Intro.Spring.Boot.Enum.Status;
import com.ironhack.lab.Intro.Spring.Boot.model.Employee;
import com.ironhack.lab.Intro.Spring.Boot.model.Patient;
import com.ironhack.lab.Intro.Spring.Boot.repository.EmployeeRepository;
import com.ironhack.lab.Intro.Spring.Boot.repository.PatientRepository;
import com.ironhack.lab.Intro.Spring.Boot.serviceInterface.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService implements CrudServiceInterface<Patient> {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Patient getEntityById(String id) {
        try {
            Optional<Patient> patientFound = patientRepository.findById(Long.parseLong(id));
            if (patientFound.isPresent()) {
                return patientFound.get();
            } else {
                throw new ChangeSetPersister.NotFoundException();
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while finding the patient");
        }
    }

    @Override
    public List<Patient> getAllEntities() {
        return patientRepository.findAll();
    }

    @Override
    public void addNewEntity(Patient entity) {
        patientRepository.save(entity);
    }

    @Override
    public void putEntity(String id, Patient entity) {
        if(Objects.equals(id, String.valueOf(entity.getId()))){
            Optional<Patient> maybePatientFound = patientRepository.findById(Long.parseLong(id));
            maybePatientFound.ifPresent(patient -> {
                patient.setId(entity.getId());
                patient.setName(entity.getName());
                patient.setDateOfBirth(entity.getDateOfBirth());
                Optional<Employee> maybeFoundEmployee = employeeRepository.findById(entity.getAdmittedBy().getEmployeeId());
                maybeFoundEmployee.ifPresent(patient::setAdmittedBy);
                patientRepository.save(patient);
            });
        }
    }

    @Override
    public void patchEntity(Object dto) {

    }

    // ________SPECIALIZATION ABOUT PATIENT SERVICE_____
    public List<Patient> findAllByDateOfBirthBetween(LocalDate startDate, LocalDate endDate) {
        try{
            return patientRepository.findByDateOfBirthBetween(startDate, endDate);
        }catch (Exception e){
            throw new RuntimeException("An error occurred while finding the patients");
        }

    }

    public List<Patient> findAllByEmployeeDepartment(Department department) {
            return patientRepository.findAllByEmployeeDepartment(department);
    }

    public List<Patient> findAllByEmployeeStatus(Status status) {
            return patientRepository.findAllByEmployeeStatus(status);
    }
    public List<Patient> findAllByEmployeeStatusOff() {
            return patientRepository.findAllByEmployeeStatusOff();
    }
}
