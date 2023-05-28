package com.poriadin.brdo.services;

import com.poriadin.brdo.dto.AccountDto;
import com.poriadin.brdo.entities.Account;
import com.poriadin.brdo.entities.Role;
import com.poriadin.brdo.repositories.RoleRepository;
import com.poriadin.brdo.repositories.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        account.setRoles(List.of(role));
        accountRepository.save(account);
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public List<AccountDto> findAllUsers() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private AccountDto convertEntityToDto(Account user) {
        AccountDto userDto = new AccountDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
