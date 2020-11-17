package spring_security.dao;

import org.springframework.stereotype.Repository;
import spring_security.model.Role;
import spring_security.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<User> allUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1,"ROLE_USER"));
        user.setRoles(roles);
        em.persist(user);
    }

    @Override
    public void delete(Integer id) {
        User user = em.find(User.class,id);
        em.remove(user);
    }

    @Override
    public void edit(User user) {
        em.merge(user);
    }

    @Override
    public User getUserById(Integer id) {
        return em.find(User.class,id);
    }

    @Override
    public User getByName(String username) {
        return em.find(User.class, username);
    }

    @Override
    public User getUserByName(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :userName", User.class)
                .setParameter("userName", username)
                .setMaxResults(1)
                .getSingleResult();
    }
}
