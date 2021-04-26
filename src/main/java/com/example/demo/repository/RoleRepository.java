package com.example.demo.repository;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> origin/master
import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
<<<<<<< HEAD

    Optional<Role> findByName(ERole role);

}
=======
    Optional<Role> findByName(ERole role);
}
>>>>>>> thai
