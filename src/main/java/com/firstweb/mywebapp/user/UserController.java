package com.firstweb.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/user/add")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add User");
        return "user_form";
    }

    @PostMapping("/user/save")
    public String save(User user, RedirectAttributes redirectAttributes){
        userService.save(user);
        redirectAttributes.addFlashAttribute("message","Successfully");
        return "redirect:/user";
    }

    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            User user = userService.getUser(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User");
            return "user_form";
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/user";
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message","The User ID: "+id+" has been deleted");
        } catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/user";
    }
}
