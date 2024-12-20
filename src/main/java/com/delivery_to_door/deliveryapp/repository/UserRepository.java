package com.delivery_to_door.deliveryapp.repository;

import com.delivery_to_door.deliveryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    int countUserByEmail(String email);

    User save(User user);
}
