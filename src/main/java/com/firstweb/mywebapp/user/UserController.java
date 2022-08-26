package com.firstweb.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String showListUser(Model model){
        List<User> listUser = userService.listAllUser();
        model.addAttribute("listUser",listUser);

        return "users";
    }
}
