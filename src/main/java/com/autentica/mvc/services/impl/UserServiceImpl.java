package com.autentica.mvc.services.impl;

import com.autentica.mvc.dao.ReservationDao;
import com.autentica.mvc.dao.UserDao;
import com.autentica.mvc.dao.impl.ReservationDaoImpl;
import com.autentica.mvc.models.database.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.autentica.mvc.services.UserService;

import java.util.List;

/**
 * Created by mkl on 3/6/2017.
 */

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private ReservationDao reservationDao;

    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        long id = reservationDao.getReservationIdByUserId(user.getId());
        user.setCurrentReservationId(id);

        this.userDao.updateUser(user);
    }

    @Transactional
    public void deleteUser(long id) {
        this.userDao.deleteUser(id);
    }

    @Transactional
    public User getUserById(long id) {
        return this.userDao.getUserById(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }
}
