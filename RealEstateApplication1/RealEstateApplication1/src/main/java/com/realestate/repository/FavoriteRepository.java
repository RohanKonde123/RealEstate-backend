package com.realestate.repository;

import com.realestate.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository // ✅ Improves clarity
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
    // ✅ Fetch all favorites by User ID
    List<Favorite> findByUserId(Long userId);

    // ✅ Fetch a specific favorite by User ID and Property ID
    Optional<Favorite> findByUserIdAndPropertyId(Long userId, Long propertyId);

    // ✅ Fetch all favorites for a specific property
    List<Favorite> findByPropertyId(Long propertyId);

    // ✅ Fetch the latest N favorite properties for a user (Optional)
    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId ORDER BY f.id DESC")
    List<Favorite> findLatestFavoritesByUser(Long userId);
}
