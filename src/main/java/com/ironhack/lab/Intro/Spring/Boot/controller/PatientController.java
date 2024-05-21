package com.ironhack.lab.Intro.Spring.Boot.controller;

import com.ironhack.lab.Intro.Spring.Boot.Enum.Department;
import com.ironhack.lab.Intro.Spring.Boot.Enum.Status;
import com.ironhack.lab.Intro.Spring.Boot.model.Patient;
import com.ironhack.lab.Intro.Spring.Boot.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/by-dob")
    public List<Patient> findAllByDateOfBirthBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return patientService.findAllByDateOfBirthBetween(startDate, endDate);
    }


    @GetMapping("/by-department")
    public List<Patient> findAllByEmployeeDepartment(@RequestParam("department") Department department) {
        return patientService.findAllByEmployeeDepartment((Department) department);
    }

    @GetMapping("/by-status")
    public List<Patient> findAllByEmployeeStatus(Status status) {
        return patientService.findAllByEmployeeStatus((Status) status);
    }


    @GetMapping("/by-status-off")
    public List<Patient> findAllByEmployeeStatusOff() {
        return patientService.findAllByEmployeeStatusOff();
    }

    @GetMapping("/{id}")
    public Patient getEntityById(@PathVariable String id) {
        return patientService.getEntityById(id);
    }

    @GetMapping("")
    public List<Patient> getAllEntities() {
        return patientService.getAllEntities();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEntity(@RequestBody @Valid Patient patient) {
        patientService.addNewEntity(patient);
    }

    @PutMapping("/{id}")
    public void putEntity(@PathVariable String id, @RequestBody Patient patient) {
        patientService.putEntity(id, patient);
    }

    @PatchMapping("/{id}")
    public void patchEntity(Object dto) {

    }
}
