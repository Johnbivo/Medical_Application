package com.bivolaris.KavalaMed.controllers;


import com.bivolaris.KavalaMed.config.JwtConfig;
import com.bivolaris.KavalaMed.dtos.JwtResponse;
import com.bivolaris.KavalaMed.dtos.LoginRequest;
import com.bivolaris.KavalaMed.dtos.RegisterRequest;
import com.bivolaris.KavalaMed.dtos.UserDto;
import com.bivolaris.KavalaMed.mappers.UserDtoMapper;
import com.bivolaris.KavalaMed.repositories.UserRepository;
import com.bivolaris.KavalaMed.services.JwtService;
import com.bivolaris.KavalaMed.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;


    // Login -- Not finished
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            var accessToken = jwtService.generateAccessToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            var cookie = new Cookie("refreshToken", refreshToken);
            cookie.setHttpOnly(true);
            cookie.setPath("/auth/refresh");
            cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
            cookie.setSecure(true);
            response.addCookie(cookie);

            return ResponseEntity.ok(new JwtResponse(accessToken));
    }


    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@CookieValue(value="refreshToken") String refreshToken){
        if(!jwtService.validateToken(refreshToken)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var userId = jwtService.getUsernameFromToken(refreshToken);
        var user = userRepository.findById(Integer.valueOf(userId)).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        return ResponseEntity.ok(new JwtResponse(accessToken));
    }




    // Register - only for patients/normal users

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        try {
            userService.register(request);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        var user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(userDtoMapper.toDto(user));
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }





}
