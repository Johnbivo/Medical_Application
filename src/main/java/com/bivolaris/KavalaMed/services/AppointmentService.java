package com.bivolaris.KavalaMed.services;


import com.bivolaris.KavalaMed.dtos.AppointmentDto;
import com.bivolaris.KavalaMed.entities.Appointment;
import com.bivolaris.KavalaMed.mappers.AppointmentDtoMapper;
import com.bivolaris.KavalaMed.repositories.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapper appointmentDtoMapper;


    // Get all appointments
    public List<AppointmentDto> getAppointments() {
        return appointmentRepository.findAllWithPatientAndDoctor()
                .stream()
                .map(appointmentDtoMapper::toDto)
                .toList();
    }



    //Get specific appointment
    public AppointmentDto getAppointmentById(int id) {
        return appointmentRepository.findByIdWithPatientAndDoctor(id).map(appointmentDtoMapper::toDto).orElse(null);
    }


    //Create appointment
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    //Update appointment
    public Appointment updateAppointment(Appointment appointment) {
        if (appointment.getAppointmentId() == null || !appointmentRepository.existsById(appointment.getAppointmentId())) {
            return null;
        }
        return appointmentRepository.save(appointment);
    }


    //Delete appointment
    public boolean deleteAppointmentById(int id) {
        if(!appointmentRepository.existsById(id)){
            return false;
        }
        appointmentRepository.deleteById(id);
        return true;
    }
}
