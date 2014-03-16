package com.crowdchef.core;

import com.crowdchef.datamodel.ValidationException;
import com.crowdchef.datamodel.daos.UserDAO;
import com.crowdchef.datamodel.entities.User;
import com.crowdchef.datamodel.entities.UserInfo;

public class UserHandler {
    private UserDAO userDao;

    public UserHandler(DatabaseController databaseController) throws ValidationException {
        this.userDao = new UserDAO(databaseController.getDatabase());
    }

    public Long registerUser(String username, String password) {
        return userDao.updateUser(null, username, password);
    }

    public Long checkUser(String username, String password) {
        User user;
        try {
            user = userDao.getUser(username);
        } catch (ValidationException e) {
            return null;
        }
        return user.getId();
    }

    public void updateUser(Long id, String username, String password) throws ValidationException {
        userDao.updateUser(id, username, password);
    }

    public User getUser(Long id) throws ValidationException {
        return userDao.getUser(id);
    }

    public UserInfo getUserInfo(Long id) throws ValidationException {
        UserInfo userInfo = userDao.getUserInfo(id);
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public void updateUserInfo(Long id, String email, String address, String city, String country) throws ValidationException {
        userDao.updateUserInfo(id, email, address, city, country);
    }
}
