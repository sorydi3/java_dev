package com.udacity.jwdnd.course1.cloudstorage.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
   
    /**
     * @param user
     * @RETURN user id if user is added successfully
     */
    public int addUser(User user) {
        System.out.println(user);
        return userMapper.addUser(user);
    }

    /**
     * @param username
     * @return user if user is found otherwise null
     **/
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    /**
     * @param username
     * @return user id if user is deleted successfully
     */
    public int deleteUser(String username) {
        return userMapper.deleteUser(username);
    }

    /**
     * @param username
     * @return user id if user is updated successfully
     */
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * @param username
     * @return true if user is found otherwise false
     */
    public boolean isUsernameAvailable(String userName) {
        return userMapper.getUser(userName) == null;
    }

}
