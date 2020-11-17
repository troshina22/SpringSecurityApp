package spring_security.dao;

import spring_security.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();
    void add(User user);
    void delete(Integer id);
    void edit(User user);
    User getUserById(Integer id);
    User getByName (String name);
    User getUserByName(String name);
}
