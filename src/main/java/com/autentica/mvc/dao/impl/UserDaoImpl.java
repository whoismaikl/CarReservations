package com.autentica.mvc.dao.impl;

import com.autentica.mvc.dao.UserDao;
import com.autentica.mvc.models.database.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by mkl on 3/6/2017.
 */

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;

    public void addUser(User user) {

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User saved. User " + user);
    }

    public void updateUser(User user) {

        Session session = this.sessionFactory.getCurrentSession();
        session.flush();
        session.update(user);
        logger.info("User updated. User " + user);
    }

    public void deleteUser(long id) {
        Session session = this.sessionFactory.getCurrentSession();

        User user = (User) session.load(User.class, id);
        if (user != null) {
            session.delete(user);
            //session.delete(id);
        }
        logger.info("User deleted. User " + user);
    }

    public User getUserById(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        //User user = (User) session.get(User.class, id);
        logger.info("User loaded. User " + user);
        return user;
    }

    public List<User> getAllUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();

        for (User user : userList) {
            logger.info("Users List + " + user);
        }

        return userList;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
