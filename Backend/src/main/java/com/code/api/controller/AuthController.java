package com.code.api.controller;

import com.code.api.entity.User;
import com.code.api.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Log the password to check if it is coming through correctly
        System.out.println("Password before encoding: " + user.getPassword());

        // Check for existing username and email
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        // Encode password and save user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Password after encoding: " + user.getPassword());  // Log encoded password

        user.setRole("PATIENT");
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }


    // Login user (No JWT, just a simple session-based approach or random token)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());

        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Generate a temporary token (You can use any logic here to generate a token)
            String token = UUID.randomUUID().toString();  // Temporary token

            // Store the token in some kind of session or a memory-based storage (Optional)
            // In a real scenario, you'd use a session or a redis cache to store the token.

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token); // Return a simple token to the client (you can store this on the client-side)
            response.put("user", existingUser); // Send user info in the response

            return ResponseEntity.ok(response);
        }

        // Return unauthorized if username or password is incorrect
        Map<String, String> error = new HashMap<>();
        error.put("message", "Invalid username or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
