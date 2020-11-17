package spring_security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import spring_security.model.User;

import java.util.List;
@Service
public interface UserService extends UserDetailsService {
    List<User> allUsers();
    void add(User user);
    void delete(Integer id);
    void edit(User user);
    User getUserById(Integer id);
    User getUserByName(String name);
}
