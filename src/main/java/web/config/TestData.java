package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestData {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public TestData(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void insertData() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.addNewRole(roleAdmin);
        Role roleUser = new Role("ROLE_USER");
        roleService.addNewRole(roleUser);
        //roleService.addNewRole(new Role("ROLE_USER"));
        Set<Role> roles1 = new HashSet<>(roleService.getRoles());

       // Set<Role> roles1 = new HashSet<>();
       // roles1.add(roleService.getRolesByName("ROLE_ADMIN");

        User adm = new User();
        adm.setName("ADMIN");
        adm.setPassword("111");
        adm.setUsername("Admin");
        adm.setEmail("111@test.com");
        adm.setRoles(roles1);
        userService.create(adm);


       Set<Role> roles2 = new HashSet<>();

        User usr = new User();
        usr.setName("USER");
        usr.setPassword("112");
        usr.setUsername("User");
        usr.setEmail("112@test.com");
        usr.setRoles(roles2);
        userService.create(usr);
        usr.setRoles(Set.of(roleUser));
           }
}