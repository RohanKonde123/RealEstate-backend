package com.realestate.service;

import com.realestate.entity.Property;
import com.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    
    // ✅ Add Property
    public Property addProperty(Property property) {
        if (property.getTitle() == null || property.getPrice() == null) {
            throw new IllegalArgumentException("Title and price cannot be null.");
        }
        return propertyRepository.save(property);
    }

    // ✅ Get All Properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // ✅ Get Property by ID
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with ID: " + id));
    }

    // ✅ Update Property
    public Property updateProperty(Long id, Property updatedProperty) {
        Property existingProperty = getPropertyById(id);
        existingProperty.setTitle(updatedProperty.getTitle());
        existingProperty.setDescription(updatedProperty.getDescription());
        existingProperty.setPrice(updatedProperty.getPrice());
        existingProperty.setLocation(updatedProperty.getLocation());
        existingProperty.setTransactionType(updatedProperty.getTransactionType());
        return propertyRepository.save(existingProperty);
    }

    // ✅ Delete Property
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new RuntimeException("Property not found with ID: " + id);
        }
        propertyRepository.deleteById(id);
    }
}
