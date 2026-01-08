package com.bivolaris.KavalaMed.controllers;


import com.bivolaris.KavalaMed.config.AdminConfig;
import com.bivolaris.KavalaMed.dtos.DoctorRegisterRequest;
import com.bivolaris.KavalaMed.dtos.SecretaryRegisterRequest;
import com.bivolaris.KavalaMed.services.DoctorService;
import com.bivolaris.KavalaMed.services.SecretaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminConfig adminConfig;
    private final DoctorService doctorService;
    private final SecretaryService secretaryService;

    public AdminController(AdminConfig adminConfig, DoctorService doctorService, SecretaryService secretaryService) {
        this.adminConfig = adminConfig;
        this.doctorService = doctorService;
        this.secretaryService = secretaryService;
    }


    // Register a doctor only with admin key
    @PostMapping("/register-doctor")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorRegisterRequest request,
                                                 @RequestHeader("X-Admin-Key") String adminKey){

        if (!adminConfig.getKey().equals(adminKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid admin key");
        }

        try {
            boolean success = doctorService.registerDoctor(request);
            if (success) {
                return ResponseEntity.ok("Doctor registered successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register doctor");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/register-secretary")
    public ResponseEntity<String> registerSecretary(@RequestBody SecretaryRegisterRequest request,
                                                    @RequestHeader("X-Admin-Key") String adminKey) {

        if (!adminConfig.getKey().equals(adminKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid admin key");
        }

        try {
            boolean success = secretaryService.registerSecretary(request);
            if (success) {
                return ResponseEntity.ok("Secretary registered successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register secretary");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
