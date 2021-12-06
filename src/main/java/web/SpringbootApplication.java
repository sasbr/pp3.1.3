package web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import web.model.Role;
import web.model.User;
import web.service.RoleServiceImp;
import web.service.UserServiceImp;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

  //  @Bean
   // CommandLineRunner commandLineRunner(UserServiceImp userService, RoleServiceImp roleService) {
     //   return args -> {
       //     System.out.println("In CommandLineRunnerImpl ");
/*
           roleService.addNewRole(new Role(null, "ROLE_USER"));
            roleService.addNewRole(new Role(null, "ROLE_ADMIN"));

            userService.create(new User ("ADMIN", "ADMIN", "Admin@mail.ru","111", new HashSet<>()));
            userService.create(new User ("USER", "USER", "User@mail.ru","112", new HashSet<>()));
*/
/*
            Role user = new Role("ROLE_USER");
            roleService.addNewRole(user);
            Role admin = new Role("ROLE_ADMIN");
            roleService.addNewRole(admin);

            User user1 = new User("USER", "USER", "sas@sas", "112",
                    new HashSet<>());
            user1.setRoles(Set.of(user));

            Set<Role> roleAdmin = new HashSet<>(roleService.getRoles());
            /*
            User user2 = new User("ADMIN", "ADMIN", "Adm@admin", "111",
                    new HashSet<>());
            user2.setRoles(roleAdmin);

            userService.create(user1);
            userService.create(user2); */
      //  };
  //  }
}
