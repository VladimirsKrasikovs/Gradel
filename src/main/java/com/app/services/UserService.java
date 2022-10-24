package com.app.services;

import com.app.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("Test", "test", "test@test", "+3712"));
        users.add(new User("Test1", "test2", "test2@test", "+3712"));
        users.add(new User("Test2", "test2", "test3@test", "+371234"));
        users.add(new User("Test3", "test3", "test4@test", "+371235"));
        users.add(new User("Test4", "test4", "test5@test", "+3712356"));
        users.add(new User("Test5", "test5", "test6@test", "+3712356"));
        users.add(new User("Test6", "test6", "test7@test", "+3712345"));
        users.add(new User("Test8", "test8", "test8@test", "+371231"));

          return users;
    }


    public User validateUser(User user){
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() ||
            user.geteMail().isEmpty() || user.getPhoneNumber().isEmpty()){
            return null;
        }

        String phone = user.getPhoneNumber().trim().replaceAll("\\(", "")
                .replaceAll("\\)", "");
        user.setPhoneNumber(phone);
            return user;
    }

}
