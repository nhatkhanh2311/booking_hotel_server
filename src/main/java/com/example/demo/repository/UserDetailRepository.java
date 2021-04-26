package com.example.demo.repository;

<<<<<<< HEAD
import com.example.demo.entity.UserDetails;
=======
import com.example.demo.entity.UserDetail;
>>>>>>> origin/master
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
