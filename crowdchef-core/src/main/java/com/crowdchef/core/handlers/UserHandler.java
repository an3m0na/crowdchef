package com.crowdchef.core.handlers;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.ValidationErrorCode;
import com.crowdchef.datamodel.ValidationException;
import com.crowdchef.datamodel.daos.UserDAO;
import com.crowdchef.datamodel.entities.User;
import com.crowdchef.datamodel.entities.UserInfo;

public class UserHandler {
    private UserDAO userDao;

    public UserHandler(CrowdChefDatabase database) throws ValidationException {
        this.userDao = new UserDAO(database);
    }

    public User registerUser(String username, String password) {
        return userDao.updateUser((Long) null, username, password);
    }

    public User checkUser(String username, String password) throws ValidationException {
        User user = userDao.getUser(username);
        if (!user.getPassword().equals(password))
            throw new ValidationException(ValidationErrorCode.USER_INCORRECT);
        return user;
    }

    public User updateUser(Long id, String username, String password) throws ValidationException {
        return userDao.updateUser(id, username, password);
    }

    public User getUser(Long id) throws ValidationException {
        return userDao.getUser(id);
    }

    public UserInfo getUserInfo(Long id) throws ValidationException {
        return userDao.getUserInfo(id);
    }

    public User updateUserInfo(Long id, String email, String address, String city, String country) throws ValidationException {
        return userDao.updateUserInfo(id, email, address, city, country);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
