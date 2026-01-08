package com.bivolaris.KavalaMed.controllers;


import com.bivolaris.KavalaMed.dtos.AppointmentDto;
import com.bivolaris.KavalaMed.dtos.PatientDto;
import com.bivolaris.KavalaMed.entities.Patient;
import com.bivolaris.KavalaMed.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;


    // Get all patients
    @GetMapping
    public ResponseEntity<List<PatientDto>> getPatients() {
        List<PatientDto> patients = patientService.getPatients();
        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patients);
    }


    //Get patient by id
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable int id) {
        PatientDto patient = patientService.getPatientById(id);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }


    //Create patient --NO ACCOUNT
    @PostMapping
    public ResponseEntity<String> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.createPatient(patient);
        if (createdPatient == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created patient.");
    }


    //Update patient info
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@RequestBody Patient patient, @PathVariable int id) {
        patient.setPatientId(id);
        Patient updatedPatient = patientService.updatePatient(patient);
        if (updatedPatient == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated patient.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id) {
        if (!patientService.deletePatient(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Patient deleted successfully.");
    }

    //Patient views his appointments
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByPatientId(@PathVariable int id) {
        List<AppointmentDto> appointments = patientService.getAppointmentsByPatientId(id);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appointments);
    }

}
