package com.crowdchef.datamodel.daos;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.ValidationErrorCode;
import com.crowdchef.datamodel.ValidationException;
import com.crowdchef.datamodel.entities.User;
import com.crowdchef.datamodel.entities.UserInfo;

import java.util.List;

public class UserDAO {
    private CrowdChefDatabase database;

    public UserDAO(CrowdChefDatabase database) {
        this.database = database;
    }

    public User updateUser(User user, String username, String password) throws ValidationException {
        if (user == null) {
            user = new User(username, password);
        }
        user.setUsername(username);
        user.setPassword(password);
        database.saveOrUpdate(user, User.class);
        return user;
    }

    public User updateUser(Long id, String username, String password) throws ValidationException {
        User user;
        if (id == null) {
            user = new User(username, password);
        } else {
            user = getUser(id);
        }
        updateUser(user, username, password);
        return user;
    }

    public void deleteUser(Long id) throws ValidationException {
        User user = getUser(id);
        database.delete(user, User.class);
    }

    public void deleteAllUsers() throws ValidationException {
        List<User> users = getAllUsers();
        database.delete(users, User.class);
    }

    public List<User> getAllUsers() throws ValidationException {
        List<User> result = database.retrieve("AllUsers", User.class);
        return result;
    }

    public User getUser(Long id) throws ValidationException {
        List<User> result = database.retrieve("OneUserById", "id", id, User.class);
        if (result.size() < 1)
            throw new ValidationException(ValidationErrorCode.ID_NOT_EXIST);
        else if (result.size() > 1)
            throw new ValidationException(ValidationErrorCode.TOO_MANY_RESULTS);
        return result.get(0);
    }

    public User getUser(String username) throws ValidationException {
        List<User> result = database.retrieve("OneUserByUsername", "username", username, User.class);
        if (result.size() < 1)
            throw new ValidationException(ValidationErrorCode.ID_NOT_EXIST);
        else if (result.size() > 1)
            throw new ValidationException(ValidationErrorCode.TOO_MANY_RESULTS);
        return result.get(0);
    }

    public UserInfo getUserInfo(Long id) throws ValidationException {
        User user = getUser(id);
        return user.getUserInfo();
    }

    public User updateUserInfo(User user, String email, String address, String city, String country) throws ValidationException {
        UserInfo userInfo = user.getUserInfo();
        if (userInfo == null) {
            userInfo = new UserInfo(user, email, address, city, country);
            user.setUserInfo(userInfo);
        } else {
            userInfo.setEmail(email);
            userInfo.setAddress(address);
            userInfo.setCity(city);
            userInfo.setCountry(country);
        }
        database.saveOrUpdate(userInfo, UserInfo.class);
        return user;
    }

    public User updateUserInfo(Long id, String email, String address, String city, String country) throws ValidationException {
        User user = getUser(id);
        updateUserInfo(user, email, address, city, country);
        return user;
    }
}
