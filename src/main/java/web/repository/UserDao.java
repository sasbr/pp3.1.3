package web.repository;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;

import java.util.List;

public interface UserDao {
    void updateUser(Long id, User user);

    List<User> getAllUsers();

    UserDetails loadUserByUsername(String s);

    void addUser(User user);

    void deleteUser(Long id);

    User getUser(Long id);
}
