package org.example.dao;

import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public UserDaoImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

//    private final EntityManagerFactory entityManagerFactory;
//
//    @Autowired
//    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public List<User> getAllUsers() {
//        Session session = sessionFactory.getCurrentSession();
//        List<User> allUsers = session.createQuery("from User", User.class).getResultList();
//        Query<User> query = session.createQuery("from User", User.class);
//        List<User> allUsers = query.getResultList();
//        return allUsers;
//    }

        @Override
        public List<User> getAllUsers() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        List<User> allUsers = query.getResultList();

        return allUsers;
    }

//    @Override
//    public void saveUser(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        session.merge(user);
//        entityManager.merge(user);
//    }

    @Override
    public void saveUser(User user) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.merge(user);
    }

//    @Override
//    public User getUser(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        User user = session.get(User.class, id);
//        return user;
//    }


    public User getUser(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, id);

        return user;
    }

//    @Override
//    public void deleteUser(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Query<User> query = session.createQuery("delete from User where id = :UserId");
//        query.setParameter("UserId", id);
//        query.executeUpdate();

    @Override
    public void deleteUser(int id) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query<User> query = (Query<User>) entityManager.createQuery("delete from User u where u.id = :UserId");
        query.setParameter("UserId", id);
        query.executeUpdate();

    }

}
