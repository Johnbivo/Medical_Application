package com.bivolaris.KavalaMed.services;


import com.bivolaris.KavalaMed.dtos.DoctorDto;
import com.bivolaris.KavalaMed.dtos.DoctorRegisterRequest;
import com.bivolaris.KavalaMed.entities.Doctor;
import com.bivolaris.KavalaMed.entities.User;
import com.bivolaris.KavalaMed.mappers.DoctorDtoMapper;
import com.bivolaris.KavalaMed.mappers.DoctorRegisterRequestMapper;
import com.bivolaris.KavalaMed.repositories.DoctorRepository;
import com.bivolaris.KavalaMed.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorDtoMapper doctorDtoMapper;
    private final UserRepository userRepository;
    private final DoctorRegisterRequestMapper doctorRegisterRequestMapper;
    private final PasswordEncoder passwordEncoder;

    // GET ALL DOCTORS
    public List<DoctorDto> getDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorDtoMapper::toDto)
                .toList();
    }

    // GET DOCTOR BY ID
    public DoctorDto getDoctorbyId(int id) {
        return doctorRepository.findById(id).map(doctorDtoMapper::toDto).orElse(null);
    }



    //Register Doctors - protected by key
    @Transactional
    public boolean registerDoctor(DoctorRegisterRequest request) {
        try{
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new RuntimeException("Username already exists");
            }
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                throw new RuntimeException("Passwords do not match");
            }

            User user = doctorRegisterRequestMapper.toUser(request);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(User.Role.Doctor);
            user.setActivated(true);
            User savedUser = userRepository.save(user);

            Doctor doctor = doctorRegisterRequestMapper.toDoctor(request);
            doctor.setUser(savedUser);
            doctorRepository.save(doctor);
            return true;
        }
        catch(Exception e){
            throw new RuntimeException("Failed to register doctor: " + e.getMessage());
        }
    }




}
