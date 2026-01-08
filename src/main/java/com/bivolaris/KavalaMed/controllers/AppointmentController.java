package com.bivolaris.KavalaMed.controllers;


import com.bivolaris.KavalaMed.dtos.AppointmentDto;
import com.bivolaris.KavalaMed.entities.Appointment;
import com.bivolaris.KavalaMed.mappers.AppointmentDtoMapper;
import com.bivolaris.KavalaMed.services.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentDtoMapper appointmentDtoMapper;




    // Get all appointments
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAppointments();
        if (appointments == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointments);
    }

    // Get specific appointment
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable int id) {
        var appointmentDto = appointmentService.getAppointmentById(id);
        if (appointmentDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointmentDto);
    }



    //Create an appointment
    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        if (createdAppointment == null) {
            return ResponseEntity.badRequest().build();
        }
        AppointmentDto appointmentDto = appointmentDtoMapper.toDto(createdAppointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentDto);
    }


    //Update an appointment
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@RequestBody Appointment appointment, @PathVariable int id) {
       appointment.setAppointmentId(id);
       Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
       if (updatedAppointment == null) {
           return ResponseEntity.notFound().build();
       }
        AppointmentDto appointmentDto = appointmentDtoMapper.toDto(updatedAppointment);
        return ResponseEntity.ok(appointmentDto);
    }


    // Delete an appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        boolean deleted = appointmentService.deleteAppointmentById(id);
        if(!deleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
