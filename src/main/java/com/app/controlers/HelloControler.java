package com.app.controlers;

import com.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HelloControler {

    @GetMapping("/hello/{userName}")
    public String sayHello(@PathVariable(value = "userName") String userName, Model model){
        model.addAttribute("name", userName);
        return "hello";
    }


}
