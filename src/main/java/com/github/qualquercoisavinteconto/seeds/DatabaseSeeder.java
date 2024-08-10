package com.github.qualquercoisavinteconto.seeds;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.qualquercoisavinteconto.models.Role;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.repositories.RoleRepository;
import com.github.qualquercoisavinteconto.repositories.UserRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DatabaseSeeder(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAll(List.of(
                    new Role("ADMIN"),
                    new Role("CUSTOMER"),
                    new Role("SELLER"),
                    new Role("ADVERTISER")));
        }

        if (this.userRepository.count() == 0) {
            String encryptedPassword = new BCryptPasswordEncoder().encode("123456");
            User user = new User("Admin Tester", "tester@admin.com", encryptedPassword);
            Role role = roleRepository.findById(1L).orElseThrow();
            user.setRoles(List.of(role));
            this.userRepository.save(user);
        }
    }

}
