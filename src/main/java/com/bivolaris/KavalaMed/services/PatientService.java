package com.bivolaris.KavalaMed.services;


import com.bivolaris.KavalaMed.dtos.AppointmentDto;
import com.bivolaris.KavalaMed.dtos.PatientDto;
import com.bivolaris.KavalaMed.entities.Patient;
import com.bivolaris.KavalaMed.mappers.AppointmentDtoMapperImpl;
import com.bivolaris.KavalaMed.mappers.PatientDtoMapper;
import com.bivolaris.KavalaMed.repositories.AppointmentRepository;
import com.bivolaris.KavalaMed.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {


    private final PatientRepository patientRepository;
    private final PatientDtoMapper patientDtoMapper;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapperImpl appointmentDtoMapperImpl;


    // Get all patients
    public List<PatientDto> getPatients(){
        return patientRepository.findAll()
                .stream()
                .map(patientDtoMapper::toDto)
                .toList();
    }

    // Get patient by id
    public PatientDto getPatientById(int id){
        return patientRepository.findById(id)
                .map(patientDtoMapper::toDto)
                .orElse(null);
    }

    //Create patient -- NO ACCOUNT CREATION
    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    //Update patient info
    public Patient updatePatient(Patient patient){
        if(patient.getPatientId() == null || patientRepository.existsById(patient.getPatientId())){
            return null;
        }
        return patientRepository.save(patient);
    }

    //Delete Patient
    public Boolean deletePatient(int id){
        if(patientRepository.existsById(id)){
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }


    // Get appointments by id
    public List<AppointmentDto> getAppointmentsByPatientId(int id){
        return appointmentRepository.findByPatientId(id)
                .stream()
                .map(appointmentDtoMapperImpl::toDto)
                .toList();
    }


}
