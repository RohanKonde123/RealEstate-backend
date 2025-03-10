package com.realestate.repository;

import com.realestate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository // ✅ Improves clarity
public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ Fetch a user by username
    Optional<User> findByUsername(String username);

    // ✅ Check if a username already exists
    boolean existsByUsername(String username);

    // ✅ Fetch users by role (USER, AGENT, ADMIN)
    List<User> findByRole(String role);

    // ✅ Fetch user by email (Optional)
    Optional<User> findByEmail(String email);
}
