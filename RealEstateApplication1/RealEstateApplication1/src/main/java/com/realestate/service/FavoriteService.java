package com.realestate.service;

import com.realestate.entity.Favorite;
import com.realestate.repository.FavoriteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    // ✅ Constructor-based dependency injection (Best Practice)
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public Favorite addFavorite(Favorite favorite) {
        if (favorite == null || favorite.getUser() == null || favorite.getProperty() == null) {
            throw new IllegalArgumentException("Invalid favorite details.");
        }

        // ✅ Check if favorite already exists
        Optional<Favorite> existingFavorite = favoriteRepository.findByUserIdAndPropertyId(
            favorite.getUser().getId(), favorite.getProperty().getId()
        );

        if (existingFavorite.isPresent()) {
            throw new RuntimeException("Property is already in favorites.");
        }

        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public void removeFavorite(Long favoriteId) {
        if (!favoriteRepository.existsById(favoriteId)) {
            throw new RuntimeException("Favorite not found with ID: " + favoriteId);
        }
        favoriteRepository.deleteById(favoriteId);
    }
}
