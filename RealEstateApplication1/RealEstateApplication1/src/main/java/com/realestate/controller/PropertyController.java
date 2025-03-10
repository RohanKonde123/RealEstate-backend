package com.realestate.controller;

import com.realestate.entity.Property;
import com.realestate.entity.User;
import com.realestate.repository.UserRepository;
import com.realestate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow React frontend
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private UserRepository userRepository;

    // ✅ Add a new property (only for authenticated users)
    @PostMapping("/add")
    public ResponseEntity<Property> addProperty(@RequestBody Property property, Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            return ResponseEntity.status(401).build(); // 🚀 Handle unauthorized users
        }
        
        String username = authentication.getName();
        User owner = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        property.setOwner(owner);
        
        Property saved = propertyService.addProperty(property);
        return ResponseEntity.ok(saved);
    }

    // ✅ Fetch all properties (publicly accessible)
    @GetMapping("/all")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }
}
