package com.shop.seeder;

import com.shop.Repository.UserRepository;
import com.shop.entity.Role;
import com.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void init() {
        Role employeeRole = new Role();
        employeeRole.setName("EMPLOYEE");

        Role salesRole = new Role();
        salesRole.setName("SALES");

        Role financeRole = new Role();
        financeRole.setName("FINANCE");

        Set<Role> bobRoles = new HashSet<>();
        bobRoles.add(employeeRole);
        bobRoles.add(salesRole);

        User bob = new User();
        bob.setName("bob");
        bob.setEmail("bob@gmail.com");
        bob.setPassword(passwordEncoder.encode("password"));
        bob.setRoles(bobRoles);

        Set<Role> maryRoles = new HashSet<>();
        maryRoles.add(employeeRole);
        maryRoles.add(financeRole);

        User mary = new User();
        mary.setName("mary");
        mary.setEmail("mary@gmail.com");
        mary.setPassword(passwordEncoder.encode("password"));
        mary.setRoles(maryRoles);

        userRepository.save(bob);
        userRepository.save(mary);
    }
}
