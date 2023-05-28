package com.poriadin.brdo.repositories;

import com.poriadin.brdo.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String email);
}
