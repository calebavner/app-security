package app.cofig;

import app.entities.Role;
import app.entities.User;
import app.repos.RoleRepo;
import app.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class UserAdminConfig implements CommandLineRunner {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAdminConfig(RoleRepo roleRepo, UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepo.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepo.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin ja existe");
                },
                () -> {
                    var user = new User();
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));

                    userRepo.save(user);
                }
        );

    }
}
