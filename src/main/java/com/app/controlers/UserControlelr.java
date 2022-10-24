package com.app.controlers;


import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserControlelr {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model){
      model.addAttribute("users",userService.getUsers());
      return "users";
    }
}
