package com.autentica.mvc.dao;

import com.autentica.mvc.models.database.User;
import java.util.List;

/**
 * Created by mkl on 3/6/2017.
 */
public interface UserDao {

    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(long id);
    public User getUserById(long id);
    public List<User> getAllUsers();

}
