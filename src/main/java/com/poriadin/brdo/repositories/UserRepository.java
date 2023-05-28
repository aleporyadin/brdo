package com.poriadin.brdo.repositories;

import com.poriadin.brdo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
