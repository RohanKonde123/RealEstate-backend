package com.realestate.controller;

import com.realestate.entity.Favorite;
import com.realestate.entity.Property;
import com.realestate.entity.User;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepository;
import com.realestate.service.FavoriteService;
import com.realestate.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:3000")  // ✅ Explicitly allow React frontend
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    @Autowired
    private FavoriteRepository favoriteRepository;
    
    // Add a property to favorites for the authenticated user
    @PostMapping("/add")
    public ResponseEntity<Favorite> addFavorite(@RequestParam Long propertyId, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                   .orElseThrow(() -> new RuntimeException("User not found"));
        Property property = propertyRepository.findById(propertyId)
                   .orElseThrow(() -> new RuntimeException("Property not found"));
        // Check if already favorited
        Optional<Favorite> existing = favoriteRepository.findByUserIdAndPropertyId(user.getId(), propertyId);
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProperty(property);
        Favorite saved = favoriteService.addFavorite(favorite);
        return ResponseEntity.ok(saved);
    }
    
    // Get favorites of the authenticated user
    @GetMapping("/my")
    public ResponseEntity<List<Favorite>> getMyFavorites(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                   .orElseThrow(() -> new RuntimeException("User not found"));
        List<Favorite> favorites = favoriteService.getFavoritesByUser(user.getId());
        return ResponseEntity.ok(favorites);
    }
}
