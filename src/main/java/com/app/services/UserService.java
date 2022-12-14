package com.app.services;

import com.app.dao.UserDao;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUsers(){

          return userDao.getUsers();
    }


//    public User validateUser(User user){
//        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() ||
//            user.geteMail().isEmpty() || user.getPhoneNumber().isEmpty()){
//            return null;
//        }
//
//        String phone = user.getPhoneNumber().trim().replaceAll("\\(", "")
//                .replaceAll("\\)", "");
//        user.setPhoneNumber(phone);
//            return user;
//    }

    public User validateUser(User user){
        if (!user.getFirstName().isEmpty() || !user.getLastName().isEmpty() ||
            !user.geteMail().isEmpty() || !user.getPhoneNumber().isEmpty()){

            String phone = user.getPhoneNumber().trim().replaceAll("\\(", "")
               .replaceAll("\\)", "");
            user.setPhoneNumber(phone);

            userDao.saveUser(user);
           return user;
       }

      return null;
    }

}
