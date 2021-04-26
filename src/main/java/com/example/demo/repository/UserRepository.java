package com.example.demo.repository;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> origin/master
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsById( long id);
    User findById(long id);
<<<<<<< HEAD

=======
>>>>>>> thai

}
