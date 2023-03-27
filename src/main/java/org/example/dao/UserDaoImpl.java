package org.example.dao;

import org.example.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {

        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        List<User> allUsers = query.getResultList();

        return allUsers;
    }

    @Override
    public void saveUser(User user) {
        if ((user.getId() == 0)) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        Query<User> query = (Query<User>) entityManager.createQuery("delete from User u where u.id = :UserId");
        query.setParameter("UserId", id);
        query.executeUpdate();

    }

}
