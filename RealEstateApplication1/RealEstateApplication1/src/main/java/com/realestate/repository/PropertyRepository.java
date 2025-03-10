package com.realestate.repository;

import com.realestate.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // ✅ Improves clarity
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    // ✅ Fetch all properties owned by a specific user
    List<Property> findByOwnerId(Long ownerId);

    // ✅ Search properties by location (case insensitive)
    List<Property> findByLocationContainingIgnoreCase(String location);

    // ✅ Filter properties by transaction type (RENT, BUY, SALE)
    List<Property> findByTransactionType(String transactionType);

    // ✅ Filter properties by price range
    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);

    // ✅ Fetch latest N properties (Optional)
    @Query("SELECT p FROM Property p ORDER BY p.id DESC")
    List<Property> findLatestProperties();
}
