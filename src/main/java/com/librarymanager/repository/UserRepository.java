package com.librarymanager.repository;

import com.librarymanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.loans l WHERE l.status = :status")
    List<User> findByLoansStatus(@Param("status") String status);
}
